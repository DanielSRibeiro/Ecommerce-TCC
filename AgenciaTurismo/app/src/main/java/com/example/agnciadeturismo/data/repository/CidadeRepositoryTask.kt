package com.example.agnciadeturismo.data.repository

import android.util.Log
import com.example.agnciadeturismo.data.api.RetrofitTask
import java.util.*

class CidadeRepositoryTask {

    private val TAG = "CidadeRepositoryTask"
    private val listCidade = ArrayList<String>()
    private var codigoCidade: Int? = null
    private var cidade: String? = null

    fun getAllCidades(): ArrayList<String> {
        val response = RetrofitTask.getRetrofit().allCidades.execute()
        if(response.isSuccessful){
            for (cidade in response.body()!!) {
                listCidade.add(cidade.cidade)
            }
            Log.d(TAG, "Quantidade: " + listCidade.size)
        }else{
            Log.d(TAG, response.errorBody().toString())
        }

        return listCidade
    }

    fun getCodigoCidade(cidade: String?): Int? {
        val response = RetrofitTask.getRetrofit().getNomeCidade(cidade).execute()
        if (response.isSuccessful) {
            val listCidade = response.body()!!
            if (listCidade.size != 0) {
                codigoCidade = listCidade[0].cd
            } else {
                codigoCidade = null
                Log.d(TAG, "Não encontrado a cidade")
            }
        } else {
            Log.d(TAG, "ERRO: " + response.code())
        }

        return codigoCidade
    }

    fun getNomeCidade(codigo: Int): String? {
        val response = RetrofitTask.getRetrofit().getCodigoCidade(codigo).execute()
        if (response.isSuccessful) {
            val listCidade = response.body()!!
            if (listCidade.size != 0) {
                cidade = listCidade[0].cidade
            } else {
                cidade = null
                Log.d(TAG, "Não encontrado a cidade")
            }
        } else {
            Log.d(TAG, "ERRO: " + response.code())
        }

        return cidade
    }

}