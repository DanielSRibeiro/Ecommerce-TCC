<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $cdCartao = $_GET["cdCartao"];
    $cpf= $_GET["cpf"];
    $valorTotal = $_GET["valorTotal"];
    $statusReserva = $_GET["statusReserva"];
    $data = $_GET["data"];

    $array = array();
    $sqlComando = "SELECT cd_reserva FROM reserva WHERE 
                        CPF = '$cpf'
                    AND cd_cartao = $cdCartao
                    AND status_reserva = $statusReserva
                    AND vl_total = $valorTotal  
                    AND dthr_reserva = '$data'";
    $sql = $con->prepare("$sqlComando");
    $sql->execute();
    $sql->bind_result($codigo);

    while($sql->fetch()){
        $json = [
            "cd" => $codigo
        ];
        
        array_push($array, $json);
    }

    echo json_encode($array);
}
?>