package com.onboarding.simulator.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onboarding.simulator.model.valid.ValidOnboardingData;
import com.onboarding.simulator.service.SimulatorService;

@RestController
@RequestMapping("/api/simulator/control")
public class SimulatorControlController {
    private final SimulatorService simulatorService;

    public SimulatorControlController(SimulatorService simulatorService) {
        this.simulatorService = simulatorService;
    }
    
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
        ValidOnboardingData data = simulatorService.generateSingleCadastro();
        return ResponseEntity.ok(Map.of("hash", data.getIdentifier()));
    }
}