<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $CPF = $_GET["cpf"];

    $array = array();
    $sqlComando =  "SELECT * FROM vwCarrinho  
                    WHERE cpf = '$CPF'";
    $sql = $con->prepare("$sqlComando");
    $sql->execute();
    $sql->bind_result($cd, $cdPacote, $cdReserva, $cpf, $unit, $parcial, $quantidade, $img, $destino, $nomePacote);

    while($sql->fetch()){
        $json = [
            "cd" => $cd,
            "cdReserva" => $cdReserva,
            "cdPacote" => $cdPacote,
            "cpf" => $cpf,
            "valorTotal" => $parcial,
            "quantidade" => $quantidade,
            "img" => $img,
            "destino" => $destino,
            "nomePacote" => $nomePacote,
        ];
        
        array_push($array, $json);
    }

    echo json_encode($array);
}
?>