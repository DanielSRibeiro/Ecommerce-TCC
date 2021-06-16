package com.example.agnciadeturismo.model;

import com.google.gson.annotations.SerializedName;

public class CidadeDto {

    @SerializedName("cd")
    private int cd;

    @SerializedName("cdEstado")
    private int cdEstado;

    @SerializedName("cidade")
    private String cidade;

    public CidadeDto(int cd, int cdEstado, String cidade) {
        this.cd = cd;
        this.cdEstado = cdEstado;
        this.cidade = cidade;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public int getCdEstado() {
        return cdEstado;
    }

    public void setCdEstado(int cdEstado) {
        this.cdEstado = cdEstado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
