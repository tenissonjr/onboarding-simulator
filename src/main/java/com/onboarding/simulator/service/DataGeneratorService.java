package com.onboarding.simulator.service;

import com.github.javafaker.Faker;
import com.onboarding.simulator.model.SimulatedOnboardingData;
import com.onboarding.simulator.repository.SimulatedOnboardingDataRepository;
import com.onboarding.simulator.repository.SimulatorConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataGeneratorService {
    private final SimulatedOnboardingDataRepository onboardingDataRepository;
    private final SimulatorConfigRepository configRepository;
    private final Faker faker = new Faker(new Locale("pt-BR"));
    
    public SimulatedOnboardingData generateOnboardingData() {
        String hash = generateUniqueHash();
        var config = configRepository.findById(1L).orElseThrow();
        int ttlMinutes = config.getDataTtlMinutes();
        
        SimulatedOnboardingData data = new SimulatedOnboardingData();
        data.setHash(hash);
        data.setCpf(generateCpf());
        data.setNome(faker.name().fullName());
        data.setNomeSocial(Math.random() > 0.8 ? faker.name().fullName() : null);
        
        // Generate birth date between 18 and 90 years ago
        LocalDate birthDate = generateRandomDate(LocalDate.now().minusYears(90), LocalDate.now().minusYears(18));
        data.setDataNascimento(birthDate);
        
        data.setNomeMae(faker.name().fullName());
        data.setNumeroDocumento(generateDocumentNumber());
        data.setPaisOrigem("Brasil");
        data.setOrgaoEmissor(generateOrgaoEmissor());
        data.setUf(generateUf());
        
        // Document should be issued between 18th birthday and now
        LocalDate minIssueDate = birthDate.plusYears(18);
        LocalDate issueDate = generateRandomDate(minIssueDate, LocalDate.now());
        data.setDataExpedicao(issueDate);
        
        // Document expires 10 years after issuance
        data.setDataVencimento(issueDate.plusYears(10));
        
        // Generate mock base64 images
        data.setFotoUsuario(generateMockBase64Image("selfie"));
        data.setImagemDocumentoFrente(generateMockBase64Image("docFront"));
        data.setImagemDocumentoVerso(generateMockBase64Image("docBack"));
        
        data.setCreatedAt(LocalDateTime.now());
        data.setExpiresAt(LocalDateTime.now().plusMinutes(ttlMinutes));
        
        log.debug("Generated onboarding data with hash: {}", hash);
        return onboardingDataRepository.save(data);
    }
    
    private String generateUniqueHash() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    private String generateCpf() {
        int[] cpf = new int[11];
        Random random = new Random();
        
        // Generate 9 random digits
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }
        
        // Calculate first verification digit
        int sum = 0;
        for (int i = 0, j = 10; i < 9; i++, j--) {
            sum += cpf[i] * j;
        }
        int remainder = sum % 11;
        cpf[9] = remainder < 2 ? 0 : 11 - remainder;
        
        // Calculate second verification digit
        sum = 0;
        for (int i = 0, j = 11; i < 10; i++, j--) {
            sum += cpf[i] * j;
        }
        remainder = sum % 11;
        cpf[10] = remainder < 2 ? 0 : 11 - remainder;
        
        // Format CPF (xxx.xxx.xxx-xx)
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d",
                cpf[0], cpf[1], cpf[2], cpf[3], cpf[4], cpf[5], cpf[6], cpf[7], cpf[8], cpf[9], cpf[10]);
    }
    
    private String generateDocumentNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    
    private String generateOrgaoEmissor() {
        String[] orgaos = {"SSP", "DETRAN", "SESP", "SJS", "SPTC", "IFP", "DGPC"};
        return orgaos[new Random().nextInt(orgaos.length)];
    }
    
    private String generateUf() {
        String[] ufs = {"AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT",
                "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"};
        return ufs[new Random().nextInt(ufs.length)];
    }
    
    private LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long startDay = startDate.toEpochDay();
        long endDay = endDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startDay, endDay + 1);
        return LocalDate.ofEpochDay(randomDay);
    }
    
    private String generateMockBase64Image(String type) {
        // Generate mock base64 image in format: data:image/png;base64,...
        // In a real implementation, you'd generate actual document/selfie images
        // Here we'll just return a placeholder
        return "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII=";
    }
    
    public void cleanupExpiredData() {
        LocalDateTime now = LocalDateTime.now();
        List<SimulatedOnboardingData> expiredData = onboardingDataRepository.findByExpiresAtBefore(now);
        if (!expiredData.isEmpty()) {
            log.debug("Cleaning up {} expired onboarding data records", expiredData.size());
            onboardingDataRepository.deleteAll(expiredData);
        }
    }
}