package com.example.agnciadeturismo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agnciadeturismo.data.repository.ClienteRepositoryTask;

public class ViewModelCliente extends ViewModel {

    private MutableLiveData<String> _clienteCadastrado = new MutableLiveData<>();
    public LiveData clienteCadastrado = _clienteCadastrado;

    ClienteRepositoryTask clienteRepositoryTask = new ClienteRepositoryTask();
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
        postCliente = clienteRepositoryTask.inserirCliente(nome, email, cpf, rg, telefone, senha);
        cadastradoSucesso = "Cadastrado com Sucesso!!";
        setClienteCadastrado(cadastradoSucesso, postCliente);
    }

    private void updateCliente(String nome, String email, String cpf, String rg, String telefone, String senha, String img) {
        postCliente = clienteRepositoryTask.alterarCliente(nome, email, cpf, rg, telefone, senha, img);
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
