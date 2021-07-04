package com.example.agnciadeturismo.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agnciadeturismo.data.repository.CidadeRepositoryTask

class CidadeViewModel(private val cidadeRepositoryTask: CidadeRepositoryTask) : ViewModel() {

    private val TAG = "CidadeViewModel"
    private val mTodasCidades = MutableLiveData<Array<String>>()
    private val mCodigoOrigem = MutableLiveData<Int>()
    private val mCodigoDestino = MutableLiveData<Int>()
    private val mOrigemCidade = MutableLiveData<String>()
    private val mDestinoCidade = MutableLiveData<String>()

    var nomeOrigem: LiveData<String> = mOrigemCidade
    var nomeDestino: LiveData<String> = mDestinoCidade
    var todasCidades: LiveData<Array<String>> = mTodasCidades
    var codigoOrigem: LiveData<Int> = mCodigoOrigem
    var codigoDestino: LiveData<Int> = mCodigoDestino

    fun getAllCidades() {
        Thread {
            try{
                val listCidades = cidadeRepositoryTask.getAllCidades()
                mTodasCidades.postValue(listCidades?.toTypedArray())
            }catch (ex : Exception){ Log.d(TAG, ex.toString())}
        }.start()
    }

    fun getCodigoCidade(origem: String?, destino: String?) {
        Thread {
            try{
                mCodigoOrigem.postValue(cidadeRepositoryTask.getCodigoCidade(origem))
                mCodigoDestino.postValue(cidadeRepositoryTask.getCodigoCidade(destino))
            }catch (ex : Exception){ Log.d(TAG, ex.toString())}
        }.start()
    }

    fun getNomeCidade(origem: Int, destino: Int) {
        Thread {
            try{
                mOrigemCidade.postValue(cidadeRepositoryTask.getNomeCidade(origem))
                mDestinoCidade.postValue(cidadeRepositoryTask.getNomeCidade(destino))
            }catch (ex : Exception){ Log.d(TAG, ex.toString())}
        }.start()
    }

    class ViewModelFactory(var cidadeRepositoryTask: CidadeRepositoryTask) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(CidadeRepositoryTask::class.java)
                .newInstance(cidadeRepositoryTask)
        }
    }
}