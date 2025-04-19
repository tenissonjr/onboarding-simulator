package com.onboarding.simulator.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.onboarding.simulator.model.NotificationRecord;
import com.onboarding.simulator.model.NotificationStatus;
import com.onboarding.simulator.model.SimulatedOnboardingData;
import com.onboarding.simulator.repository.NotificationRecordRepository;
import com.onboarding.simulator.repository.SimulatorConfigRepository;


@Service
public class NotificationService {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRecordRepository notificationRepository;
    private final SimulatorConfigRepository configRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final Random random = new Random();
    
    public NotificationService(NotificationRecordRepository notificationRepository,
                              SimulatorConfigRepository configRepository) {
        this.notificationRepository = notificationRepository;
        this.configRepository = configRepository;
    }

    public NotificationRecord sendNotification(SimulatedOnboardingData data) {
        var config = configRepository.findById(1L).orElseThrow();
        String endpoint = config.getNotificationEndpoint();
        
        NotificationRecord record = new NotificationRecord();
        record.setHash(data.getHash());
        record.setNotificationTime(LocalDateTime.now());
        record.setTargetEndpoint(endpoint);
        record.setStatus(NotificationStatus.PENDING);
        
        // Simulate potential errors based on configuration
        if (config.isGenerateErrors() && random.nextDouble() < config.getErrorRate()) {
            record.setStatus(NotificationStatus.FAILED);
            record.setErrorMessage("Simulated error");
            log.debug("Simulated notification failure for hash: {}", data.getHash());
            return notificationRepository.save(record);
        }
        
        // Simulate response delay if configured
        if (config.getResponseDelayMs() > 0) {
            try {
                Thread.sleep(config.getResponseDelayMs());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Build and send notification
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            Map<String, String> payload = Map.of("hash", data.getHash());
            HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);
            
            restTemplate.postForEntity(endpoint, request, String.class);
            
            record.setStatus(NotificationStatus.SENT);
            log.debug("Sent notification for hash: {}", data.getHash());
        } catch (RestClientException e) {
            record.setStatus(NotificationStatus.FAILED);
            record.setErrorMessage(e.getMessage());
            log.error("Failed to send notification for hash: {}", data.getHash(), e);
        }
        
        return notificationRepository.save(record);
    }
}