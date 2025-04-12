package com.onboarding.simulator.service;

import com.onboarding.simulator.dto.*;
import com.onboarding.simulator.model.GenerationPattern;
import com.onboarding.simulator.model.NotificationStatus;
import com.onboarding.simulator.model.SimulatedOnboardingData;
import com.onboarding.simulator.model.SimulatorConfig;
import com.onboarding.simulator.repository.NotificationRecordRepository;
import com.onboarding.simulator.repository.SimulatedOnboardingDataRepository;
import com.onboarding.simulator.repository.SimulatorConfigRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class SimulatorService {
    private final SimulatorConfigRepository configRepository;
    private final SimulatedOnboardingDataRepository onboardingDataRepository;
    private final NotificationRecordRepository notificationRepository;
    private final DataGeneratorService dataGeneratorService;
    private final NotificationService notificationService;
    
    private long totalGeneratedCount = 0;
    private final Random random = new Random();
    
    @Value("${simulator.default.cadastros-per-minute}")
    private int defaultCadastrosPerMinute;
    
    @Value("${simulator.default.pattern}")
    private String defaultPattern;
    
    @Value("${simulator.default.generate-errors}")
    private boolean defaultGenerateErrors;
    
    @Value("${simulator.default.error-rate}")
    private double defaultErrorRate;
    
    @Value("${simulator.default.response-delay-ms}")
    private int defaultResponseDelayMs;
    
    @Value("${simulator.default.data-ttl-minutes}")
    private int defaultDataTtlMinutes;
    
    @Value("${simulator.default.notification-endpoint}")
    private String defaultNotificationEndpoint;
    
    @PostConstruct
    public void initializeConfig() {
        if (configRepository.findById(1L).isEmpty()) {
            SimulatorConfig config = new SimulatorConfig();
            config.setId(1L);
            config.setCadastrosPerMinute(defaultCadastrosPerMinute);
            config.setPattern(GenerationPattern.valueOf(defaultPattern));
            config.setGenerateErrors(defaultGenerateErrors);
            config.setErrorRate(defaultErrorRate);
            config.setResponseDelayMs(defaultResponseDelayMs);
            config.setDataTtlMinutes(defaultDataTtlMinutes);
            config.setNotificationEndpoint(defaultNotificationEndpoint);
            config.setSimulationRunning(false);
            configRepository.save(config);
            log.info("Initialized default simulator configuration");
        }
    }
    
    @Transactional
    public SimulatorConfig updateGenerationRate(GenerationRateConfigDto configDto) {
        SimulatorConfig config = configRepository.findById(1L).orElseThrow();
        config.setCadastrosPerMinute(configDto.getCadastrosPerMinute());
        config.setPattern(configDto.getPattern());
        return configRepository.save(config);
    }
    
    @Transactional
    public SimulatorConfig updateErrorConfig(ErrorConfigDto configDto) {
        SimulatorConfig config = configRepository.findById(1L).orElseThrow();
        config.setGenerateErrors(configDto.isGenerateErrors());
        config.setErrorRate(configDto.getErrorRate());
        config.setResponseDelayMs(configDto.getResponseDelayMs());
        return configRepository.save(config);
    }
    
    @Transactional
    public SimulatorConfig updateNotificationConfig(NotificationConfigDto configDto) {
        SimulatorConfig config = configRepository.findById(1L).orElseThrow();
        config.setNotificationEndpoint(configDto.getNotificationEndpoint());
        config.setDataTtlMinutes(configDto.getDataTtlMinutes());
        return configRepository.save(config);
    }
    
    @Transactional
    public SimulatorConfig getConfig() {
        return configRepository.findById(1L).orElseThrow();
    }
    
    @Transactional
    public void startSimulation() {
        SimulatorConfig config = configRepository.findById(1L).orElseThrow();
        config.setSimulationRunning(true);
        configRepository.save(config);
        log.info("Simulation started");
    }
    
    @Transactional
    public void stopSimulation() {
        SimulatorConfig config = configRepository.findById(1L).orElseThrow();
        config.setSimulationRunning(false);
        configRepository.save(config);
        log.info("Simulation stopped");
    }
    
    @Transactional
    public SimulatedOnboardingData generateSingleCadastro() {
        SimulatedOnboardingData data = dataGeneratorService.generateOnboardingData();
        totalGeneratedCount++;
        notificationService.sendNotification(data);
        return data;
    }
    
    @Scheduled(fixedRate = 60000) // Run every minute
    @Transactional
    public void scheduledGeneration() {
        SimulatorConfig config = configRepository.findById(1L).orElseThrow();
        
        if (!config.isSimulationRunning() || config.getCadastrosPerMinute() <= 0) {
            return;
        }
        
        int cadastrosToGenerate = config.getCadastrosPerMinute();
        log.info("Scheduled generation of {} cadastros", cadastrosToGenerate);
        
        switch (config.getPattern()) {
            case CONSTANT:
                generateWithConstantPattern(cadastrosToGenerate);
                break;
            case BURST:
                generateWithBurstPattern(cadastrosToGenerate);
                break;
            case INCREASING:
                generateWithIncreasingPattern(cadastrosToGenerate);
                break;
            case RANDOM:
                generateWithRandomPattern(cadastrosToGenerate);
                break;
        }
    }
    
    private void generateWithConstantPattern(int total) {
        // Distribute evenly throughout the minute in separate thread
        new Thread(() -> {
            try {
                int interval = 60000 / total;
                for (int i = 0; i < total; i++) {
                    generateSingleCadastro();
                    Thread.sleep(interval);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    
    private void generateWithBurstPattern(int total) {
        // Generate all at once
        for (int i = 0; i < total; i++) {
            generateSingleCadastro();
        }
    }
    
    private void generateWithIncreasingPattern(int total) {
        // Gradually increase rate throughout minute in separate thread
        new Thread(() -> {
            try {
                for (int i = 0; i < total; i++) {
                    // Gradually decrease sleep time to increase frequency
                    int sleep = (int) (60000 * (1 - ((double) i / total)));
                    Thread.sleep(Math.max(10, sleep / total));
                    generateSingleCadastro();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    
    private void generateWithRandomPattern(int total) {
        // Generate random bursts in separate thread
        new Thread(() -> {
            try {
                for (int i = 0; i < total; i++) {
                    // Random sleep between generations
                    Thread.sleep(random.nextInt(60000 / total));
                    generateSingleCadastro();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    
    @Scheduled(fixedRate = 300000) // Every 5 minutes
    @Transactional
    public void cleanupExpiredData() {
        dataGeneratorService.cleanupExpiredData();
    }
    
    public SimulatorStatisticsDto getSimulationStatistics() {
        SimulatorConfig config = configRepository.findById(1L).orElseThrow();
        
        return new SimulatorStatisticsDto(
                totalGeneratedCount,
                notificationRepository.countByStatus(NotificationStatus.SENT),
                notificationRepository.countByStatus(NotificationStatus.FAILED),
                onboardingDataRepository.countActiveCadastros(LocalDateTime.now()),
                config.getCadastrosPerMinute(),
                config.isSimulationRunning()
        );
    }
    
    public OnboardingDataResponseDto mapToResponseDto(SimulatedOnboardingData data) {
        OnboardingDataResponseDto.DocumentoDto documentoDto = new OnboardingDataResponseDto.DocumentoDto(
                data.getNumeroDocumento(),
                data.getPaisOrigem(),
                data.getOrgaoEmissor(),
                data.getUf(),
                data.getDataExpedicao(),
                data.getDataVencimento()
        );
        
        return new OnboardingDataResponseDto(
                data.getCpf(),
                data.getNome(),
                data.getNomeSocial(),
                data.getDataNascimento(),
                data.getNomeMae(),
                documentoDto,
                data.getFotoUsuario(),
                data.getImagemDocumentoFrente(),
                data.getImagemDocumentoVerso()
        );
    }
}