package com.onboarding.simulator.repository.valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onboarding.simulator.model.valid.ValidOnboardingData;

@Repository
public interface ValidOnboardingDataRepository extends JpaRepository<ValidOnboardingData, String> {

}