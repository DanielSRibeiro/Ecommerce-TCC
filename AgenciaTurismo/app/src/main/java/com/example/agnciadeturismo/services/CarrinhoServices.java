package com.example.agnciadeturismo.services;

import com.example.agnciadeturismo.model.CarrinhoDto;

import java.util.ArrayList;

public class CarrinhoServices {
    static ArrayList<CarrinhoDto> listCarrinho = new ArrayList<>();

    public static void setListCarrinho(CarrinhoDto itensReserva) {
        listCarrinho.add(itensReserva);
    }

    public static ArrayList<CarrinhoDto> getListCarrinho() {
        return listCarrinho;
    }

    public static void clearListCarrinho(){
        listCarrinho.clear();
    }
}
