package com.onboarding.simulator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulatorConfig {
    @Id
    private Long id = 1L; // Singleton configuration
    
    private int cadastrosPerMinute;
    
    @Enumerated(EnumType.STRING)
    private GenerationPattern pattern;
    
    private boolean generateErrors;
    private double errorRate; // 0.0 to 1.0
    private int responseDelayMs;
    private String notificationEndpoint;
    private int dataTtlMinutes; // Time to live for generated data
    private boolean simulationRunning;
}