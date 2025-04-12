package com.onboarding.simulator.model;

public enum NotificationStatus {
    PENDING,    // Notification is created but not sent yet
    SENT,       // Notification has been sent to target endpoint
    FAILED,     // Notification failed to send
    RECEIVED    // Received confirmation from target endpoint
}