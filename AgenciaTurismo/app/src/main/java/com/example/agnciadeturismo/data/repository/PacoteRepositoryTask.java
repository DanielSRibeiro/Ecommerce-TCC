package com.example.agnciadeturismo.data.repository;

import android.util.Log;

import com.example.agnciadeturismo.data.api.RetrofitTask;
import com.example.agnciadeturismo.model.PacoteDto;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class PacoteRepositoryTask {

    private static final String TAG = "PacoteRepositoryTask";
    ArrayList<PacoteDto> pacote;
    ArrayList<PacoteDto> oferta;

    public ArrayList<PacoteDto> getAllOfertas(){
        Call<ArrayList<PacoteDto>> api = RetrofitTask.getRetrofit().consultarOferta();

        try {
            Response<ArrayList<PacoteDto>> response = api.execute();
            if(response.isSuccessful()){
                oferta = response.body();
                Log.d(TAG, "Quantidade na Lista: "+response.body().size());
            }else{
                Log.d(TAG, "ERRO: "+response.code());
            }

        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }

        return oferta;
    }

    public ArrayList<PacoteDto> getAllPacotes(int origem, int destino, int tipo){
        Call<ArrayList<PacoteDto>> api = RetrofitTask.getRetrofit().buscarPacotes(origem, destino, tipo);

        try {
            Response<ArrayList<PacoteDto>> response = api.execute();
            if(response.isSuccessful()){
                pacote = response.body();
                Log.d(TAG, "Quantidade na Lista de Pacotes: "+response.body().size());
            }else{
                Log.d(TAG, "ERRO: "+response.code());
            }

        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }

        return pacote;
    }
}
