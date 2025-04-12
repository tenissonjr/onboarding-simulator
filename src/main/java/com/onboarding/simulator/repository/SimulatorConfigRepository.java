package com.onboarding.simulator.repository;

import com.onboarding.simulator.model.SimulatorConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulatorConfigRepository extends JpaRepository<SimulatorConfig, Long> {
}