<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");
    
    $nome = $_POST["nome"];
    $email = $_POST["email"];
    $CPF = $_POST["cpf"];
    $rg = $_POST["rg"];
    $telefone = $_POST["telefone"];
    $senha = $_POST["senha"];

    $sqlComando = "INSERT INTO Cliente(nome, email, CPF, rg, telefone, senha, tipo)
    VALUES ('$nome', '$email', '$CPF', '$rg', '$telefone', '$senha', '3')";

    if(mysqli_query($con, $sqlComando)){
        echo("true");
    } else{
        echo("false");
    }
}

?>
