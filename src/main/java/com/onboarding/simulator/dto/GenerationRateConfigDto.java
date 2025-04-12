package com.onboarding.simulator.dto;

import com.onboarding.simulator.model.GenerationPattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerationRateConfigDto {
    private int cadastrosPerMinute;
    private GenerationPattern pattern;
}