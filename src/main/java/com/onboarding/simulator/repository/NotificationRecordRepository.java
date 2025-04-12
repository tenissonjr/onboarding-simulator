package com.onboarding.simulator.repository;

import com.onboarding.simulator.model.NotificationRecord;
import com.onboarding.simulator.model.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRecordRepository extends JpaRepository<NotificationRecord, Long> {
    long countByStatus(NotificationStatus status);
}