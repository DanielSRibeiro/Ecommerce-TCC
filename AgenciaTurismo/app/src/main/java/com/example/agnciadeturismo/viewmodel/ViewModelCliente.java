package com.example.agnciadeturismo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agnciadeturismo.repository.RepositoryTask;

public class ViewModelCliente extends ViewModel {

    private MutableLiveData<String> _clienteCadastrado = new MutableLiveData<>();
    public LiveData clienteCadastrado = _clienteCadastrado;
    RepositoryTask repositoryTask = new RepositoryTask();
    private static String post;
    public static String cadastradoSucesso = post+" com Sucesso!!";
    public static String cadastradoFalha = "Esse CPF já foi cadastrado!!";

    public void postCliente(boolean alterar, String nome, String email, String cpf, String rg, String telefone, String senha){
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean postCliente;

                if(alterar){
                    postCliente = repositoryTask.alterarCliente(nome, email, cpf, rg, telefone, senha);
                    post = "Alterado";
                } else{
                    postCliente = repositoryTask.inserirCliente(nome, email, cpf, rg, telefone, senha);
                    post = "Cadastrado";
                }

                if(postCliente == true){
                    _clienteCadastrado.postValue(cadastradoSucesso);
                } else{
                    _clienteCadastrado.postValue(cadastradoFalha);
                }
            }
        }).start();
    }
}
