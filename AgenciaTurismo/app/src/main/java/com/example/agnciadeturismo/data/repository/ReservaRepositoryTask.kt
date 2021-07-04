package com.example.agnciadeturismo.data.repository

import android.util.Log
import com.example.agnciadeturismo.data.api.RetrofitTask
import com.example.agnciadeturismo.model.ReservaDto
import java.io.IOException
import java.util.*

class ReservaRepositoryTask {
    private val TAG = "ReservaRepositoryTask"
    private var resultadoCadastro = false
    private var reservas = ArrayList<ReservaDto>()

    fun cadastrarReserva(codigoCartao: Int, cpf: String?, valorTotal: Double, status: Int, data: String?): Boolean {
        val response = RetrofitTask.getRetrofit().cadastrarReserva(codigoCartao, cpf, valorTotal, status, data).execute()
        if (response.isSuccessful) {
            if(response.body() == true) resultadoCadastro = true
            else resultadoCadastro = false
        } else Log.d(TAG, response.code().toString())

        return resultadoCadastro
    }

    fun getCodigoReserva(codigoCartao: Int, cpf: String?, valorTotal: Double, status: Int, data: String?): Int {
        var resultado = -1
        val response = RetrofitTask.getRetrofit().getCodigoReserva(codigoCartao, cpf, valorTotal, status, data).execute()
        if (response.isSuccessful) {
            resultado = response.body()!![0].cd
        } else Log.d(TAG, response.code().toString())

        return resultado
    }

    fun getAllReserva(cpf: String?): ArrayList<ReservaDto> {
        val response = RetrofitTask.getRetrofit().getAllReserva(cpf).execute()
        if (response.isSuccessful) {
            reservas = response.body()!!
            Log.d(TAG, "Tamanho: " + response.body()!!.size)
        } else Log.d(TAG, response.code().toString() + "")

        return reservas
    }
}