package com.onboarding.simulator.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.onboarding.simulator.dto.AuthenticationRequest;
import com.onboarding.simulator.dto.AuthenticationResponse;
import com.onboarding.simulator.model.SimulatorConfig;
import com.onboarding.simulator.model.valid.ValidOnboardingData;
import com.onboarding.simulator.repository.SimulatorConfigRepository;
import com.onboarding.simulator.service.valid.ValidOnboardingDataService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/Admin")
public class OnboardingDataController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OnboardingDataController.class);

    private final ValidOnboardingDataService validOnboardingDataService;
    private final SimulatorConfigRepository configRepository;

    public OnboardingDataController(ValidOnboardingDataService validOnboardingDataService,
            SimulatorConfigRepository configRepository) {
        this.validOnboardingDataService = validOnboardingDataService;
        this.configRepository = configRepository;
    }

    @PostMapping("/Authentication/Token")
    public ResponseEntity<AuthenticationResponse> getAuthenticationToken(@RequestBody AuthenticationRequest request) {
        log.debug("Received request for authentication token");
        String token = java.util.UUID.randomUUID().toString();
        return ResponseEntity.ok(new AuthenticationResponse("Câmara dos Deputados", 9000, token));
    }

    @GetMapping("/Schedule/{hash}")
    public ResponseEntity<ValidOnboardingData> getOnboardingData(@PathVariable String hash,
            HttpServletResponse response) {
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

        ValidOnboardingData data = validOnboardingDataService.findByIdentifier(hash)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found for hash: " + hash));

        return ResponseEntity.ok(data);
    }

    @GetMapping("/StartDate/{startDate}/EndDate/{endDate}/Page/{page}/Size/{size}")
    public ResponseEntity<List<ValidOnboardingData>> listOnboardingData(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS") LocalDateTime startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS") LocalDateTime endDate,
            @PathVariable int page,
            @PathVariable int size) {

        log.debug("Received request to list all onboarding data");
        List<ValidOnboardingData> dataList = validOnboardingDataService.list(startDate, endDate, (page-1), size);
        return ResponseEntity.ok(dataList);

    }
}