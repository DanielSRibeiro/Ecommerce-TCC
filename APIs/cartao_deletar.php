<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");
    
    $CPF = $_POST["cpf"];
    $cd = $_POST["cdCartao"];

    $sqlComando =  "DELETE FROM Cartao 
                    WHERE cd_cartao = $cd and CPF = '$CPF'";

    if(mysqli_query($con, $sqlComando)){
        echo("true");
    } else{
        echo("false");
    }
}

?>
