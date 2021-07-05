package com.example.agnciadeturismo.presenter.view.services

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

class PermissoesServices {
    companion object{
        fun validarPermissoes(activity: Activity, permissoes : Array<String>, requestCode : Int){
            if(Build.VERSION.SDK_INT >= 23){
                var listPermissoes = ArrayList<String>()
                for(permissao in permissoes){
                    var temPermissao = ActivityCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED
                    if(!temPermissao) {
                        listPermissoes.add(permissao)
                    }
                }
                if(listPermissoes.isNotEmpty()) ActivityCompat.requestPermissions(activity, listPermissoes.toTypedArray(), requestCode)
            }
        }
    }
}