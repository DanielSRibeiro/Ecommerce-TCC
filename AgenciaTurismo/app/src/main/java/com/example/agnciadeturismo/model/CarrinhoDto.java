package com.example.agnciadeturismo.model;

import com.google.gson.annotations.SerializedName;

public class CarrinhoDto {

    @SerializedName("cd")
    private int cd;

    @SerializedName("cdReserva")
    private int cdReserva;

    @SerializedName("cdPacote")
    private int cdPacote;

    private String cpf;

    @SerializedName("valorUnitario")
    private double valorUnitario;

    @SerializedName("valorTotal")
    private double valor;

    private int quantidade;

    private String img;

    private String destino;

    private String nomePacote;

    @SerializedName("codigoTransporte")
    private int codigoTransporte;

    public CarrinhoDto(){}

    public CarrinhoDto(int cd, int cdReserva, int cdPacote, String cpf, double valorUnitario, double valor, int quantidade, String img, String destino, String nomePacote, int codigoTransporte) {
        this.cd = cd;
        this.cdReserva = cdReserva;
        this.cdPacote = cdPacote;
        this.cpf = cpf;
        this.valorUnitario = valorUnitario;
        this.valor = valor;
        this.quantidade = quantidade;
        this.img = img;
        this.destino = destino;
        this.nomePacote = nomePacote;
        this.codigoTransporte = codigoTransporte;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getCdReserva() {
        return cdReserva;
    }

    public void setCdReserva(int cdReserva) {
        this.cdReserva = cdReserva;
    }

    public int getCdPacote() {
        return cdPacote;
    }

    public void setCdPacote(int cdPacote) {
        this.cdPacote = cdPacote;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }

    public int getCodigoTransporte() {
        return codigoTransporte;
    }

    public void setCodigoTransporte(int codigoTransporte) {
        this.codigoTransporte = codigoTransporte;
    }
}