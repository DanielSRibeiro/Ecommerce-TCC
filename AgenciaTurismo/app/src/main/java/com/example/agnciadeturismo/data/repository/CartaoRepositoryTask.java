package com.example.agnciadeturismo.data.repository;

import android.util.Log;

import com.example.agnciadeturismo.data.api.RetrofitTask;
import com.example.agnciadeturismo.model.CartaoDto;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class CartaoRepositoryTask {

    private static final String TAG = "CartaoRepositoryTask";
    boolean cadastrado = false;
    boolean excluido = false;
    ArrayList<CartaoDto> cartao;

    public ArrayList<CartaoDto> getAllCartoes(String cpf){
        Call<ArrayList<CartaoDto>> api = RetrofitTask.getRetrofit().consultarCartao(cpf);

        try {
            Response<ArrayList<CartaoDto>> response = api.execute();
            if(response.isSuccessful()){
                cartao = response.body();
                Log.d(TAG, "Quantidade na Lista: "+response.body().size());
            }else{
                Log.d(TAG, "ERRO: "+response.code());
            }

        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }

        return cartao;
    }

    public boolean inserirCartao(String cpf, String nomeCartao, String nomeImpresso, String numero, String cvv, String data){
        Call<Boolean> api = RetrofitTask.getRetrofit().cadastrarCartao(cpf, nomeCartao, nomeImpresso,
                numero, cvv, data);

        try {
            Response<Boolean> response = api.execute();

            if(response.isSuccessful()){
                if(response.body() == true){
                    cadastrado = true;
                }
            }else{
                Log.d(TAG, "ERRO: "+response.code());
                cadastrado = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cadastrado;
    }

    public boolean deletarCartao(String cpf, int codigo){
        Call<Boolean> api = RetrofitTask.getRetrofit().deletarCartao(cpf, codigo);
        try {
            Response<Boolean> response = api.execute();
            if(response.isSuccessful()){
                if (response.body() == true){
                    excluido = true;
                }else{
                    excluido = false;
                }
            }else{
                Log.d(TAG, "ERRO: "+response.code());
            }
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
        return excluido;
    }
}
