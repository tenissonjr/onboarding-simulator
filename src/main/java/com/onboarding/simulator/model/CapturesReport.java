package com.onboarding.simulator.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class CapturesReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oid;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaptureItemReport> captureItemReport;

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
    public List<CaptureItemReport> getCaptureItemReport() {
        return captureItemReport;
    }
    public void setCaptureItemReport(List<CaptureItemReport> captureItemReport) {
        this.captureItemReport = captureItemReport;
    }

    
}