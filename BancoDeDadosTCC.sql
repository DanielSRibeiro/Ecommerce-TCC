/*Dropar o banco de dados(Nunca faça isso caso seja realmente nescessário*/
drop database if exists db_horizon;
create database db_horizon
default character set utf8
default collate utf8_general_ci;

use db_horizon;

/* Criando a tabela Fucnionário*/
CREATE TABLE Funcionario (
    nome VARCHAR(100),
    senha VARCHAR(10),
    CPF VARCHAR(20) not null unique primary key,
	telefone VARCHAR(20) not null,
	email VARCHAR(150) not null,
	rg VARCHAR(9) not null,
    cargo VARCHAR(50) not null,
    tipo ENUM ('1','2','3','4') not null,
	img VARCHAR(1000)
);

/* Criando a tabela Cliente*/
CREATE TABLE Cliente (
    nome VARCHAR(150) not null,
    telefone VARCHAR(20) not null,
    email VARCHAR(150) not null,
    CPF VARCHAR(20) not null unique primary key,
    rg VARCHAR(9) not null,
    senha VARCHAR(10) not null,
    img VARCHAR(1000),
    tipo ENUM ('1','2','3','4') not null
);

/* Criando a tabela Estado*/
CREATE TABLE Estado (
    cd_estado INTEGER not null primary key auto_increment,
    estado VARCHAR(50) not null,
    uf VARCHAR(2) not null
);

/* Criando a tabela Cidade*/
CREATE TABLE Cidade (
    cd_cidade INTEGER not null primary key auto_increment,
    cd_estado INTEGER not null,
    cidade VARCHAR(100) not null,
    FOREIGN KEY (cd_estado) REFERENCES Estado (cd_estado) /*Referencia */
);

-- criando a tabela tipodetransporte
CREATE TABLE TipoTransporte (
cd_tipotransporte INTEGER primary key auto_increment,
tipo_transporte VARCHAR(60) -- Ônibus / Avião / Cruzeiro
);

/* Criando a tabela Transporte*/
CREATE TABLE Transporte (
    cd_transporte INTEGER not null primary key auto_increment,
    cidade_transporte INTEGER not null,
    nome_transporte VARCHAR(100) not null,
    cd_tipotransporte INTEGER not null,
    img_transporte VARCHAR(1000) not null,
    FOREIGN KEY (cd_tipotransporte) REFERENCES TipoTransporte(cd_tipotransporte),
     FOREIGN KEY (cidade_transporte) REFERENCES Cidade(cd_cidade)
);

/* Criando a tabela Hotel*/
CREATE TABLE Hotel (
    cd_hotel INTEGER not null primary key auto_increment,
    cd_cidade INTEGER not null,
    nome_hotel VARCHAR(100) not null,
    descricao_hotel VARCHAR(400) not null,
    telefone_hotel VARCHAR(17) not null,
    endereco_hotel VARCHAR(100) not null,
    diaria_hotel decimal (10,2) not null,
    img_hotel VARCHAR(500) not null,
    FOREIGN KEY (cd_cidade) REFERENCES Cidade (cd_cidade) /*Referencia */
);

/* Criando a tabela Viagem*/
CREATE TABLE Viagem (
    cd_viagem INTEGER not null primary key auto_increment,
    cd_tipotransporte INTEGER not null,
	nome_viagem VARCHAR(100) not null,
    origem INTEGER not null,
    destino INTEGER not null,
    dt_ida DATETIME not null,
    dt_chegada DATETIME not null,
    descricao VARCHAR(400) not null,
    vl_total VARCHAR(50) not null,
    img_viagem VARCHAR(1000) not null,
    FOREIGN KEY (origem) REFERENCES Transporte(cd_transporte) /*Referencia */,
    FOREIGN KEY (destino) REFERENCES Transporte(cd_transporte) /*Referencia */,
    FOREIGN KEY (cd_tipotransporte) REFERENCES TipoTransporte(cd_tipotransporte)
);

/*Criando a tabela categoria */
CREATE TABLE Categoria (
cd_categoria  INTEGER primary key auto_increment,
Categoria VARCHAR(50) null
);

/* Criando a tabela Pacote*/
CREATE TABLE Pacote (
    cd_pacote INTEGER not null primary key auto_increment,
    cd_viagem INTEGER not null,
    cd_hotel INTEGER not null,
    cd_categoria INTEGER  null,
    cd_tipotransporte INTEGER null,
    cd_cidOrigem INTEGER not null,
    cd_cidDestino INTEGER not null,
    nome_pacote VARCHAR(50) not null,
    descricao_pacote VARCHAR(500) not null,
    dtChekin_hotel DATETIME not null,
    dtChekout_hotel DATETIME not null,
    img_pacote VARCHAR(1000) not null,
    vl_pacote   VARCHAR(50)   null,
    FOREIGN KEY (cd_hotel) REFERENCES Hotel (cd_hotel) /*Referencia */,
	FOREIGN KEY (cd_viagem) REFERENCES Viagem(cd_viagem) /*Referencia */,
	FOREIGN KEY (cd_cidOrigem) REFERENCES Cidade(cd_cidade) /*Referencia */,
	FOREIGN KEY (cd_cidDestino) REFERENCES Cidade(cd_cidade) /*Referencia */,
	FOREIGN KEY (cd_categoria) REFERENCES Categoria(cd_categoria) /*Referencia */,
    FOREIGN KEY (cd_tipotransporte) REFERENCES TipoTransporte(cd_tipotransporte)
);

/* Criando a tabela Cartão */
CREATE TABLE Cartao (
    cd_cartao INTEGER not null primary key auto_increment,
    CPF VARCHAR(20) not null ,
    nome_cartao VARCHAR(80) not null,
    nome_impresso VARCHAR(150) not null, 
    numero_cartao VARCHAR(20) not null,
    cvv_cartao VARCHAR(3) not null,
    validade_cartao DATE not null,
    FOREIGN KEY (CPF) REFERENCES Cliente(CPF) /*Referencia */
);

/* Criando a tabela Reserva*/
CREATE TABLE Reserva (
    cd_reserva INTEGER not null primary key auto_increment,
    CPF VARCHAR(20) not null,
    cd_cartao INTEGER not null,
    vl_total Decimal(14,0) not null,
	status_reserva bit not null,
    dthr_reserva datetime not null,
    FOREIGN KEY (CPF) REFERENCES Cliente (CPF), /*Referencia */
    FOREIGN KEY (cd_cartao) REFERENCES Cartao (cd_cartao) /*Referencia */
);

/* Criando a tabela Itens_Reservas*/
CREATE TABLE ItensReserva(
 cd_itensreserva INTEGER auto_increment not null primary key,
 cd_pacote INTEGER not null,
 cd_reserva INTEGER not null,
 vl_unit DECIMAL(14,0) not null,
 vl_parcial DECIMAL(14,0) not null,
 qt_itens INTEGER  not null,
 status_itens bit not null,
 CPF VARCHAR(20) not null,
 FOREIGN KEY (cd_pacote) REFERENCES Pacote (cd_pacote),  /*Referencia */
 FOREIGN KEY (cd_reserva) REFERENCES Reserva (cd_reserva),
 FOREIGN KEY (CPF) REFERENCES Cliente (CPF)  /*Referencia *//*Referencia */
);

-- Views logo Abaixo;
drop view  if exists vwItensReserva;
CREATE VIEW vwItensReserva as select
	carrinho.cd_itensreserva as codigo,
    carrinho.cd_pacote as codigoPacote,
    carrinho.cd_reserva as codigoReseva,
    carrinho.CPF as cpf,
    carrinho.vl_unit as valorUnitario,
    carrinho.vl_parcial as valorParcial,
    carrinho.qt_itens as quantidade,
    pacote.img_pacote as imagemPacote,
    cidade.cidade as destino,
    pacote.nome_pacote as nomePacote,
    pacote.cd_tipotransporte as codigoTransporte
from ItensReserva as carrinho 
inner join pacote on pacote.cd_pacote = carrinho.cd_pacote
inner join cidade on cidade.cd_cidade = pacote.cd_cidDestino;

drop view  if exists vwReserva;
CREATE VIEW vwReserva as select
	reserva.cd_reserva as codigoReserva,
    cartao.nome_cartao as cartao,
    cliente.CPF,
    cliente.nome as nomeCliente,
    reserva.dthr_reserva,
    reserva.vl_total as valorTotal
from reserva 
inner join cartao on cartao.cd_cartao = reserva.cd_cartao
inner join cliente on cliente.CPF = reserva.CPF;

-- Selecionando as Tabelas ( Selects de cada tabela);
SELECT * FROM Cidade;
SELECT * FROM Estado;
SELECT * FROM Categoria;
SELECT * FROM tipotransporte;
SELECT * FROM Cliente;
SELECT * FROM Hotel;
SELECT * FROM Cartao;
SELECT * FROM Funcionario;
SELECT * FROM Viagem;
SELECT * FROM Transporte;
SELECT * FROM Pacote;
SELECT * FROM Reserva;
SELECT * FROM ItensReserva;
SELECT * FROM Itens_escolhidos;
select * from Cidade where cidade like '%recife%';
select * from Pacote where cd_cidDestino = 4816 and cd_cidOrigem = 591;
Select * from Funcionario where rg = '241574230';
select * from vwReserva;
select * from vwitensreserva;