package com.onboarding.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationConfigDto {
    private String notificationEndpoint;
    private int dataTtlMinutes;
}