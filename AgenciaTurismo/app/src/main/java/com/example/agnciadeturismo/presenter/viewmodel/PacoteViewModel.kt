package com.example.agnciadeturismo.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agnciadeturismo.data.repository.PacoteRepositoryTask
import com.example.agnciadeturismo.model.PacoteDto
import java.util.*

class PacoteViewModel(private val pacoteRepositoryTask: PacoteRepositoryTask) : ViewModel() {

    private val TAG = "PacoteViewModel"
    private val mOfertas = MutableLiveData<ArrayList<PacoteDto>>()
    private val mPacotes = MutableLiveData<ArrayList<PacoteDto>>()
    var pacote: LiveData<ArrayList<PacoteDto>> = mPacotes
    var oferta: LiveData<ArrayList<PacoteDto>> = mOfertas

    fun getAllPacotesOferta() {
        Thread {
            try {
                mOfertas.postValue(pacoteRepositoryTask.getAllOfertas())
            }catch (ex : Exception){ Log.d(TAG, ex.toString())}
        }.start()

    }

    fun getAllPacotes(origem: Int, destino: Int, tipo: Int) {
        Thread {
            try {
                mPacotes.postValue(pacoteRepositoryTask.getAllPacotes(origem, destino, tipo))
            }catch (ex : Exception){Log.d(TAG, ex.toString())}
        }.start()
    }

    class ViewModelFactory(private val pacoteRepositoryTask: PacoteRepositoryTask) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(PacoteRepositoryTask::class.java)
                .newInstance(pacoteRepositoryTask)
        }
    }
}