package com.onboarding.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorConfigDto {
    private boolean generateErrors;
    private double errorRate;
    private int responseDelayMs;
}