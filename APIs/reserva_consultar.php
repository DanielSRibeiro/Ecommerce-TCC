<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $CPF = $_GET["cpf"];

    $array = array();
    $sqlComando = "SELECT * FROM vwReserva WHERE CPF = '$CPF' order by dthr_reserva desc;";
    $sql = $con->prepare("$sqlComando");
    $sql->execute();
    $sql->bind_result($cdReserva, $nomeCartao, $CPF, $nomeCliente, $dthrReserva, $valorTotal);

    while($sql->fetch()){
        $json = [
            "cd" => $cdReserva,
            "nomeCartao" => $nomeCartao,
            "nomeCliente" => $nomeCliente,
            "valorTotal" => $valorTotal,
            "dthrReserva" => $dthrReserva
        ];
        
        array_push($array, $json);
    }

    echo json_encode($array);
}
?>