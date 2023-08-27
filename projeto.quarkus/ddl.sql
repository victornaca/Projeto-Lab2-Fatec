create schema projeto;

use projeto;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on projeto.* to user@'localhost';


CREATE TABLE dispositivo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(255),
    modelo VARCHAR(255)
);

CREATE TABLE veiculo (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(255),
    modelo VARCHAR(255)
);

CREATE TABLE leilao (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    data_ocorrencia DATE,
    data_visita DATE,
    local VARCHAR(255)
);

CREATE TABLE cliente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE instituicao_financeira (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    cnpj VARCHAR(255)
);
