<?php

if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $CPFUsuario = $_GET["cpf"];
    $senhaUsuario = $_GET["senha"];
    
    $array = array();
    $sqlComando = "SELECT * FROM Cliente WHERE CPF = '$CPFUsuario' AND senha = '$senhaUsuario'";
    $stmt = $con->prepare($sqlComando);
    $stmt->execute();
    $stmt->bind_result($nome, $telefone, $email, $CPF, $rg, $senha, $img, $tipo);

    while($stmt->fetch()){
        $temp = [
            'nome' => $nome,
            'telefone' => $telefone,
            'email' => $email,
            'CPF' => $CPF,
            'rg' => $rg,
            'senha' => $senha,
            'img' => $img,
            'tipo' => $tipo
        ];

        array_push($array, $temp);
    }

    echo json_encode($array);

}
?>