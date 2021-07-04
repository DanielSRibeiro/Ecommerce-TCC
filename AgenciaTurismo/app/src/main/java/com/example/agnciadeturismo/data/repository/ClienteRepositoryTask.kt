package com.example.agnciadeturismo.data.repository

import android.util.Log
import com.example.agnciadeturismo.data.api.RetrofitTask

class ClienteRepositoryTask {
    private val TAG = "RepositoryTask"
    var resultadoCadastrado = false
    var resultadoUpdate = false

    fun inserirCliente(nome: String?, email: String?, cpf: String?, rg: String?, telefone: String?, senha: String?, img: String?): Boolean {
        val response = RetrofitTask.getRetrofit().cadastrarCliente(nome, email, cpf, rg, telefone, senha, img).execute()
        if (response.isSuccessful) {
            if(response.body() == true) resultadoCadastrado = true
            else resultadoCadastrado = false
        } else {
            Log.d(TAG, response.code().toString())
        }
        return resultadoCadastrado
    }

    fun alterarCliente(nome: String?, email: String?, cpf: String?, rg: String?, telefone: String?, senha: String?, img: String?): Boolean {
        val response = RetrofitTask.getRetrofit().updateCliente(nome, email, cpf, rg, telefone, senha, img).execute()
        if (response.isSuccessful) {
            if(response.body() == true) resultadoUpdate = true
            else resultadoUpdate = false
        } else Log.d(TAG, response.code().toString())

        return resultadoUpdate
    }
}