package com.example.agnciadeturismo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agnciadeturismo.data.repository.ReservaRepositoryTask;
import com.example.agnciadeturismo.model.ReservaDto;

import java.util.ArrayList;

public class ReservaViewModel extends ViewModel {

    private MutableLiveData<Boolean> mCadastrado = new MutableLiveData<>();
    private MutableLiveData<Integer> mCodigo = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ReservaDto>> mAllReservas = new MutableLiveData<>();

    public LiveData reservas = mAllReservas;
    public LiveData cadastrado = mCadastrado;
    public LiveData codigoReserva = mCodigo;

    public void cadastrarReserva(int codigoCartao, String cpf, double valorTotal, int status, String data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mCadastrado.postValue(ReservaRepositoryTask.cadastrarReserva(codigoCartao, cpf, valorTotal, status, data));
            }
        }).start();
    }

    public void getCodigoReserva(int codigoCartao, String cpf, double valorTotal, int status, String data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mCodigo.postValue(ReservaRepositoryTask.getCodigoReserva(codigoCartao, cpf, valorTotal, status, data));
            }
        }).start();
    }

    public void getAllReserva(String cpf){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mAllReservas.postValue(ReservaRepositoryTask.getAllReserva(cpf));
            }
        }).start();
    }
}
