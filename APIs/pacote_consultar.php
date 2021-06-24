<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $origem = $_GET["origem"];
    $destino = $_GET["destino"];
    $tipo = $_GET["tipoTransporte"];

    $array = array();
    if($tipo != -1){
        $sqlComando = "SELECT * FROM Pacote WHERE cd_cidOrigem = '$origem' and cd_cidDestino = '$destino' and cd_tipotransporte = $tipo";
    }else{
        $sqlComando = "SELECT * FROM Pacote WHERE cd_cidOrigem = '$origem' and cd_cidDestino = '$destino' order by cd_pacote desc;";
    }
    $sql = $con->prepare("$sqlComando");
    $sql->execute();
    $sql->bind_result($cd_pacote, $cd_viagem, $cd_hotel, $cd_categoria, $cd_tipotransporte, $cd_cidOrigem, $cd_cidDestino, 
    $nome_pacote, $descricao_pacote, $dtChekin_hotel, $dtChekout_hotel, $img_pacote, $vl_pacote);

    while($sql->fetch()){
        $json = [
            "cd" => $cd_pacote,
            "cdViagem" => $cd_viagem,
            "cdHotel" => $cd_hotel,
            "cdCategoria" => $cd_categoria,
            "cdTipoTransporte" => $cd_tipotransporte,
            "cdOrigem" => $cd_cidOrigem,
            "cdDestino" => $cd_cidDestino,
            "nomePacote" => $nome_pacote,
            "descricao" => $descricao_pacote,
            "checkin" => $dtChekin_hotel,
            "checkout" => $dtChekout_hotel,
            "img" => $img_pacote,
            "valor" => $vl_pacote
        ];
        
        array_push($array, $json);
    }

    echo json_encode($array);
}
?>