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

    @SerializedName("valorTotal")
    private String valor;

    private int quantidade;

    private String img;

    private String destino;

    private String nomePacote;

    private int tipoTransport;

    public CarrinhoDto(){}

    public CarrinhoDto(int cd, int cdReserva, int cdPacote, String cpf, String valor, int quantidade, String img, String destino, String nomePacote, int tipoTransport) {
        this.cd = cd;
        this.cdReserva = cdReserva;
        this.cdPacote = cdPacote;
        this.cpf = cpf;
        this.valor = valor;
        this.quantidade = quantidade;
        this.img = img;
        this.destino = destino;
        this.nomePacote = nomePacote;
        this.tipoTransport = tipoTransport;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getCdPacote() {
        return cdPacote;
    }

    public void setCdPacote(int cdPacote) {
        this.cdPacote = cdPacote;
    }

    public int getCdReserva() {
        return cdReserva;
    }

    public void setCdReserva(int cdReserva) {
        this.cdReserva = cdReserva;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
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

    public int getTipoTransport() {
        return tipoTransport;
    }

    public void setTipoTransport(int tipoTransport) {
        this.tipoTransport = tipoTransport;
    }
}