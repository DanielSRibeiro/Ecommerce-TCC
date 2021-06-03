package com.example.agnciadeturismo.model;

import com.google.gson.annotations.SerializedName;

public class EstadoDto {

    @SerializedName("cd_estado")
    private int id;

    private String estado, uf;

    public EstadoDto(int id, String estado, String uf) {
        this.id = id;
        this.estado = estado;
        this.uf = uf;
    }

    public EstadoDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}