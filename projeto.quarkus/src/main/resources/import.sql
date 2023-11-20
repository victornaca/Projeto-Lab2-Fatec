-- Inserir a primeira instituição financeira
INSERT INTO instituicaofinanceira (nome, cnpj) VALUES ('Banco A', '12345678901');

-- Inserir a segunda instituição financeira
INSERT INTO instituicaofinanceira (nome, cnpj) VALUES ('Banco B', '23456789012');

-- Inserir a terceira instituição financeira
INSERT INTO instituicaofinanceira (nome, cnpj) VALUES ('Banco C', '34567890123');


-- Inserir o primeiro leilão
INSERT INTO leilao (dataInicio, dataFim, dataVisitaInicio, dataVisitaFim, status, endereco, cidade, estado)
VALUES ('2023-11-17 08:00:00', '2023-11-20 08:00:00', '2023-11-18 08:00:00', '2023-11-19 08:00:00', 'Aberto', 'Endereço 1', 'Cidade 1', 'UF');

-- Vincular o primeiro leilão às instituições financeiras (Assumindo que as instituições financeiras têm IDs 1 e 2)
INSERT INTO leilaoinstituicaofinanceira (instituicao_financeira_id, leilao_id)
VALUES (1, 1), (2, 1);

-- Inserir o segundo leilão
INSERT INTO leilao (dataInicio, dataFim, dataVisitaInicio, dataVisitaFim, status, endereco, cidade, estado)
VALUES ('2023-11-25 08:00:00', '2023-11-30 08:00:00', '2023-11-26 08:00:00', '2023-11-29 08:00:00', 'Fechado', 'Endereço 2', 'Cidade 2', 'UF');

-- Vincular o segundo leilão às instituições financeiras (Assumindo que as instituições financeiras têm IDs 2 e 3)
INSERT INTO leilaoinstituicaofinanceira (instituicao_financeira_id, leilao_id)
VALUES (2, 2), (3, 2);

-- Inserir o terceiro leilão
INSERT INTO leilao (dataInicio, dataFim, dataVisitaInicio, dataVisitaFim, status, endereco, cidade, estado)
VALUES ('2023-11-07 08:00:00', '2023-11-11 08:00:00', '2023-11-08 08:00:00', '2023-11-10 08:00:00','Finalizado', 'Endereço 3','Cidade 3', 'UF');

-- Vincular o terceiro leilão às instituições financeiras (Assumindo que as instituições financeiras têm IDs 1 e 3)
INSERT INTO leilaoinstituicaofinanceira (instituicao_financeira_id, leilao_id)
VALUES (1, 3), (3, 3);


-- Inserir um Caminhão associado a um Leilão (Assumindo que o Caminhão tem ID 1 e o Leilão tem ID 1)
INSERT INTO veiculo (marca, modelo, ano, estadoConservacao, status, tipoVeiculo, capacidadeCarga, tipoCarroceria, numeroEixos, temCarreta, leilaoId)
VALUES ('Caminhão A', 'Modelo A', 2020, 'Bom', 'VINCULADO', 'CAMINHAO', 5000, 'Carga Seca', 2, true, 1);

-- Inserir um Carro associado a um Leilão (Assumindo que o Carro tem ID 2 e o Leilão tem ID 2)
INSERT INTO veiculo (marca, modelo, ano, estadoConservacao, status, tipoVeiculo, numeroPortas, capacidadePassageiros, combustivel, tipoTransmissao, arCondicionado, travaEletrica, leilaoId)
VALUES ('Carro B', 'Modelo B', 2019, 'Excelente', 'VINCULADO', 'CARRO', 4, 5, 'Gasolina', 'Automática', true, true, 2);

-- Inserir uma Motocicleta (sem associação a um Leilão)
INSERT INTO veiculo (marca, modelo, ano, estadoConservacao, status, tipoVeiculo, tipoMotocicleta, cilindrada, partidaEletrica, freioAbs)
VALUES ('Motocicleta C', 'Modelo C', 2021, 'Bom', 'NAO VINCULADO', 'MOTOCICLETA', 'Esportiva', 600, true, true);

-- Inserir um Carro associado a um Leilão (Assumindo que o Carro tem ID 2 e o Leilão tem ID 3)
INSERT INTO veiculo (marca, modelo, ano, estadoConservacao, status, tipoVeiculo, numeroPortas, capacidadePassageiros, combustivel, tipoTransmissao, arCondicionado, travaEletrica, leilaoId)
VALUES ('Carro D', 'Modelo D', 2019, 'Excelente', 'VINCULADO', 'CARRO', 4, 5, 'Gasolina', 'Automática', true, true, 3);


-- Inserir um Tablet vinculado a um Leilão (Assumindo que o Tablet tem ID 1 e o Leilão tem ID 1)
INSERT INTO dispositivoinformatica (tipo, modelo, estadoConservacao, status, tipoDispositivoInformatica, fabricante, tamanhoTela, características, memoria, memoriaRAM, leilaoId)
VALUES ('Tablet', 'Tablet A', 'Bom', 'VINCULADO', 'TABLET', 'Fabricante A', 10.1, 'Características A', '32 GB', '4 GB', 1);

-- Inserir um Notebook sem vinculação a um Leilão
INSERT INTO dispositivoinformatica (tipo, modelo, estadoConservacao, status, tipoDispositivoInformatica, fabricante, processador, tamanhoTela, memoria, memoriaRAM, peso)
VALUES ('Notebook', 'Notebook B', 'Excelente', 'NAO VINCULADO', 'NOTEBOOK', 'Fabricante B', 'Processador B', 15.6, '512 GB', '8 GB', '2.0 kg');

-- Inserir um Monitor vinculado a um Leilão (Assumindo que o Monitor tem ID 2 e o Leilão tem ID 2)
INSERT INTO dispositivoinformatica (tipo, modelo, estadoConservacao, status, tipoDispositivoInformatica, fabricante, tamanhoTela, recursos, entradas, peso, leilaoId)
VALUES ('Monitor', 'Monitor C', 'Bom', 'VINCULADO', 'MONITOR', 'Fabricante C', 27.0, 'Recursos C', 'HDMI, VGA', '5.5 kg', 2);

-- Inserir um Celular sem vinculação a um Leilão
INSERT INTO dispositivoinformatica (tipo, modelo, estadoConservacao, status, tipoDispositivoInformatica, fabricante, sistemaOperacional, tamanhoTela, memoria, camera)
VALUES ('Celular', 'Celular D', 'Bom', 'NAO VINCULADO', 'CELULAR', 'Fabricante D', 'Android', 6.0, '128 GB', '16 MP');

-- Inserir outro Tablet sem vinculação a um Leilão
INSERT INTO dispositivoinformatica (tipo, modelo, estadoConservacao, status, tipoDispositivoInformatica, fabricante, tamanhoTela, características, memoria, memoriaRAM, leilaoId)
VALUES ('Tablet', 'Tablet E', 'Bom', 'VINCULADO', 'TABLET', 'Fabricante E', 8.0, 'Características E', '64 GB', '6 GB', 3);



-- Inserir o primeiro cliente
INSERT INTO cliente (nome, email) VALUES ('Cliente A', 'clienteA@email.com');

-- Inserir o segundo cliente
INSERT INTO cliente (nome, email) VALUES ('Cliente B', 'clienteB@email.com');

-- Inserir o terceiro cliente
INSERT INTO cliente (nome, email) VALUES ('Cliente C', 'clienteC@email.com');



-- Inserir o primeiro lance ligando a um Veículo (Assumindo que o Veículo tem ID 1 e o Cliente tem ID 1)
INSERT INTO lance (dataHora, valorInicial, ValorAdicional, Veiculoid, Clienteid) 
VALUES ('2023-10-10 08:00:00', 1000.00, 200.00, 1, 1);

-- Inserir o segundo lance ligando a um Dispositivo de Informática (Assumindo que o Dispositivo tem ID 2 e o Cliente tem ID 2)
INSERT INTO lance (dataHora, valorInicial, ValorAdicional, Dispositivoid, Clienteid) 
VALUES ('2023-10-11 09:30:00', 800.00, 200.00, 2, 2);

-- Inserir o terceiro lance ligando a um Veículo (Assumindo que o Veículo tem ID 3 e o Cliente tem ID 3)
INSERT INTO lance (dataHora, valorInicial, ValorAdicional, Veiculoid, Clienteid) 
VALUES ('2023-10-12 10:45:00', 1200.00, 200.00, 3, 3);

-- Inserir o terceiro lance ligando a um Veículo (Assumindo que o Veículo tem ID 3 e o Cliente tem ID 3)
INSERT INTO lance (dataHora, valorInicial, ValorAdicional, Veiculoid, Clienteid) 
VALUES ('2023-10-12 10:45:00', 1200.00, 200.00, 4, 3);

-- Inserir o segundo lance ligando a um Dispositivo de Informática (Assumindo que o Dispositivo tem ID 2 e o Cliente tem ID 2)
INSERT INTO lance (dataHora, valorInicial, ValorAdicional, Dispositivoid, Clienteid) 
VALUES ('2023-10-11 09:30:00', 800.00, 200.00, 3, 1);

-- Inserir o segundo lance ligando a um Dispositivo de Informática (Assumindo que o Dispositivo tem ID 2 e o Cliente tem ID 2)
INSERT INTO lance (dataHora, valorInicial, ValorAdicional, Dispositivoid, Clienteid) 
VALUES ('2023-10-11 09:30:00', 800.00, 200.00, 4, 3);

-- Inserir o segundo lance ligando a um Dispositivo de Informática (Assumindo que o Dispositivo tem ID 2 e o Cliente tem ID 2)
INSERT INTO lance (dataHora, valorInicial, ValorAdicional, Dispositivoid, Clienteid) 
VALUES ('2023-10-11 09:30:00', 800.00, 200.00, 5, 3);
