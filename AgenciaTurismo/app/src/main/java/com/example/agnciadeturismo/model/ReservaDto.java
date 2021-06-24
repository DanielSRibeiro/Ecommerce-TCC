package com.example.agnciadeturismo.model;

import com.google.gson.annotations.SerializedName;

public class ReservaDto {

    private int cd;

    private String cpf;

    private int cdCartao;

    private double valorTotal;

    @SerializedName("statusReserva")
    private int status;

    @SerializedName("dthrReserva")
    private int data;
}
