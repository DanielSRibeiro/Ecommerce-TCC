package com.example.agnciadeturismo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agnciadeturismo.data.repository.ClienteRepositoryTask;

public class ClienteViewModel extends ViewModel {

    private MutableLiveData<Boolean> mCadastrado = new MutableLiveData<>();
    public LiveData cadastrado = mCadastrado;

    ClienteRepositoryTask clienteRepositoryTask = new ClienteRepositoryTask();
    public static String cadastradoSucesso;
    boolean postCliente;

    public void modificarCliente(boolean alterar, String nome, String email, String cpf, String rg, String telefone, String senha, String img){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!alterar){
                    inserirCliente(nome, email, cpf, rg, telefone, senha, img);
                }else{
                    updateCliente(nome, email, cpf, rg, telefone, senha, img);
                }
            }
        }).start();
    }

    private void inserirCliente(String nome, String email, String cpf, String rg, String telefone, String senha, String img) {
        postCliente = clienteRepositoryTask.inserirCliente(nome, email, cpf, rg, telefone, senha, img);
        cadastradoSucesso = "Cadastrado com Sucesso!!";
        mCadastrado.postValue(postCliente);
    }

    private void updateCliente(String nome, String email, String cpf, String rg, String telefone, String senha, String img) {
        postCliente = clienteRepositoryTask.alterarCliente(nome, email, cpf, rg, telefone, senha, img);
        cadastradoSucesso = "Alterado com Sucesso!!";
        mCadastrado.postValue(postCliente);
    }
}
