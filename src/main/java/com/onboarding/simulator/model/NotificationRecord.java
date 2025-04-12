package com.onboarding.simulator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String hash;
    private LocalDateTime notificationTime;
    private String targetEndpoint;
    
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;
    
    private String errorMessage;
}