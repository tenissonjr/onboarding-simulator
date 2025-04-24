package com.onboarding.simulator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onboarding.simulator.dto.ErrorConfigDto;
import com.onboarding.simulator.dto.GenerationRateConfigDto;
import com.onboarding.simulator.dto.NotificationConfigDto;
import com.onboarding.simulator.model.SimulatorConfig;
import com.onboarding.simulator.service.SimulatorService;

@RestController
@RequestMapping("/api/simulator/config")

public class SimulatorConfigController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SimulatorConfigController.class);

    private final SimulatorService simulatorService;

    public SimulatorConfigController(SimulatorService simulatorService) {
        this.simulatorService = simulatorService;
    }
    
    @GetMapping
    public ResponseEntity<SimulatorConfig> getConfig() {
        return ResponseEntity.ok(simulatorService.getConfig());
    }
    
    @PutMapping("/rate")
    public ResponseEntity<SimulatorConfig> updateGenerationRate(@RequestBody GenerationRateConfigDto configDto) {
        log.info("Updating generation rate to {} cadastros per minute with pattern {}", 
                configDto.getCadastrosPerMinute(), configDto.getPattern());
        return ResponseEntity.ok(simulatorService.updateGenerationRate(configDto));
    }
    
    @PutMapping("/errors")
    public ResponseEntity<SimulatorConfig> updateErrorConfig(@RequestBody ErrorConfigDto configDto) {
        log.info("Updating error configuration: generateErrors={}, errorRate={}, responseDelayMs={}",
                configDto.isGenerateErrors(), configDto.getErrorRate(), configDto.getResponseDelayMs());
        return ResponseEntity.ok(simulatorService.updateErrorConfig(configDto));
    }
    
    @PutMapping("/notification")
    public ResponseEntity<SimulatorConfig> updateNotificationConfig(@RequestBody NotificationConfigDto configDto) {
        return ResponseEntity.ok(simulatorService.updateNotificationConfig(configDto));
    }
}