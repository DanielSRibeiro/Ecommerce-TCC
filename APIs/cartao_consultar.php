<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $CPFUsuario = $_GET["cpf"];

    $array = array();
    $sqlComando = "SELECT * FROM Cartao WHERE CPF = '$CPFUsuario'";
    $sql = $con->prepare("$sqlComando");
    $sql->execute();
    $sql->bind_result($cdCartao, $CPF, $nomeCartao, $nomeImpresso, $numeroCartao, $cvvCartao, $validadeCartao);

    while($sql->fetch()){
        $json = [
            "cd" => $cdCartao,
            "cpf" => $CPF,
            "nome" => $nomeCartao,
            "nomeImpresso" => $nomeImpresso,
            "numero" => $numeroCartao,
            "cvv" => $cvvCartao,
            "validade" => $validadeCartao
        ];
        
        array_push($array, $json);
    }

    echo json_encode($array);
}
?>