package com.example.agnciadeturismo.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agnciadeturismo.data.repository.ItensReservaRepositoryTask
import com.example.agnciadeturismo.model.ItensReservaDto
import kotlin.collections.ArrayList

class ItensReservaViewModel(private var itensReservaRepositoryTask: ItensReservaRepositoryTask) : ViewModel() {

    private val TAG = "ItensReservaViewModel"
    private val mCadastrado = MutableLiveData<Boolean>()
    private val mAllItens = MutableLiveData<ArrayList<ItensReservaDto>>()

    var itensReserva: LiveData<ArrayList<ItensReservaDto>> = mAllItens
    var cadastrado: LiveData<Boolean> = mCadastrado

    fun cadastrarItemReserva(codigoPagote: Int, cpf: String?, codigoReserva: Int, quantidade: Int, valorUnitario: Double) {
        Thread {
            try {
                mCadastrado.postValue(
                    itensReservaRepositoryTask.cadastrarItemReserva(
                        codigoPagote,
                        cpf,
                        codigoReserva,
                        quantidade,
                        valorUnitario
                    )
                )
            }catch (ex : Exception){Log.d(TAG, ex.toString())}

        }.start()
    }

    fun getAllItemReserva(cpf: String?, codigoReserva: Int) {
        Thread {
            try {
                mAllItens.postValue(
                    itensReservaRepositoryTask.getAllItemReserva(
                        cpf,
                        codigoReserva
                    )
                )
            }catch (ex : Exception){Log.d(TAG, ex.toString())}

        }.start()
    }

    class ViewModelFactory(private val itensReservaRepositoryTask: ItensReservaRepositoryTask) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(ItensReservaRepositoryTask::class.java)
                .newInstance(itensReservaRepositoryTask)
        }
    }
}