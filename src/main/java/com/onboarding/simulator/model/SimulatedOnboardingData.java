package com.onboarding.simulator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity

public class SimulatedOnboardingData {
    @Id
    private String hash;
    
    private String cpf;
    private String nome;
    private String nomeSocial;
    private LocalDate dataNascimento;
    private String nomeMae;
    private String numeroDocumento;
    private String paisOrigem;
    private String orgaoEmissor;
    private String uf;
    private LocalDate dataExpedicao;
    private LocalDate dataVencimento;
    
    @Lob
    @Column(length = 100000)
    private String fotoUsuario; // Base64 encoded image
    
    @Lob
    @Column(length = 100000)
    private String imagemDocumentoFrente; // Base64 encoded image
    
    @Lob
    @Column(length = 100000)
    private String imagemDocumentoVerso; // Base64 encoded image
    
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNomeSocial() {
        return nomeSocial;
    }
    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getNomeMae() {
        return nomeMae;
    }
    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
    public String getNumeroDocumento() {
        return numeroDocumento;
    }
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
    public String getPaisOrigem() {
        return paisOrigem;
    }
    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }
    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }
    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public LocalDate getDataExpedicao() {
        return dataExpedicao;
    }
    public void setDataExpedicao(LocalDate dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }
    public LocalDate getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public String getFotoUsuario() {
        return fotoUsuario;
    }
    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
    public String getImagemDocumentoFrente() {
        return imagemDocumentoFrente;
    }
    public void setImagemDocumentoFrente(String imagemDocumentoFrente) {
        this.imagemDocumentoFrente = imagemDocumentoFrente;
    }
    public String getImagemDocumentoVerso() {
        return imagemDocumentoVerso;
    }
    public void setImagemDocumentoVerso(String imagemDocumentoVerso) {
        this.imagemDocumentoVerso = imagemDocumentoVerso;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    
}