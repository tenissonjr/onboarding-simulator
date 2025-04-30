package com.onboarding.simulator.model.valid;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class ValidOnboardingData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyId;
    private String scheduleConfigurationId;
    private String name;
    private String identifier;
    private String employee;
    private String emailClient;
    private String phoneNumber;
    private String scheduleType;
    private String scheduleStatus;
    private String scheduleTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dateStarted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dateCompleted;
    
    private String dateDuration;
    private String capturePlatform;
    private boolean externalInitialize;
    @ElementCollection
    private List<String> chat;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValidCapturesReport> capturesReport;

    private String certificationFacematchRecord;
    private String relationshipScheduleConfigurationId;
    private String clientGeolocationLatitude;
    private String clientGeolocationLongitude;
    private String crSummaryResponse;
    
  @PrePersist
    private void generateIdentifier() {
        if (this.identifier == null || this.identifier.isEmpty()) {
            this.identifier = UUID.randomUUID().toString();
        }
    }


    public String getCompanyId() {
        return companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getScheduleConfigurationId() {
        return scheduleConfigurationId;
    }
    public void setScheduleConfigurationId(String scheduleConfigurationId) {
        this.scheduleConfigurationId = scheduleConfigurationId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getEmployee() {
        return employee;
    }
    public void setEmployee(String employee) {
        this.employee = employee;
    }
    public String getEmailClient() {
        return emailClient;
    }
    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getScheduleType() {
        return scheduleType;
    }
    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }
    public String getScheduleStatus() {
        return scheduleStatus;
    }
    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }
    public String getScheduleTime() {
        return scheduleTime;
    }
    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public LocalDateTime getDateStarted() {
        return dateStarted;
    }
    public void setDateStarted(LocalDateTime dateStarted) {
        this.dateStarted = dateStarted;
    }
    public LocalDateTime getDateCompleted() {
        return dateCompleted;
    }
    public void setDateCompleted(LocalDateTime dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
    public String getDateDuration() {
        return dateDuration;
    }
    public void setDateDuration(String dateDuration) {
        this.dateDuration = dateDuration;
    }
    public String getCapturePlatform() {
        return capturePlatform;
    }
    public void setCapturePlatform(String capturePlatform) {
        this.capturePlatform = capturePlatform;
    }
    public boolean isExternalInitialize() {
        return externalInitialize;
    }
    public void setExternalInitialize(boolean externalInitialize) {
        this.externalInitialize = externalInitialize;
    }
    public List<String> getChat() {
        return chat;
    }
    public void setChat(List<String> chat) {
        this.chat = chat;
    }
    public List<ValidCapturesReport> getCapturesReport() {
        return capturesReport;
    }
    public void setCapturesReport(List<ValidCapturesReport> capturesReport) {
        this.capturesReport = capturesReport;
    }
    public String getCertificationFacematchRecord() {
        return certificationFacematchRecord;
    }
    public void setCertificationFacematchRecord(String certificationFacematchRecord) {
        this.certificationFacematchRecord = certificationFacematchRecord;
    }
    public String getRelationshipScheduleConfigurationId() {
        return relationshipScheduleConfigurationId;
    }
    public void setRelationshipScheduleConfigurationId(String relationshipScheduleConfigurationId) {
        this.relationshipScheduleConfigurationId = relationshipScheduleConfigurationId;
    }
    public String getClientGeolocationLatitude() {
        return clientGeolocationLatitude;
    }
    public void setClientGeolocationLatitude(String clientGeolocationLatitude) {
        this.clientGeolocationLatitude = clientGeolocationLatitude;
    }
    public String getClientGeolocationLongitude() {
        return clientGeolocationLongitude;
    }
    public void setClientGeolocationLongitude(String clientGeolocationLongitude) {
        this.clientGeolocationLongitude = clientGeolocationLongitude;
    }
    public String getCrSummaryResponse() {
        return crSummaryResponse;
    }
    public void setCrSummaryResponse(String crSummaryResponse) {
        this.crSummaryResponse = crSummaryResponse;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    
}