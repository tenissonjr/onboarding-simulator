package com.onboarding.simulator.dto;

public class ErrorConfigDto {
    private boolean generateErrors;
    private double errorRate;
    private int responseDelayMs;
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


    
}