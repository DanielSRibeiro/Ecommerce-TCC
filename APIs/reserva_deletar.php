<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");
    
    $CPF = $_POST["cpf"];
    $cd = $_POST["cdReserva"];

    $sqlComando =  "UPDATE Reserva SET 
                        status_reserva = 0
                    WHERE cd_reserva = $cd";

    if(mysqli_query($con, $sqlComando)){
        echo("true");
    } else{
        echo("false");
    }
}

?>
