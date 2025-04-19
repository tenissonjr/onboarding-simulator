package com.onboarding.simulator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.onboarding.simulator.dto.OnboardingDataResponseDto;
import com.onboarding.simulator.model.SimulatedOnboardingData;
import com.onboarding.simulator.model.SimulatorConfig;
import com.onboarding.simulator.repository.SimulatedOnboardingDataRepository;
import com.onboarding.simulator.repository.SimulatorConfigRepository;
import com.onboarding.simulator.service.SimulatorService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/onboarding")
public class OnboardingDataController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OnboardingDataController.class);

    private final SimulatedOnboardingDataRepository onboardingDataRepository;
    private final SimulatorConfigRepository configRepository;
    private final SimulatorService simulatorService;

    public OnboardingDataController(SimulatedOnboardingDataRepository onboardingDataRepository,
                                  SimulatorConfigRepository configRepository,
                                  SimulatorService simulatorService) {
        this.onboardingDataRepository = onboardingDataRepository;
        this.configRepository = configRepository;
        this.simulatorService = simulatorService;
    }    
    
    @GetMapping("/data/{hash}")
    public ResponseEntity<OnboardingDataResponseDto> getOnboardingData(@PathVariable String hash, HttpServletResponse response) {
        log.debug("Received request for onboarding data with hash: {}", hash);
        
        // Check if errors should be simulated
        SimulatorConfig config = configRepository.findById(1L).orElseThrow();
        if (config.isGenerateErrors() && Math.random() < config.getErrorRate()) {
            log.debug("Simulating error response for hash: {}", hash);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Simulated service error");
        }
        
        // Simulate response delay if configured
        if (config.getResponseDelayMs() > 0) {
            try {
                Thread.sleep(config.getResponseDelayMs());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        SimulatedOnboardingData data = onboardingDataRepository.findById(hash)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found for hash: " + hash));
        
        OnboardingDataResponseDto responseDto = simulatorService.mapToResponseDto(data);
        return ResponseEntity.ok(responseDto);
    }
    
    @PostMapping("/notification")
    public ResponseEntity<String> testNotificationEndpoint(@RequestBody String body) {
        log.debug("Received test notification: {}", body);
        return ResponseEntity.ok("Notification received successfully");
    }
}