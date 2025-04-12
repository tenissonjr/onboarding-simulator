package com.onboarding.simulator.controller;

import com.onboarding.simulator.dto.SimulatorStatisticsDto;
import com.onboarding.simulator.service.SimulatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/simulator/stats")
@RequiredArgsConstructor
public class SimulatorStatsController {
    private final SimulatorService simulatorService;
    
    @GetMapping
    public ResponseEntity<SimulatorStatisticsDto> getSimulationStatistics() {
        return ResponseEntity.ok(simulatorService.getSimulationStatistics());
    }
}