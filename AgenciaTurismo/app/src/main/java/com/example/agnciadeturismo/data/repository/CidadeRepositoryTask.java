package com.example.agnciadeturismo.data.repository;

import android.util.Log;

import com.example.agnciadeturismo.data.api.RetrofitTask;
import com.example.agnciadeturismo.model.CidadeDto;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class CidadeRepositoryTask {

    private static final String TAG = "CidadeRepositoryTask";
    private static ArrayList<String> listCidade = new ArrayList<>();
    private static Integer cdOrigem;
    private static Integer cdDestino;

    public static ArrayList<String> getAllCidades(){

        Call<ArrayList<CidadeDto>> api = RetrofitTask.getRetrofit().getAllCidades();
        try {
            Response<ArrayList<CidadeDto>> response = api.execute();
            if(response.isSuccessful()){
                for(CidadeDto cidade : response.body()){
                    Log.d(TAG, "Quantidade");
                    listCidade.add(cidade.getCidade());
                    Log.d(TAG, "Quantidade");
                }
                Log.d(TAG, "Quantidade: "+listCidade.size());
            }else{
                Log.d(TAG, "ERRO: "+response.code());
            }
        } catch (IOException e) {
            Log.d(TAG, "ERRO: "+e.getMessage());
        }

        return listCidade;
    }

    public static Integer getOrigemCidade(String origem){
        Call<ArrayList<CidadeDto>> api = RetrofitTask.getRetrofit().getNomeCidade(origem);
        try {
            Response<ArrayList<CidadeDto>> response = api.execute();
            if(response.isSuccessful()){
                ArrayList<CidadeDto> listCidade = response.body();
                if(listCidade.size() != 0){
                    cdOrigem = listCidade.get(0).getCd();
                }else{
                    cdOrigem = null;
                    Log.d(TAG, "Não encontrado a cidade origem");
                }

            }else{
                Log.d(TAG, "ERRO: "+response.code());
            }
        } catch (IOException e) {
            Log.d(TAG, "ERRO: "+e.getMessage());
        }

        return cdOrigem;
    }

    public static Integer getDestinoCidade(String nome){

        Call<ArrayList<CidadeDto>> api = RetrofitTask.getRetrofit().getNomeCidade(nome);
        try {
            Response<ArrayList<CidadeDto>> response = api.execute();
            if(response.isSuccessful()){
                ArrayList<CidadeDto> listCidade = response.body();
                if(listCidade.size() != 0){
                    cdDestino = listCidade.get(0).getCd();
                }else{
                    cdDestino = null;
                    Log.d(TAG, "Não encontrado a cidade destino");
                }

            }else{
                Log.d(TAG, "ERRO: "+response.code());
            }
        } catch (IOException e) {
            Log.d(TAG, "ERRO: "+e.getMessage());
        }

        return cdDestino;
    }
}
