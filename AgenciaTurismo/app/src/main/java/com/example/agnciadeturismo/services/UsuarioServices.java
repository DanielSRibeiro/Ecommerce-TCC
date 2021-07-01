package com.example.agnciadeturismo.services;

import com.example.agnciadeturismo.model.ClienteDto;


public class UsuarioServices {
    static ClienteDto cliente = new ClienteDto(null, null, null, null, null, null, null, null);

    public static ClienteDto setUsuario(ClienteDto novoCliente){
        return cliente = novoCliente;
    }

    public static ClienteDto getUsuario(){
        return cliente;
    }

    public static void clearUsuario(){
        cliente = new ClienteDto(null, null, null, null, null, null, null, null);
    }
}
