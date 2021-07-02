package com.example.agnciadeturismo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agnciadeturismo.data.repository.CartaoRepositoryTaskK
import com.example.agnciadeturismo.model.CartaoDto
import kotlin.collections.ArrayList

class CartaoViewModel(private val repositoryTask: CartaoRepositoryTaskK) : ViewModel(){

    private val TAG = "CartaoViewModel"
    private var mCadastrar = MutableLiveData<Boolean>()
    private var mExcluir = MutableLiveData<Boolean>()
    private var mCartao = MutableLiveData<ArrayList<CartaoDto>>()

    var excluir: LiveData<Boolean> = mExcluir
    var cadastrar: LiveData<Boolean> = mCadastrar
    var cartao: LiveData<ArrayList<CartaoDto>> = mCartao

    fun init(cpf: String) = Thread { getAllCartoes(cpf) }.start()

    fun cadastrarCartao(cpf: String, nomeCartao: String, nomeImpresso: String, numero: String, cvv: String, data: String) {
        Thread {
            try {
                mCadastrar.postValue(
                    repositoryTask.inserirCartao(
                        cpf,
                        nomeCartao,
                        nomeImpresso,
                        numero,
                        cvv,
                        data
                    )
                )
            }catch (ex : Exception) {Log.d(TAG, ex.toString())}
        }.start()
    }

    fun deletarCartao(cpf: String, codigo: Int){
        Thread {
            try {
                mExcluir.postValue(repositoryTask.deletarCartao(cpf, codigo))
            } catch (ex : Exception){ Log.d(TAG, ex.toString())}
        }.start()
    }

    private fun getAllCartoes(cpf: String){
        try{
            mCartao.postValue(repositoryTask.getAllCartoes(cpf))
        }catch (ex : Exception){Log.d(TAG, ex.toString())}
    }

    class ViewModelFactory(private val repositoryTask: CartaoRepositoryTaskK) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(CartaoRepositoryTaskK::class.java)
                .newInstance(repositoryTask)
        }
    }
}