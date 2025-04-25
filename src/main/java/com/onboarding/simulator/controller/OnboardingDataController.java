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

import com.onboarding.simulator.model.SimulatorConfig;
import com.onboarding.simulator.model.valid.ValidOnboardingData;
import com.onboarding.simulator.repository.SimulatorConfigRepository;
import com.onboarding.simulator.repository.valid.ValidOnboardingDataRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/onboarding")
public class OnboardingDataController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OnboardingDataController.class);

    private final ValidOnboardingDataRepository validOnboardingDataRepository;
    private final SimulatorConfigRepository configRepository;

    public OnboardingDataController(ValidOnboardingDataRepository validOnboardingDataRepository,
                                  SimulatorConfigRepository configRepository) {
        this.validOnboardingDataRepository = validOnboardingDataRepository;
        this.configRepository = configRepository;
    }    
    
    @GetMapping("/data/{hash}")
    public ResponseEntity<ValidOnboardingData> getOnboardingData(@PathVariable String hash, HttpServletResponse response) {
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
        
        ValidOnboardingData data = validOnboardingDataRepository.findById(hash)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found for hash: " + hash));
        
        return ResponseEntity.ok(data);
    }
    
    @PostMapping("/notification")
    public ResponseEntity<String> testNotificationEndpoint(@RequestBody String body) {
        log.debug("Received test notification: {}", body);
        return ResponseEntity.ok("Notification received successfully");
    }
}