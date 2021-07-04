package com.example.agnciadeturismo.data.repository

import android.util.Log
import com.example.agnciadeturismo.data.api.RetrofitTask
import com.example.agnciadeturismo.model.ItensReservaDto
import java.util.*

class ItensReservaRepositoryTask {
    private val TAG = "ItensReservaRepositoryTask"
    private var resultadoCadastro = false
    private var itensReserva: ArrayList<ItensReservaDto>? = null

    fun cadastrarItemReserva(codigoPagote: Int, cpf: String?, codigoReserva: Int, quantidade: Int, valorUnitario: Double): Boolean {
        val response = RetrofitTask.getRetrofit().cadastrarItemReserva(codigoPagote, cpf, codigoReserva, quantidade, valorUnitario).execute()
        if (response.isSuccessful) {
            if(response.body() == true) resultadoCadastro = true
            else resultadoCadastro = false
        } else Log.d(TAG, response.code().toString())
        return resultadoCadastro
    }

    fun getAllItemReserva(cpf: String?, codigoReserva: Int): ArrayList<ItensReservaDto>? {
        val response = RetrofitTask.getRetrofit().getAllItemReserva(cpf, codigoReserva).execute()
        if (response.isSuccessful) {
            itensReserva = response.body()
        } else {
            Log.d(TAG, response.code().toString())
        }

        return itensReserva
    }
}