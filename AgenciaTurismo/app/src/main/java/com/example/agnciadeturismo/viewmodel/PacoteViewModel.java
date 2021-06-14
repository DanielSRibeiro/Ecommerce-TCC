package com.example.agnciadeturismo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agnciadeturismo.data.repository.PacoteRepositoryTask;
import com.example.agnciadeturismo.model.PacoteDto;

import java.util.ArrayList;

public class PacoteViewModel extends ViewModel{

    PacoteRepositoryTask repositoryTask = new PacoteRepositoryTask();
    private MutableLiveData<ArrayList<PacoteDto>> mOfertas = new MutableLiveData();
    public LiveData oferta = mOfertas;

    public void getAllPacotesOferta(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mOfertas.postValue(repositoryTask.getAllOfertas());
            }
        }).start();
    }
}