package com.onboarding.simulator.model.valid;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ValidCapturesReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oid;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ValidCaptureItemReport> captureItemReport;

    public String getOid() {
        return oid;
    }
    public void setOid(String oid) {
        this.oid = oid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<ValidCaptureItemReport> getCaptureItemReport() {
        return captureItemReport;
    }
    public void setCaptureItemReport(List<ValidCaptureItemReport> captureItemReport) {
        this.captureItemReport = captureItemReport;
    }

    
}