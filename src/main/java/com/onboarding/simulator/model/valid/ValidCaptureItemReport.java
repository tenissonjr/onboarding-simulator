package com.onboarding.simulator.model.valid;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class ValidCaptureItemReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String oid;
    private String time;
    private String type;
    private String url;
    private String url_2;

    
    //private Object captureFormItens;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ValidLinessesTwoDimensionResult linessesTwoDimensionResult;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ValidOcrDocumentReport ocrDocumentReport;
    
    @Lob
    private String base64;

    @Lob
   private String base64_2;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }
    public void setOid(String oid) {
        this.oid = oid;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl_2() {
        return url_2;
    }
    public void setUrl_2(String url_2) {
        this.url_2 = url_2;
    }
    public ValidLinessesTwoDimensionResult getLinessesTwoDimensionResult() {
        return linessesTwoDimensionResult;
    }
    public void setLinessesTwoDimensionResult(ValidLinessesTwoDimensionResult linessesTwoDimensionResult) {
        this.linessesTwoDimensionResult = linessesTwoDimensionResult;
    }
    public ValidOcrDocumentReport getOcrDocumentReport() {
        return ocrDocumentReport;
    }
    public void setOcrDocumentReport(ValidOcrDocumentReport ocrDocumentReport) {
        this.ocrDocumentReport = ocrDocumentReport;
    }
    public String getBase64() {
        return base64;
    }
    public void setBase64(String base64) {
        this.base64 = base64;
    }
    public String getBase64_2() {
        return base64_2;
    }
    public void setBase64_2(String base64_2) {
        this.base64_2 = base64_2;
    }

    
}