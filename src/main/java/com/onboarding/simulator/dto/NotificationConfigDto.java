package com.onboarding.simulator.dto;

public class NotificationConfigDto {
    private String notificationEndpoint;
    private int dataTtlMinutes;
    public String getNotificationEndpoint() {
        return notificationEndpoint;
    }
    public void setNotificationEndpoint(String notificationEndpoint) {
        this.notificationEndpoint = notificationEndpoint;
    }
    public int getDataTtlMinutes() {
        return dataTtlMinutes;
    }
    public void setDataTtlMinutes(int dataTtlMinutes) {
        this.dataTtlMinutes = dataTtlMinutes;
    }

    
}