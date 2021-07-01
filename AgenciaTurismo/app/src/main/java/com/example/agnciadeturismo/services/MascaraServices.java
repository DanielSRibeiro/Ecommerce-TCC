package com.example.agnciadeturismo.services;

import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

public class MascaraServices {

    public static void maskFormatter(EditText editText, String mascara) {
        SimpleMaskFormatter mask = new SimpleMaskFormatter(mascara);
        MaskTextWatcher mtw = new MaskTextWatcher(editText, mask);
        editText.addTextChangedListener(mtw);
    }
}