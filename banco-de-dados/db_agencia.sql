/*Criando o banco de dados*/
drop database if exists db_horizon;
create database db_horizon
default character set utf8
default collate utf8_general_ci;

use db_horizon;

/*Atualizado: 23/05 */

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

insert into CLiente()
values('Daniel','112121221','daniel@gmail.com','000.000.000-00','123456789','1234567890', null ,'3'),
('Teste','112121221','teste@gmail.com','000.000.000-01','123456789','1234567890', null ,'3');

/*
select * from Cliente;
select * from Cliente where CPF = '000.000.000-01' and senha = '1234567890';

UPDATE Cliente SET 
	nome = 'Teste 1'
WHERE CPF = '000.000.000-00';

DELETE FROM Cliente	WHERE numeroCartao = $numeroCartao and CPF = $CPF;
*/
/* Criando a tabela Estado*/
CREATE TABLE Estado (
    cd_estado INTEGER not null primary key auto_increment,
    estado VARCHAR(50) not null,
    uf VARCHAR(2) not null
);

INSERT INTO Estado(estado, uf)
VALUES ('São Paulo', 'SP'),
('Rio de janeiro', 'RJ');

/* Criando a tabela Cidade*/
CREATE TABLE Cidade (
    cd_cidade INTEGER not null primary key auto_increment,
    cd_estado INTEGER not null,
    cidade VARCHAR(100) not null,
    FOREIGN KEY (cd_estado) REFERENCES Estado (cd_estado) /*Referencia */
);

INSERT INTO Cidade(cd_estado, cidade)
VALUES (1, 'São Paulo'),
(1, 'Osasco'),
(2, 'Rio de janeiro'),
(2, 'Niterói');

-- criando a tabela tipodetransporte
CREATE TABLE TipoTransporte (
cd_tipotransporte INTEGER primary key auto_increment,
tipo_transporte VARCHAR(60) -- Ônibus / Avião / Cruzeiro
);

INSERT INTO TipoTransporte(tipo_transporte)
VALUES ('Ônibus'),
('Avião'),
('Cruzeiro');

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

insert into Transporte(cidade_transporte, nome_transporte, cd_tipotransporte, img_transporte )
values(3, 'Porto do Rio Janeiro', 1, "" ),
(1, 'Porto Itajai', 1,"");

/* Criando a tabela Hotel*/
CREATE TABLE Hotel (
    cd_hotel INTEGER not null primary key auto_increment,
    cd_cidade INTEGER not null,
    nome_hotel VARCHAR(100) not null,
    descricao_hotel VARCHAR(400) not null,
    telefone_hotel VARCHAR(17) not null,
    endereco_hotel VARCHAR(100) not null,
    diaria_hotel DECIMAL(10,2) not null,
    img_hotel VARCHAR(500) not null,
    FOREIGN KEY (cd_cidade) REFERENCES Cidade (cd_cidade) /*Referencia */
);

insert into Hotel(cd_cidade, nome_hotel, diaria_hotel, descricao_hotel, endereco_hotel, telefone_hotel , img_hotel )
values(1, 'Nome do Hotel', 3, 'Descrição', 'Endereço' , '1212121', ''),
(3, 'Nome do Hotel do Rio', 7, 'Descrição', 'Endereço' , '11111', '');

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
    vl_total DECIMAL(10,2) not null,
    img_viagem VARCHAR(1000) not null,
    FOREIGN KEY (origem) REFERENCES Transporte(cd_transporte) /*Referencia */,
    FOREIGN KEY (destino) REFERENCES Transporte(cd_transporte) /*Referencia */,
    FOREIGN KEY (cd_tipotransporte) REFERENCES TipoTransporte(cd_tipotransporte)
);

insert into Viagem(nome_viagem,  cd_tipotransporte, origem,
destino,  dt_ida, dt_chegada, descricao, vl_total, img_viagem)
values('Rio de Janeiro', 1, 2, 1, '2021-06-05', '2021-06-05' , 'Descrição', 790.00, ''),
	  ('São Paulo',      2, 1, 2, '2021-06-05', '2021-06-05' , 'Descrição', 1090.00, '' );

/*Criando a tabela categoria */
CREATE TABLE Categoria (
cd_categoria  INTEGER primary key auto_increment,
Categoria VARCHAR(50) null
);

insert into Categoria(Categoria)
values('Em oferta'),
('Mais procurados'),
('Destaques'),
('Premiuns');

/* Criando a tabela Cartão */
CREATE TABLE Cartao (
    cd_cartao INTEGER not null primary key auto_increment,
    CPF VARCHAR(20) not null ,
    nome_cartao VARCHAR(80) not null,
    nome_impresso VARCHAR(150) not null, 
    numero_cartao VARCHAR(16) not null,
    cvv_cartao VARCHAR(3) not null,
    validade_cartao DATE not null,
    FOREIGN KEY (CPF) REFERENCES Cliente(CPF) /*Referencia */
);

SELECT * FROM Cartao;

INSERT INTO Cartao(CPF, nome_cartao, nome_impresso, numero_cartao, cvv_cartao, validade_cartao)
VALUES ('000.000.000-00', 'Nubank', 'Daniel', '11', '111', '2021-08-02'),
('000.000.000-01', 'Inter', 'Anna', '11123123123123', '122', '2016-00-08'),
('000.000.000-00', 'Inter', 'Daniel', '11123123123', '111', '2013-00-08');


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
    vl_pacote DECIMAL(10,2) not null,
    FOREIGN KEY (cd_hotel) REFERENCES Hotel (cd_hotel) /*Referencia */,
	FOREIGN KEY (cd_viagem) REFERENCES Viagem(cd_viagem) /*Referencia */,
	FOREIGN KEY (cd_cidOrigem) REFERENCES Cidade(cd_cidade) /*Referencia */,
	FOREIGN KEY (cd_cidDestino) REFERENCES Cidade(cd_cidade) /*Referencia */,
	FOREIGN KEY (cd_categoria) REFERENCES Categoria(cd_categoria) /*Referencia */,
    FOREIGN KEY (cd_tipotransporte) REFERENCES TipoTransporte(cd_tipotransporte)
);

/*Com categoria*/
INSERT INTO Pacote(cd_viagem, cd_hotel, cd_categoria, cd_tipotransporte, cd_cidOrigem, cd_cidDestino, 
nome_pacote, descricao_pacote, dtChekin_hotel, dtChekout_hotel, vl_pacote, img_pacote)
VALUES (1, 1, 1, 2, 2, 3, 'Pacote', 'Descrição', '2021-06-07','2021-06-10', 1890.00, ''),
(1, 1, 1, 1, 3, 1, 'Pacote Oferta', 'Descrição', '2021-06-07','2021-06-10', 1790.00, '');

/*Sem categoria*/
INSERT INTO Pacote(cd_viagem, cd_hotel, cd_tipotransporte, cd_cidOrigem, cd_cidDestino, 
nome_pacote, descricao_pacote, dtChekin_hotel, dtChekout_hotel, vl_pacote, img_pacote)
VALUES (1, 1, 2, 3,1,'Pacote sem categoria 1', 'Descrição', '2021-06-07','2021-06-10',790.00, ''),
(1, 1, 2, 3, 1,'Pacote sem categoria 2', 'Descrição', '2021-06-07','2021-06-10',790.00, '');

/* Criando a tabela Reserva*/
CREATE TABLE Reserva (
    cd_reserva INTEGER not null primary key auto_increment,
    CPF VARCHAR(20) not null,
    cd_cartao INTEGER not null,
    vl_total Decimal(14,0) not null,
	status_reserva bit null,
    FOREIGN KEY (CPF) REFERENCES Cliente (CPF), /*Referencia */
    FOREIGN KEY (cd_cartao) REFERENCES Cartao (cd_cartao) /*Referencia */
);

/* Criando a tabela Itens_Reservas*/
CREATE TABLE ItensReserva(
 cd_itensreserva INTEGER not null primary key,
 cd_pacote INTEGER not null,
 cd_reserva INTEGER not null,
 vl_unit DECIMAL(14,0) not null,
 vl_parcial DECIMAL(14,0) not null,
 qt_itens INTEGER  not null,
 status_itens bit null,
 CPF VARCHAR(20) not null,
 FOREIGN KEY (cd_pacote) REFERENCES Pacote (cd_pacote),  /*Referencia */
 FOREIGN KEY (cd_reserva) REFERENCES Reserva (cd_reserva),
FOREIGN KEY (CPF) REFERENCES Cliente (CPF)  /*Referencia *//*Referencia */
);

-- Selecionando as Tabelas ( Selects de cada tabela);

SELECT * FROM Funcionario;
SELECT * FROM Cliente;
SELECT * FROM Viagem;
SELECT * FROM Transporte;
SELECT * FROM TipoTransporte;
SELECT * FROM Cartao;
SELECT * FROM Categoria;
SELECT * FROM Pacote;
SELECT * FROM Reserva;
SELECT * FROM Cidade;
SELECT * FROM Estado;
SELECT * FROM Hotel;
SELECT * FROM Carrinho;
select * from Cliente;
select * from Reserva;
SELECT * FROM Cartao WHERE CPF = '000.000.000-01';
SELECT * FROM Cidade where cd_estado = 1;
SELECT * FROM Pacote WHERE cd_cidOrigem = 3 and cd_cidDestino = 1;