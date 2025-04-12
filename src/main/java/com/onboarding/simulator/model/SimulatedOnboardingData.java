package com.onboarding.simulator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}