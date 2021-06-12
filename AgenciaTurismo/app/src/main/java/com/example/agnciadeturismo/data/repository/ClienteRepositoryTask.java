package com.example.agnciadeturismo.data.repository;

import android.util.Log;

import com.example.agnciadeturismo.data.api.HorizonFlyApi;
import com.example.agnciadeturismo.data.api.RetrofitTask;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ClienteRepositoryTask {

    private static final String TAG = "RepositoryTask";
    public boolean resultado;

    public boolean inserirCliente(String nome, String email, String cpf, String rg, String telefone, String senha){
        Call<Boolean> api = RetrofitTask.instanceRetrofit()
                .create(HorizonFlyApi.class).cadastrarCliente(nome, email, cpf, rg, telefone, senha);

        try {
            Response<Boolean> response = api.execute();
            if(response.isSuccessful()){
                if(response.body()){
                    resultado = true;
                } else{
                    resultado = false;
                }
            } else{
                Log.d(TAG, response.code()+"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public boolean alterarCliente(String nome, String email, String cpf, String rg, String telefone, String senha, String img){
        Call<Boolean> api = RetrofitTask.instanceRetrofit()
                .create(HorizonFlyApi.class).updateCliente(nome, email, cpf, rg, telefone, senha, img);

        try {
            Response<Boolean> response = api.execute();
            if(response.isSuccessful()){
                if(response.body()){
                    resultado = true;
                } else{
                    resultado = false;
                }
            } else{
                Log.d(TAG, response.code()+"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;
    }
}