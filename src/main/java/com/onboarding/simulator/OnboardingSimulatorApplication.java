package com.onboarding.simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnboardingSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnboardingSimulatorApplication.class, args);
    }
}