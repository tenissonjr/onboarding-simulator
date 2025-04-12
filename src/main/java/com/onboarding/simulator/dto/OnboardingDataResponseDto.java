package com.onboarding.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingDataResponseDto {
    private String cpf;
    private String nome;
    private String nomeSocial;
    private LocalDate dataNascimento;
    private String nomeMae;
    private DocumentoDto documento;
    private String fotoUsuario;
    private String imagemDocumentoFrente;
    private String imagemDocumentoVerso;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DocumentoDto {
        private String numero;
        private String paisOrigem;
        private String orgaoEmissor;
        private String uf;
        private LocalDate dataExpedicao;
        private LocalDate dataVencimento;
    }
}