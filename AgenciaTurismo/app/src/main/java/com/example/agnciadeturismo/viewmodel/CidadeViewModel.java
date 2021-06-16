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
                mCodigoOrigem.postValue(CidadeRepositoryTask.getOrigemCidade(origem));
                mCodigoDestino.postValue(CidadeRepositoryTask.getDestinoCidade(destino));
            }
        }).start();
    }
}
