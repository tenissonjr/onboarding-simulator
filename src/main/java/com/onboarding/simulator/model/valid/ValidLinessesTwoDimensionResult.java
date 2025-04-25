package com.onboarding.simulator.model.valid;

import jakarta.persistence.*;

@Entity
public class ValidLinessesTwoDimensionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;

    private boolean success;
    private String result;
    private boolean spoof;
    private int score;
    private String integrationType;
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public boolean isSpoof() {
        return spoof;
    }
    public void setSpoof(boolean spoof) {
        this.spoof = spoof;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getIntegrationType() {
        return integrationType;
    }
    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    
}