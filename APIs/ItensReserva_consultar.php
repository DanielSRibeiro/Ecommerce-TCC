<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $CPF = $_GET["cpf"];

    $array = array();
    $sqlComando = "SELECT * FROM ItensReserva WHERE CPF = '$CPF' AND status_itens = 1";
    $sql = $con->prepare("$sqlComando");
    $sql->execute();
    $sql->bind_result($cd, $cdPacote, $cdReserva, $vlUnitario, $vlParcial, $qtItens, $status,$CPF);

    while($sql->fetch()){
        $json = [
            "cd" => $cd,
            "cdPacote" => $cdPacote,
            "cdReserva" =>$cdReserva,
            "valorlUnitario" => $vlUnitario,
            "valorlParcial" => $vlParcial,
            "quantidade" => $qtItens,
            "CPF" => $CPF
        ];
        
        array_push($array, $json);
    }

    echo json_encode($array);
}
?>