package com.example.agnciadeturismo.model;

public class CartaoDto {

    private int cd;
    
    private String CPF, nomeCartao, nomeImpresso,
            numeroCartao, cvv, validadeCartao;

    public CartaoDto(String CPF, String nomeCartao, String nomeImpresso, String numeroCartao, String cvv, String validadeCartao) {
        this.CPF = CPF;
        this.nomeCartao = nomeCartao;
        this.nomeImpresso = nomeImpresso;
        this.numeroCartao = numeroCartao;
        this.cvv = cvv;
        this.validadeCartao = validadeCartao;
    }

    public CartaoDto(){};

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getValidadeCartao() {
        return validadeCartao;
    }

    public void setValidadeCartao(String validadeCartao) {
        this.validadeCartao = validadeCartao;
    }
}
