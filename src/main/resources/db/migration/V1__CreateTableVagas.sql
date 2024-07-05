CREATE TABLE vagas (
                       id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                       nome_da_vaga VARCHAR(255) NOT NULL UNIQUE,
                       data_de_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);