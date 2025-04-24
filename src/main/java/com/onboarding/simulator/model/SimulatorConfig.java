package com.onboarding.simulator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
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
    private boolean simulationRunning;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCadastrosPerMinute() {
        return cadastrosPerMinute;
    }
    public void setCadastrosPerMinute(int cadastrosPerMinute) {
        this.cadastrosPerMinute = cadastrosPerMinute;
    }
    public GenerationPattern getPattern() {
        return pattern;
    }
    public void setPattern(GenerationPattern pattern) {
        this.pattern = pattern;
    }
    public boolean isGenerateErrors() {
        return generateErrors;
    }
    public void setGenerateErrors(boolean generateErrors) {
        this.generateErrors = generateErrors;
    }
    public double getErrorRate() {
        return errorRate;
    }
    public void setErrorRate(double errorRate) {
        this.errorRate = errorRate;
    }
    public int getResponseDelayMs() {
        return responseDelayMs;
    }
    public void setResponseDelayMs(int responseDelayMs) {
        this.responseDelayMs = responseDelayMs;
    }
    public String getNotificationEndpoint() {
        return notificationEndpoint;
    }
    public void setNotificationEndpoint(String notificationEndpoint) {
        this.notificationEndpoint = notificationEndpoint;
    }
    public boolean isSimulationRunning() {
        return simulationRunning;
    }
    public void setSimulationRunning(boolean simulationRunning) {
        this.simulationRunning = simulationRunning;
    }

    
}