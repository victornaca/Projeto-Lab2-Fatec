INSERT INTO Veiculo (marca, modelo, ano, estadoConservacao, status)
VALUES ('Toyota', 'Corolla', 2022, 'Novo', 'VENDIDO');
INSERT INTO Veiculo (marca, modelo, ano, estadoConservacao, status)
VALUES ('Ford', 'Mustang', 2021, 'Usado', 'NAO VINCULADO');
INSERT INTO Veiculo (marca, modelo, ano, estadoConservacao, status)
VALUES ('Honda', 'Civic', 2020, 'Usado', 'NAO VINCULADO');
INSERT INTO Veiculo (marca, modelo, ano, estadoConservacao, status)
VALUES ('Volkswagen', 'Golf', 2019, 'Usado', 'VENDIDO');
INSERT INTO Veiculo (marca, modelo, ano, estadoConservacao, status)
VALUES ('Chevrolet', 'Cruze', 2022, 'Novo', 'VENDIDO');


INSERT INTO Leilao (dataOcorrencia, dataVisita, status, endereco, cidade, estado)
VALUES ('2023-09-10', '2023-09-05', 'Agendado', 'Rua das Flores, 123', 'São Paulo', 'SP');
INSERT INTO Leilao (dataOcorrencia, dataVisita, status, endereco, cidade, estado)
VALUES ('2023-10-15', '2023-10-10', 'Em andamento', 'Avenida da Praia, 456', 'Rio de Janeiro', 'RJ');
INSERT INTO Leilao (dataOcorrencia, dataVisita, status, endereco, cidade, estado)
VALUES ('2023-11-20', '2023-11-15', 'Encerrado', 'Rua das Montanhas, 789', 'Belo Horizonte', 'MG');
INSERT INTO Leilao (dataOcorrencia, dataVisita, status, endereco, cidade, estado)
VALUES ('2023-12-05', '2023-11-30', 'Agendado', 'Rua das Palmeiras, 567', 'Porto Alegre', 'RS');
INSERT INTO Leilao (dataOcorrencia, dataVisita, status, endereco, cidade, estado)
VALUES ('2024-01-20', '2024-01-15', 'Em andamento', 'Avenida do Sol, 890', 'Salvador', 'BA');
INSERT INTO Leilao (dataOcorrencia, dataVisita, status, endereco, cidade, estado)
VALUES ('2024-02-15', '2024-02-10', 'Encerrado', 'Rua das Estrelas, 1234', 'Curitiba', 'PR');


INSERT INTO InstituicaoFinanceira (nome, cnpj) VALUES ('Banco A', '1234567890');
INSERT INTO InstituicaoFinanceira (nome, cnpj) VALUES ('Banco B', '9876543210');
INSERT INTO InstituicaoFinanceira (nome, cnpj) VALUES ('Banco C', '5678901234');
INSERT INTO InstituicaoFinanceira (nome, cnpj) VALUES ('Banco D', '4321098765');
INSERT INTO InstituicaoFinanceira (nome, cnpj) VALUES ('Banco E', '2468013579');
INSERT INTO InstituicaoFinanceira (nome, cnpj) VALUES ('Banco F', '1357924680');


INSERT INTO DispositivoInformática (tipo, modelo) VALUES ('Laptop', 'Modelo A');
INSERT INTO DispositivoInformática (tipo, modelo) VALUES ('Desktop', 'Modelo B');
INSERT INTO DispositivoInformática (tipo, modelo) VALUES ('Tablet', 'Modelo C');
INSERT INTO DispositivoInformática (tipo, modelo) VALUES ('Smartphone', 'Modelo D');
INSERT INTO DispositivoInformática (tipo, modelo) VALUES ('Servidor', 'Modelo E');
INSERT INTO DispositivoInformática (tipo, modelo) VALUES ('Impressora', 'Modelo F');


INSERT INTO Cliente (nome, email) VALUES ('João Silva', 'joao@email.com');
INSERT INTO Cliente (nome, email) VALUES ('Maria Santos', 'maria@email.com');
INSERT INTO Cliente (nome, email) VALUES ('Pedro Alves', 'pedro@email.com');
INSERT INTO Cliente (nome, email) VALUES ('Ana Souza', 'ana@email.com');
INSERT INTO Cliente (nome, email) VALUES ('Luiz Oliveira', 'luiz@email.com');
INSERT INTO Cliente (nome, email) VALUES ('Carla Fernandes', 'carla@email.com');
