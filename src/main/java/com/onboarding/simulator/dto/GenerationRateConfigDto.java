package com.onboarding.simulator.dto;

import com.onboarding.simulator.model.GenerationPattern;


public class GenerationRateConfigDto {
    private int cadastrosPerMinute;
    private GenerationPattern pattern;
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

    

}