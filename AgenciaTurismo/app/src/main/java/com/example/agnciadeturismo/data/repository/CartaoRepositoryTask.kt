package com.example.agnciadeturismo.data.repository

import android.util.Log
import com.example.agnciadeturismo.data.api.RetrofitTask
import com.example.agnciadeturismo.model.CartaoDto
import java.util.*

class CartaoRepositoryTask {
    private val TAG = "CartaoRepositoryTask"
    var cadastrado = false
    var excluido = false
    var cartao = ArrayList<CartaoDto>()

    fun getAllCartoes(cpf: String): ArrayList<CartaoDto>? {
        val response = RetrofitTask.getRetrofit().consultarCartao(cpf).execute()

        if (response.isSuccessful) {
            cartao = response.body()!!
            Log.d(TAG, "Quantidade na Lista: " + response.body()!!.size)
        } else Log.d(TAG, "ERRO: " + response.code())

        return cartao
    }

    fun inserirCartao(cpf: String, nomeCartao: String, nomeImpresso: String, numero: String, cvv: String, data: String): Boolean {
        val response = RetrofitTask.getRetrofit().cadastrarCartao(cpf, nomeCartao, nomeImpresso, numero, cvv, data).execute()
        if (response.isSuccessful) {
            if (response.body() == true) {
                cadastrado = true
            }
        } else {
            Log.d(TAG, "ERRO: " + response.code())
            cadastrado = false
        }
        return cadastrado
    }

    fun deletarCartao(cpf: String, codigo: Int): Boolean {
        val response = RetrofitTask.getRetrofit().deletarCartao(cpf, codigo).execute()

        if (response.isSuccessful) {
            if(response.body() == true){
                excluido = true
            } else{
                excluido = false
            }
        } else Log.d(TAG, "ERRO: " + response.code())

        return excluido
    }
}