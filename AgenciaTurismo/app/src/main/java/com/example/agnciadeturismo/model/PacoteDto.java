package com.example.agnciadeturismo.model;

import com.google.gson.annotations.SerializedName;

public class PacoteDto {

    @SerializedName("cd")
    private int cd;

    @SerializedName("cdViagem")
    private int cdViagem;

    @SerializedName("cdHotel")
    private int cdHotel;

    @SerializedName("cdCategoria")
    private int cdCategoria;

    @SerializedName("cdTipoTransporte")
    private int cdTipoTranporte;

    @SerializedName("cdOrigem")
    private int cdOrigem;

    @SerializedName("cdDestino")
    private int cdDestino;

    @SerializedName("nomePacote")
    private String nomePacote;

    @SerializedName("descricao")
    private String descricaoPacote;

    @SerializedName("checkin")
    private String dtCheckin;

    @SerializedName("checkout")
    private String dtCheckout;

    @SerializedName("img")
    private String img;

    @SerializedName("valor")
    private String vlPacote;

    public PacoteDto(int cd, int cdViagem, int cdHotel, int cdCategoria, int cdTipoTranporte, int cdOrigem, int cdDestino, String nomePacote, String descricaoPacote, String dtCheckin, String dtCheckout, String img, String vlPacote) {
        this.cd = cd;
        this.cdViagem = cdViagem;
        this.cdHotel = cdHotel;
        this.cdCategoria = cdCategoria;
        this.cdTipoTranporte = cdTipoTranporte;
        this.cdOrigem = cdOrigem;
        this.cdDestino = cdDestino;
        this.nomePacote = nomePacote;
        this.descricaoPacote = descricaoPacote;
        this.dtCheckin = dtCheckin;
        this.dtCheckout = dtCheckout;
        this.img = img;
        this.vlPacote = vlPacote;
    }

    public PacoteDto(){}

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getCdViagem() {
        return cdViagem;
    }

    public void setCdViagem(int cdViagem) {
        this.cdViagem = cdViagem;
    }

    public int getCdHotel() {
        return cdHotel;
    }

    public void setCdHotel(int cdHotel) {
        this.cdHotel = cdHotel;
    }

    public int getCdCategoria() {
        return cdCategoria;
    }

    public void setCdCategoria(int cdCategoria) {
        this.cdCategoria = cdCategoria;
    }

    public int getCdTipoTranporte() {
        return cdTipoTranporte;
    }

    public void setCdTipoTranporte(int cdTipoTranporte) {
        this.cdTipoTranporte = cdTipoTranporte;
    }

    public int getCdOrigem() {
        return cdOrigem;
    }

    public void setCdOrigem(int cdOrigem) {
        this.cdOrigem = cdOrigem;
    }

    public int getCdDestino() {
        return cdDestino;
    }

    public void setCdDestino(int cdDestino) {
        this.cdDestino = cdDestino;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }

    public String getDescricaoPacote() {
        return descricaoPacote;
    }

    public void setDescricaoPacote(String descricaoPacote) {
        this.descricaoPacote = descricaoPacote;
    }

    public String getDtCheckin() {
        return dtCheckin;
    }

    public void setDtCheckin(String dtCheckin) {
        this.dtCheckin = dtCheckin;
    }

    public String getDtCheckout() {
        return dtCheckout;
    }

    public void setDtCheckout(String dtCheckout) {
        this.dtCheckout = dtCheckout;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVlPacote() {
        return vlPacote;
    }

    public void setVlPacote(String vlPacote) {
        this.vlPacote = vlPacote;
    }
}