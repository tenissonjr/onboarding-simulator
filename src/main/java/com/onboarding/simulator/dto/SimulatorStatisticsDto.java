package com.onboarding.simulator.dto;


public class SimulatorStatisticsDto {
    private long totalCadastrosGenerated;
    private long totalNotificationsSent;
    private long totalNotificationsFailed;
    private long activeCadastros;
    private long generationRate; // current per minute
    private boolean simulationRunning;
    public SimulatorStatisticsDto(long totalGeneratedCount, long countByStatus, long countByStatus2,
            long countActiveCadastros, int cadastrosPerMinute, boolean simulationRunning2) {
        this.totalCadastrosGenerated = totalGeneratedCount;
        this.totalNotificationsSent = countByStatus;
        this.totalNotificationsFailed = countByStatus2;
        this.activeCadastros = countActiveCadastros;
        this.generationRate = cadastrosPerMinute;
        this.simulationRunning = simulationRunning2;

    }
    public long getTotalCadastrosGenerated() {
        return totalCadastrosGenerated;
    }
    public void setTotalCadastrosGenerated(long totalCadastrosGenerated) {
        this.totalCadastrosGenerated = totalCadastrosGenerated;
    }
    public long getTotalNotificationsSent() {
        return totalNotificationsSent;
    }
    public void setTotalNotificationsSent(long totalNotificationsSent) {
        this.totalNotificationsSent = totalNotificationsSent;
    }
    public long getTotalNotificationsFailed() {
        return totalNotificationsFailed;
    }
    public void setTotalNotificationsFailed(long totalNotificationsFailed) {
        this.totalNotificationsFailed = totalNotificationsFailed;
    }
    public long getActiveCadastros() {
        return activeCadastros;
    }
    public void setActiveCadastros(long activeCadastros) {
        this.activeCadastros = activeCadastros;
    }
    public long getGenerationRate() {
        return generationRate;
    }
    public void setGenerationRate(long generationRate) {
        this.generationRate = generationRate;
    }
    public boolean isSimulationRunning() {
        return simulationRunning;
    }
    public void setSimulationRunning(boolean simulationRunning) {
        this.simulationRunning = simulationRunning;
    }

    
}