package com.onboarding.simulator.controller;

import com.onboarding.simulator.model.SimulatedOnboardingData;
import com.onboarding.simulator.service.SimulatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/simulator/control")
@RequiredArgsConstructor
@Slf4j
public class SimulatorControlController {
    private final SimulatorService simulatorService;
    
    @PostMapping("/start")
    public ResponseEntity<String> startSimulation() {
        simulatorService.startSimulation();
        return ResponseEntity.ok("Simulation started");
    }
    
    @PostMapping("/stop")
    public ResponseEntity<String> stopSimulation() {
        simulatorService.stopSimulation();
        return ResponseEntity.ok("Simulation stopped");
    }
    
    @PostMapping("/generate-single")
    public ResponseEntity<Map<String, String>> generateSingleCadastro() {
        SimulatedOnboardingData data = simulatorService.generateSingleCadastro();
        return ResponseEntity.ok(Map.of("hash", data.getHash()));
    }
}