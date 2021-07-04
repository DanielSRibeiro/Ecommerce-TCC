package com.example.agnciadeturismo.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agnciadeturismo.data.repository.ReservaRepositoryTask
import com.example.agnciadeturismo.model.ReservaDto
import java.lang.Exception
import java.util.*

class ReservaViewModel(private val reservaRepositoryTask: ReservaRepositoryTask) : ViewModel() {

    private val TAG = "ReservaViewModel"
    private val mCadastrado = MutableLiveData<Boolean>()
    private val mCodigo = MutableLiveData<Int>()
    private val mAllReservas = MutableLiveData<ArrayList<ReservaDto>>()

    var reservas: LiveData<ArrayList<ReservaDto>> = mAllReservas
    var cadastrado: LiveData<Boolean> = mCadastrado
    var codigoReserva: LiveData<Int> = mCodigo

    fun cadastrarReserva(codigoCartao: Int, cpf: String?, valorTotal: Double, status: Int, data: String?) {
        Thread {
            try {
                mCadastrado.postValue(
                    reservaRepositoryTask.cadastrarReserva(
                        codigoCartao,
                        cpf,
                        valorTotal,
                        status,
                        data
                    )
                )
            }catch (ex : Exception){Log.d(TAG, ex.toString())}

        }.start()
    }

    fun getCodigoReserva(codigoCartao: Int, cpf: String?, valorTotal: Double, status: Int, data: String?) {
        Thread {
            try {
                mCodigo.postValue(
                    reservaRepositoryTask.getCodigoReserva(
                        codigoCartao,
                        cpf,
                        valorTotal,
                        status,
                        data
                    )
                )
            }catch (ex : Exception){Log.d(TAG, ex.toString())}
        }.start()
    }

    fun getAllReserva(cpf: String?) {
        Thread {
            try {
                mAllReservas.postValue(reservaRepositoryTask.getAllReserva(cpf))
            }catch (ex : Exception){Log.d(TAG, ex.toString())}
        }.start()
    }

    class ViewModelFactory(private val reservaRepositoryTask: ReservaRepositoryTask) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(ReservaRepositoryTask::class.java)
                .newInstance(reservaRepositoryTask)
        }
    }

}