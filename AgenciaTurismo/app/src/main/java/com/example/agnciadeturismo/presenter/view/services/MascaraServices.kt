package com.example.agnciadeturismo.presenter.view.services

import android.widget.EditText
import com.github.rtoshiro.util.format.SimpleMaskFormatter
import com.github.rtoshiro.util.format.text.MaskTextWatcher

class MascaraServices {

    companion object{
        fun maskFormatter(editText: EditText, mascara: String?) {
            val mask = SimpleMaskFormatter(mascara)
            val mtw = MaskTextWatcher(editText, mask)
            editText.addTextChangedListener(mtw)
        }
    }
}