package com.onboarding.simulator.repository.valid;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.onboarding.simulator.model.valid.ValidOnboardingData;

@Repository
public interface ValidOnboardingDataRepository extends JpaRepository<ValidOnboardingData, Long>, JpaSpecificationExecutor<ValidOnboardingData> {

    Optional<ValidOnboardingData> findByIdentifier(String hash);


}