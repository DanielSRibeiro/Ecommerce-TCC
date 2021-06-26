package com.example.agnciadeturismo.data.repository;

import android.util.Log;

import com.example.agnciadeturismo.data.api.HorizonFlyApi;
import com.example.agnciadeturismo.data.api.RetrofitTask;
import com.example.agnciadeturismo.model.ReservaDto;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;

public class ReservaRepositoryTask {

    private static final String TAG = "ReservaRepositoryTask";
    private static boolean resultadoCadastro;
    private static ArrayList<ReservaDto> reservas = new ArrayList<>();

    public static boolean cadastrarReserva(int codigoCartao, String cpf, double valorTotal, int status, String data){
        Call<Boolean> api = RetrofitTask.getRetrofit().cadastrarReserva(codigoCartao, cpf, valorTotal, status, data);
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

    public static int getCodigoReserva(int codigoCartao, String cpf, double valorTotal, int status, String data){
        int resultado = -1;
        Call<ArrayList<ReservaDto>> api = RetrofitTask.getRetrofit().getCodigoReserva(codigoCartao, cpf, valorTotal, status, data);
        try {
            Response<ArrayList<ReservaDto>> response = api.execute();
            if(response.isSuccessful()){
                resultado = response.body().get(0).getCd();
            }else{
                resultado = -1;
                Log.d(TAG, response.code()+"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public static ArrayList<ReservaDto> getAllReserva(String cpf){
        Call<ArrayList<ReservaDto>> api = RetrofitTask.getRetrofit().getAllReserva(cpf);
        try {
            Response<ArrayList<ReservaDto>> response = api.execute();
            if(response.isSuccessful()){
                reservas = response.body();
                Log.d(TAG, "Tamanho: "+response.body().size());
            }else{
                Log.d(TAG, response.code()+"");
            }
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }

        return reservas;
    }
}
