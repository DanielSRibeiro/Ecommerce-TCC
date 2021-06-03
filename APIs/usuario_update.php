<?php

if($_SERVER["REQUEST_METHOD"] == 'POST'){
   $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
   ("Problema com a conexão");

   $nome = $_POST["nome"];
   $email = $_POST["email"];
   $CPF = $_POST["CPF"];
   $rg = $_POST["rg"];
   $telefone = $_POST["telefone"];
   $senha = $_POST["senha"];
   $img = $_POST["img"];


   $sqlComando = "  UPDATE Cliente SET 
                        nome = '$nome',
                        email = '$email',
                        rg = '$rg',
                        telefone = '$telefone',
                        senha = '$senha',
                        img = '$img'
                    WHERE CPF = '$CPF'";

    if(mysqli_query($con, $sqlComando)){
        echo("true");
    } else{
        echo("false");
    }
}
?>