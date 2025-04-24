package com.onboarding.simulator.repository;

import com.onboarding.simulator.model.ValidOnboardingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidOnboardingDataRepository extends JpaRepository<ValidOnboardingData, String> {

}