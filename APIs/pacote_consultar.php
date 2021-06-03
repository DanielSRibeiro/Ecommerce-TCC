<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $origem = $_GET["origem"];
    $destino = $_GET["destino"];

    $array = array();
    $sqlComando = "SELECT * FROM Pacote WHERE cd_cidOrigem = '$origem' and cd_cidDestino = '$destino'";
    $sql = $con->prepare("$sqlComando");
    $sql->execute();
    $sql->bind_result($cd_pacote, $cd_viagem, $cd_hotel, $cd_categoria, $cd_tipotransporte, $cd_cidOrigem, $cd_transporte, $cd_cidDestino, 
    $nome_pacote, $descricao_pacote, $dtChekin_hotel, $dtChekout_hotel, $img_pacote, $vl_pacote);

    while($sql->fetch()){
        $json = [
            "cd" => $cd_pacote,
            "cd_viagem" => $cd_viagem,
            "cd_hotel" => $cd_hotel,
            "cd_categoria" => $cd_categoria,
            "cd_tipoTransporte" => $cd_tipotransporte,
            "cd_origem" => $cd_cidOrigem,
            "cd_transporte" => $cd_transporte,
            "cd_destino" => $cd_cidDestino,
            "nome_pacote" => $nome_pacote,
            "descrecao" => $descricao_pacote,
            "chekin" => $dtChekin_hotel,
            "chekout" => $dtChekout_hotel,
            "img" => $img_pacote,
            "valor" => $vl_pacote,
        ];
        
        array_push($array, $json);
    }

    echo json_encode($array);
}
?>