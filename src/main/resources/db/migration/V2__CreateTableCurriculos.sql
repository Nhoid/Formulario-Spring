CREATE TABLE curriculos (
                            id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
                            nome VARCHAR(255) NOT NULL,
                            email VARCHAR(255) NOT NULL UNIQUE,
                            telefone VARCHAR(20) NOT NULL UNIQUE,
                            vaga_id BIGINT UNSIGNED NOT NULL,
                            escolaridade ENUM('FUNDAMENTAL_INCOMPLETO', 'FUNDAMENTAL', 'MEDIO_INCOMPLETO', 'MEDIO', 'SUPERIOR_INCOMPLETO', 'SUPERIOR') NOT NULL,
                            observacoes TEXT,
                            arquivo_url VARCHAR(255) NOT NULL,
                            desqualificado BOOLEAN NOT NULL DEFAULT FALSE,
                            ip_envio VARCHAR(45) NOT NULL,
                            data_hora_envio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT fk_vaga FOREIGN KEY (vaga_id) REFERENCES vagas(id)
);