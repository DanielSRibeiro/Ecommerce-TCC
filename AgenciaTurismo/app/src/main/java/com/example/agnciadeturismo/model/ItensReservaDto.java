package com.example.agnciadeturismo.model;

public class ItensReservaDto {

    private int cd;

    private int cdReserva;

    private int cdPacote;

    private String cpf;

    private double valorUnitario;

    private double valorTotal;

    private int quantidade;

    private String img;

    private String destino;

    private String nomePacote;

    private int codigoTransporte;

    public ItensReservaDto(){}

    public ItensReservaDto(int cd, int cdReserva, int cdPacote, String cpf, double valorUnitario, double valorTotal, int quantidade, String img, String destino, String nomePacote, int codigoTransporte) {
        this.cd = cd;
        this.cdReserva = cdReserva;
        this.cdPacote = cdPacote;
        this.cpf = cpf;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
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

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
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
