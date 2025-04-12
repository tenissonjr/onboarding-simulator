package com.onboarding.simulator.repository;

import com.onboarding.simulator.model.SimulatedOnboardingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SimulatedOnboardingDataRepository extends JpaRepository<SimulatedOnboardingData, String> {
    @Query("SELECT COUNT(s) FROM SimulatedOnboardingData s WHERE s.expiresAt > :now")
    long countActiveCadastros(LocalDateTime now);
    
    List<SimulatedOnboardingData> findByExpiresAtBefore(LocalDateTime expiryTime);
}