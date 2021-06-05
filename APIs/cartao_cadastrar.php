<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");
    
    $CPF = $_POST["cpf"];
    $nomeCartao = $_POST["nomeCartao"];
    $nomeImpresso = $_POST["nomeImpresso"];
    $numero = $_POST["numeroCartao"];
    $cvv = $_POST["cvv"];
    $validade = $_POST["validadeCartao"];

    $sqlComando = "INSERT INTO Cartao(CPF, nome_cartao, nome_impresso, numero_cartao, cvv_cartao, validade_cartao)
    VALUES ('$CPF', '$nomeCartao', '$nomeImpresso', '$numero', '$cvv', '$validade')";

    if(mysqli_query($con, $sqlComando)){
        echo("true");
    } else{
        echo("false");
    }
}

?>
