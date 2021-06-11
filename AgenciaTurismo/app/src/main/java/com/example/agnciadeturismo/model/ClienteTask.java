package com.example.agnciadeturismo.model;

public class ClienteTask {

    private static ClienteDto clienteLogado;

    public static ClienteDto clienteLogado(ClienteDto clienteDto){
        if(clienteLogado == null){
            clienteLogado = clienteDto;
        }
        return clienteLogado;
    }
}
