<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $CPF = $_GET["cpf"];

    $array = array();
    $sqlComando = "SELECT * FROM Reserva WHERE CPF = '$CPF' and status_reserva = 1";
    $sql = $con->prepare("$sqlComando");
    $sql->execute();
    $sql->bind_result($cdReserva, $CPF, $cdCartao, $valorTotal,$statusReserva);

    while($sql->fetch()){
        $json = [
            "cd" => $cdReserva,
            "CPF" => $CPF,
            "cdCartao" => $cdCartao,
            "valorTotal" => $valorTotal,
            "statusReserva" => $statusReserva
        ];
        
        array_push($array, $json);
    }

    echo json_encode($array);
}
?>