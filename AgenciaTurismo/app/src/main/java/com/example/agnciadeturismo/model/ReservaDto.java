package com.example.agnciadeturismo.model;

import com.google.gson.annotations.SerializedName;

public class ReservaDto {

    private int cd;

    private String cpf;

    private String nomeCartao;

    private int cdCartao;

    private String nomeCliente;
    private double valorTotal;

    @SerializedName("statusReserva")
    private int status;

    @SerializedName("dthrReserva")
    private String data;

    public ReservaDto(){}

    public ReservaDto(int cd, String cpf, String nomeCartao, int cdCartao, String nomeCliente, double valorTotal, int status, String data) {
        this.cd = cd;
        this.cpf = cpf;
        this.nomeCartao = nomeCartao;
        this.cdCartao = cdCartao;
        this.nomeCliente = nomeCliente;
        this.valorTotal = valorTotal;
        this.status = status;
        this.data = data;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public int getCdCartao() {
        return cdCartao;
    }

    public void setCdCartao(int cdCartao) {
        this.cdCartao = cdCartao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}