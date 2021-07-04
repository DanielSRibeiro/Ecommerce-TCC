package com.example.agnciadeturismo.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agnciadeturismo.data.repository.CidadeRepositoryTask
import com.example.agnciadeturismo.data.repository.ClienteRepositoryTask

class ClienteViewModel(private val clienteRepositoryTask: ClienteRepositoryTask) : ViewModel() {

    private val TAG = "ClienteViewModel"
    private val mCadastrado = MutableLiveData<Boolean>()
    var cadastrado: LiveData<Boolean> = mCadastrado

    var postCliente = false
    var cadastradoSucesso: String? = null

    fun modificarCliente(alterar: Boolean, nome: String, email: String, cpf: String, rg: String, telefone: String, senha: String, img: String) {
        Thread {
            try{
                if (!alterar) inserirCliente(nome, email, cpf, rg, telefone, senha, img)
                else updateCliente(nome, email, cpf, rg, telefone, senha, img)
            }catch (ex : Exception){ Log.d(TAG, ex.toString())}
        }.start()
    }

    private fun inserirCliente(nome: String, email: String, cpf: String, rg: String, telefone: String, senha: String, img: String?) {
        postCliente = clienteRepositoryTask.inserirCliente(nome, email, cpf, rg, telefone, senha, img)
        cadastradoSucesso = "Cadastrado com Sucesso!!"
        mCadastrado.postValue(postCliente)
    }

    private fun updateCliente(nome: String, email: String, cpf: String, rg: String, telefone: String, senha: String, img: String) {
        postCliente = clienteRepositoryTask.alterarCliente(nome, email, cpf, rg, telefone, senha, img)
        cadastradoSucesso = "Alterado com Sucesso!!"
        mCadastrado.postValue(postCliente)
    }

    class ViewModelFactory(private val clienteRepositoryTask: ClienteRepositoryTask) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(ClienteRepositoryTask::class.java)
                .newInstance(clienteRepositoryTask)
        }
    }
}