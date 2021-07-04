package com.example.agnciadeturismo.data.repository

import android.util.Log
import com.example.agnciadeturismo.data.api.RetrofitTask
import com.example.agnciadeturismo.model.PacoteDto
import java.io.IOException
import java.util.*

class PacoteRepositoryTask {
    private val TAG = "PacoteRepositoryTask"
    var pacote: ArrayList<PacoteDto>? = null
    var oferta: ArrayList<PacoteDto>? = null

    fun getAllOfertas(): ArrayList<PacoteDto>? {
        val response = RetrofitTask.getRetrofit().consultarOferta().execute()
        if (response.isSuccessful) {
            oferta = response.body()
            Log.d(TAG, "Quantidade na Lista: " + response.body()!!.size)
        } else {
            Log.d(TAG, "ERRO: " + response.code())
        }

        return oferta
    }

    fun getAllPacotes(origem: Int, destino: Int, tipo: Int): ArrayList<PacoteDto>? {
        val response = RetrofitTask.getRetrofit().buscarPacotes(origem, destino, tipo).execute()
        if (response.isSuccessful) {
            pacote = response.body()
            Log.d(TAG, "Quantidade na Lista de Pacotes: " + response.body()!!.size)
        } else Log.d(TAG, "ERRO: " + response.code())

        return pacote
    }
}