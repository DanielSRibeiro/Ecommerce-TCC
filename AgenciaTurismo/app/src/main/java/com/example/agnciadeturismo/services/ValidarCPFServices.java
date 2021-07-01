package com.example.agnciadeturismo.services;

public class ValidarCPFServices {

    public static boolean validarCPF(String cpf) {
        String s1, s2, s3, s4, s5, s6, s7, s8, s9, confere = "";
        int n1, n2, n3, n4, n5, n6, n7, n8, n9, verificador1, verificador2;
        s1 = cpf.substring(0,1); n1 = Integer.parseInt(s1);
        s2 = cpf.substring(1,2); n2 = Integer.parseInt(s2);
        s3 = cpf.substring(2,3); n3 = Integer.parseInt(s3);
        s4 = cpf.substring(4,5); n4 = Integer.parseInt(s4);
        s5 = cpf.substring(5,6); n5 = Integer.parseInt(s5);
        s6 = cpf.substring(6,7); n6 = Integer.parseInt(s6);
        s7 = cpf.substring(8,9); n7 = Integer.parseInt(s7);
        s8 = cpf.substring(9,10); n8 = Integer.parseInt(s8);
        s9 = cpf.substring(10,11); n9 = Integer.parseInt(s9);

        boolean valido;
        if(n1 == n2 && n2 == n3 && n3 == n4 && n4 == n5 && n5 == n6 && n6 == n7 && n7 == n8 && n8 == n9){
            valido = false;
        }else{
            verificador1 = (n1*10 + n2*9 + n3*8 + n4*7 + n5*6 + n6*5 + n7*4 + n8*3 + n9*2);
            if((verificador1 % 11) < 2){
                verificador1 = 0;
            }else{
                verificador1 = 11 - (verificador1 % 11);
            }
            verificador2 = (n1*11 + n2*10 + n3*9 + n4*8 + n5*7 + n6*6 + n7*5 + n8*4 + n9*3 + verificador1 * 2);

            if((verificador2 % 11) < 2){
                verificador2 = 0;
            }else{
                verificador2 = 11 - (verificador2 % 11);
            }
            confere = (s1 + s2 + s3 + "."+ s4 + s5 + s6 + "."+ s7 +s8 + s9 + "-" + verificador1 + "" + verificador2);

            if(confere.equals(cpf)){
                valido = true;
            }else{
                valido = false;
            }
        }

        return valido;
    }
}
