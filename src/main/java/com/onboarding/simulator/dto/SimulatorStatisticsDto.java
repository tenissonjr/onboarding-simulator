package com.onboarding.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulatorStatisticsDto {
    private long totalCadastrosGenerated;
    private long totalNotificationsSent;
    private long totalNotificationsFailed;
    private long activeCadastros;
    private long generationRate; // current per minute
    private boolean simulationRunning;
}