package com.onboarding.simulator.model;

public enum GenerationPattern {
    CONSTANT,    // Generates at a constant rate throughout the minute
    BURST,       // Generates all records at once at the start of each minute
    INCREASING,  // Gradually increases generation rate throughout the minute
    RANDOM       // Randomly distributes generation throughout the minute
}