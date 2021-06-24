<?php

if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $con = mysqli_connect("localhost", "root", "123456", "db_horizon") or die
    ("Problema com a conexão");

    $cidade = $_GET["cdCidade"];
    
    $array = array();
    $sqlComando = "SELECT * FROM Cidade where cd_cidade = '$cidade'";
    $stmt = $con->prepare($sqlComando);
    $stmt->execute();
    $stmt->bind_result($cd, $cd_estado,$cidade);

    while($stmt->fetch()){
        $temp = [
            'cd' => $cd,
            'cdEstado' => $cd_estado,
            'cidade' => $cidade
        ];

        array_push($array, $temp);
    }

    echo json_encode($array);

}
?>