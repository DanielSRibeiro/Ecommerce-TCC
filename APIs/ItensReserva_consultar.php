<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $CPF = $_GET["cpf"];
    $cdReserva = $_GET["cdReserva"];

    $array = array();
    $sqlComando =  "SELECT * FROM vwItensReserva
    WHERE cpf = '$CPF' AND codigoReseva = $cdReserva order by codigo desc;";
    $sql = $con->prepare("$sqlComando");
    $sql->execute();
    $sql->bind_result($cd, $cdPacote, $cdReserva, $cpf, $unit, $parcial, $quantidade, $img, $destino, $nomePacote, $codigoTransporte);

    while($sql->fetch()){
        $json = [
            "cd" => $cd,
            "cdReserva" => $cdReserva,
            "cdPacote" => $cdPacote,
            "cpf" => $cpf,
            "valorUnitario" => $unit,
            "valorTotal" => $parcial,
            "quantidade" => $quantidade,
            "img" => $img,
            "destino" => $destino,
            "nomePacote" => $nomePacote,
            "codigoTransporte" => $codigoTransporte
        ];
        
        array_push($array, $json);
    }

    echo json_encode($array);
}
?>