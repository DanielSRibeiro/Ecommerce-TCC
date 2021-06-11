package com.example.agnciadeturismo.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agnciadeturismo.data.api.HorizonFlyApi;
import com.example.agnciadeturismo.data.api.RetrofitTask;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.data.repository.RepositoryTask;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelCliente extends ViewModel {

    private static final String TAG = "ViewModelCliente";
    private MutableLiveData<String> _clienteCadastrado = new MutableLiveData<>();

    public LiveData clienteCadastrado = _clienteCadastrado;

    RepositoryTask repositoryTask = new RepositoryTask();
    public static String cadastradoSucesso;
    public static String cadastradoFalha = "Esse CPF já foi cadastrado!!";
    boolean postCliente;

    public void modificarCliente(boolean alterar, String nome, String email, String cpf, String rg, String telefone, String senha, String img){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!alterar){
                    inserirCliente(nome, email, cpf, rg, telefone, senha);
                }else{
                    updateCliente(nome, email, cpf, rg, telefone, senha, img);
                }
            }
        }).start();
    }

    private void inserirCliente(String nome, String email, String cpf, String rg, String telefone, String senha) {
        postCliente = repositoryTask.inserirCliente(nome, email, cpf, rg, telefone, senha);
        cadastradoSucesso = "Cadastrado com Sucesso!!";
        setClienteCadastrado(cadastradoSucesso, postCliente);
    }

    private void updateCliente(String nome, String email, String cpf, String rg, String telefone, String senha, String img) {
        postCliente = repositoryTask.alterarCliente(nome, email, cpf, rg, telefone, senha, img);
        cadastradoSucesso = "Alterado com Sucesso!!";
        setClienteCadastrado(cadastradoSucesso, postCliente);
    }

    private void setClienteCadastrado(String cadastradoSucesso, boolean postCliente) {
        if(postCliente == true){
            _clienteCadastrado.postValue(cadastradoSucesso);
        } else{
            _clienteCadastrado.postValue(cadastradoFalha);
        }
    }
}
