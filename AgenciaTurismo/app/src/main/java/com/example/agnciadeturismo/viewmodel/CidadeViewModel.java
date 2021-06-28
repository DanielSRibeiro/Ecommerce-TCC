package com.example.agnciadeturismo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agnciadeturismo.data.repository.CidadeRepositoryTask;

import java.util.ArrayList;

public class CidadeViewModel extends ViewModel {

    private MutableLiveData<String[]> mTodasCidades = new MutableLiveData<>();
    private MutableLiveData<Integer> mCodigoOrigem = new MutableLiveData<>();
    private MutableLiveData<Integer> mCodigoDestino = new MutableLiveData<>();
    private MutableLiveData<String> mOrigemCidade = new MutableLiveData<>();
    private MutableLiveData<String> mDestinoCidade = new MutableLiveData<>();

    public LiveData nomeOrigem = mOrigemCidade;
    public LiveData nomeDestino = mDestinoCidade;
    public LiveData todasCidades = mTodasCidades;
    public LiveData codigoOrigem = mCodigoOrigem;
    public LiveData codigoDestino = mCodigoDestino;

    public void getAllCidades(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> listCidades = CidadeRepositoryTask.getAllCidades();
                mTodasCidades.postValue(listCidades.toArray(new String[listCidades.size()]));
            }
        }).start();
    }

    public void getCodigoCidade(String origem, String destino){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mCodigoOrigem.postValue(CidadeRepositoryTask.getCodigoCidade(origem));
                mCodigoDestino.postValue(CidadeRepositoryTask.getCodigoCidade(destino));
            }
        }).start();
    }

    public void getNomeCidade(int origem, int destino){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mOrigemCidade.postValue(CidadeRepositoryTask.getNomeCidade(origem));
                mDestinoCidade.postValue(CidadeRepositoryTask.getNomeCidade(destino));
            }
        }).start();
    }
}
