package com.onboarding.simulator.dto;

import java.time.LocalDate;

public class OnboardingDataResponseDto {
    private String hash;
    private String cpf;
    private String nome;
    private String nomeSocial;
    private LocalDate dataNascimento;
    private String nomeMae;
    private DocumentoDto documento;
    private String fotoUsuario;
    private String imagemDocumentoFrente;
    private String imagemDocumentoVerso;

    public OnboardingDataResponseDto(String hash, String cpf, String nome, String nomeSocial, LocalDate dataNascimento,
            String nomeMae, DocumentoDto documentoDto, String fotoUsuario, String imagemDocumentoFrente,
            String imagemDocumentoVerso) {
        this.hash = hash;
        this.cpf = cpf;
        this.nome = nome;
        this.nomeSocial = nomeSocial;
        this.dataNascimento = dataNascimento;
        this.nomeMae = nomeMae;
        this.documento = documentoDto;
        this.fotoUsuario = fotoUsuario;
        this.imagemDocumentoFrente = imagemDocumentoFrente;
        this.imagemDocumentoVerso = imagemDocumentoVerso;
    }

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

    public DocumentoDto getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoDto documento) {
        this.documento = documento;
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

    public static class DocumentoDto {
        private String numero;
        private String paisOrigem;
        private String orgaoEmissor;
        private String uf;
        private LocalDate dataExpedicao;
        private LocalDate dataVencimento;

        public DocumentoDto(String numeroDocumento, String paisOrigem, String orgaoEmissor, String uf,
                LocalDate dataExpedicao, LocalDate dataVencimento) {
            this.numero = numeroDocumento;
            this.paisOrigem = paisOrigem;
            this.orgaoEmissor = orgaoEmissor;
            this.uf = uf;
            this.dataExpedicao = dataExpedicao;
            this.dataVencimento = dataVencimento;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
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

    }
}