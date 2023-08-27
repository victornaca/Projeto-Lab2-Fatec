use projeto;

DROP TABLE IF EXISTS dispositivo;
DROP TABLE IF EXISTS veiculo;
DROP TABLE IF EXISTS leilao;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS instituicao_financeira;

-- Drop user privileges
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'user'@'localhost';
-- Delete user
DROP USER 'user'@'localhost';

-- Drop schema
DROP SCHEMA projeto;

-- Drop database
DROP DATABASE projeto;
