package com.example.agnciadeturismo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.agnciadeturismo.data.repository.CartaoRepositoryTask;
import com.example.agnciadeturismo.model.CartaoDto;

import java.util.ArrayList;

public class CartaoViewModel extends ViewModel {

    CartaoRepositoryTask repositoryTask = new CartaoRepositoryTask();
    private MutableLiveData<Boolean> mCadastrar = new MutableLiveData<>();
    private MutableLiveData<Boolean> mExcluir = new MutableLiveData<>();
    private MutableLiveData<ArrayList<CartaoDto>> mCartao = new MutableLiveData<>();

    public LiveData excluir = mExcluir;
    public LiveData cadastrar = mCadastrar;
    public LiveData cartao = mCartao;

    public void init(String cpf){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getAllCartoes(cpf);
            }
        }).start();
    }

    public void cadastrarCartao(String cpf, String nomeCartao, String nomeImpresso, String numero, String cvv, String data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                 mCadastrar.postValue(repositoryTask.inserirCartao(cpf, nomeCartao, nomeImpresso, numero, cvv, data));
            }
        }).start();
    }

    public void deletarCartao(String cpf, int codigo){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mExcluir.postValue(repositoryTask.deletarCartao(cpf, codigo));
            }
        }).start();
    }

    private void getAllCartoes(String cpf){
        mCartao.postValue(repositoryTask.getAllCartoes(cpf));
    }

}