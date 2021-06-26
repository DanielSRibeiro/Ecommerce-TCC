package com.example.agnciadeturismo.data.repository;

import android.util.Log;

import com.example.agnciadeturismo.data.api.RetrofitTask;
import com.example.agnciadeturismo.model.ItensReservaDto;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class ItemReservaRepositoryTask {

    private static final String TAG = "ItemReservaRepositoryTa";
    private static boolean resultadoCadastro;
    private static  ArrayList<ItensReservaDto> itensReserva;

    public static boolean cadastrarItemReserva(int codigoPagote, String cpf, int codigoReserva, int quantidade, double valorUnitario){
        Call<Boolean> api = RetrofitTask.getRetrofit().cadastrarItemReserva(codigoPagote, cpf, codigoReserva, quantidade, valorUnitario);
        try {
            Response<Boolean> response = api.execute();
            if(response.isSuccessful()){
                if(response.body() == true){
                    resultadoCadastro = true;
                }else{
                    resultadoCadastro = false;
                }
            }else{
                Log.d(TAG, response.code()+"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultadoCadastro;
    }

    public static ArrayList<ItensReservaDto> getAllItemReserva(String cpf, int codigoReserva){
        Call<ArrayList<ItensReservaDto>> api = RetrofitTask.getRetrofit().getAllItemReserva(cpf, codigoReserva);
        try {
            Response<ArrayList<ItensReservaDto>> response = api.execute();
            if(response.isSuccessful()){
                itensReserva = response.body();
            }else{
                Log.d(TAG, response.code()+"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itensReserva;
    }
}
