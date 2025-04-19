package com.onboarding.simulator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onboarding.simulator.dto.SimulatorStatisticsDto;
import com.onboarding.simulator.service.SimulatorService;

@RestController
@RequestMapping("/api/simulator/stats")
public class SimulatorStatsController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SimulatorStatsController.class);

    private final SimulatorService simulatorService;

    public SimulatorStatsController(SimulatorService simulatorService) {
        this.simulatorService = simulatorService;
    }
    
    @GetMapping
    public ResponseEntity<SimulatorStatisticsDto> getSimulationStatistics() {
        return ResponseEntity.ok(simulatorService.getSimulationStatistics());
    }
}