package com.onboarding.simulator.controller;

import com.onboarding.simulator.dto.ErrorConfigDto;
import com.onboarding.simulator.dto.GenerationRateConfigDto;
import com.onboarding.simulator.dto.NotificationConfigDto;
import com.onboarding.simulator.model.SimulatorConfig;
import com.onboarding.simulator.service.SimulatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulator/config")
@RequiredArgsConstructor
@Slf4j
public class SimulatorConfigController {
    private final SimulatorService simulatorService;
    
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
        log.info("Updating notification configuration: endpoint={}, dataTtlMinutes={}",
                configDto.getNotificationEndpoint(), configDto.getDataTtlMinutes());
        return ResponseEntity.ok(simulatorService.updateNotificationConfig(configDto));
    }
}