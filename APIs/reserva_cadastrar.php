<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");
    
    $cdCartao = $_POST["cdCartao"];
    $CPF = $_POST["cpf"];
    $vlTotal = $_POST["vlTotal"];
    $status = $_POST["statusReserva"];
    $dthrReserva = $_POST["dthrReserva"];

    $sqlComando =  "INSERT INTO Reserva(CPF, cd_cartao, vl_total, status_reserva, dthr_reserva)
                    VALUES('$CPF', $cdCartao, $vlTotal, 1, '$dthrReserva');";

    if(mysqli_query($con, $sqlComando)){
        echo("true");
    } else{
        echo("false");
    }
}

?>