<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");
    
    $cdPacote = $_POST["cdPacote"];
    $CPF = $_POST["cpf"];
    $vlUnitario = $_POST["vlUnitario"];
    $cdReserva = $_POST["cdReserva"];
    $qtItens = $_POST["qtItens"];
    $vlParcial = $vlUnitario * $qtItens;

    $sqlComando =  "INSERT INTO ItensReserva(cd_pacote, cd_reserva, vl_unit, vl_parcial, qt_itens, status_itens, CPF)
                    values(
                    $cdPacote, 
                    $cdReserva, 
                    $vlUnitario, 
                    $vlParcial, 
                    $qtItens, 
                    1,
                    '$CPF')";

    if(mysqli_query($con, $sqlComando)){
        echo("true");
    } else{
        echo("false");
    }
}

?>
