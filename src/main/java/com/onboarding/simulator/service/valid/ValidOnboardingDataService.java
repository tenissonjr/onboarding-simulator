package com.onboarding.simulator.service.valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.onboarding.simulator.model.valid.ValidOnboardingData;
import com.onboarding.simulator.repository.valid.ValidOnboardingDataRepository;

import jakarta.persistence.criteria.Predicate;



@Service
public class ValidOnboardingDataService {

    private final ValidOnboardingDataRepository validOnboardingDataRepository;

    public ValidOnboardingDataService(ValidOnboardingDataRepository validOnboardingDataRepository) {
        this.validOnboardingDataRepository = validOnboardingDataRepository;
    }

    public List<ValidOnboardingData> getAllValidOnboardingData() {
        return validOnboardingDataRepository.findAll();
    }


	public Specification<ValidOnboardingData> createSpecification(LocalDateTime startDate, LocalDateTime endDate){
		
		return (root, criteriaQuery ,criteriaBuilder)->{
			
			Predicate filters = criteriaBuilder.conjunction();
			
            filters = criteriaBuilder.and(filters, criteriaBuilder.greaterThanOrEqualTo(root.get("dateStarted"), startDate));
            filters = criteriaBuilder.and(filters, criteriaBuilder.lessThanOrEqualTo(root.get("dateStarted"), endDate));

            if (criteriaQuery != null) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("dateStarted")));
            }
            return filters;
        };		
    }

    public List<ValidOnboardingData> list(LocalDateTime startDate, LocalDateTime endDate, int page, int size) {
        Page<ValidOnboardingData> validOnboardingDataPage = validOnboardingDataRepository
                .findAll(createSpecification(startDate, endDate), PageRequest.of(page, size));
        return  validOnboardingDataPage.getContent();
    }

    public Optional<ValidOnboardingData> findByIdentifier(String hash) {
        return validOnboardingDataRepository.findByIdentifier(hash);
    }



}
