-- Criação da tabela 'paciente'
CREATE TABLE pacientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    endereco_logradouro VARCHAR(255) NOT NULL,
    endereco_bairro VARCHAR(255) NOT NULL,
    endereco_cep VARCHAR(8) NOT NULL,
    endereco_cidade VARCHAR(255) NOT NULL,
    endereco_uf VARCHAR(2) NOT NULL,
    endereco_numero VARCHAR(10),
    endereco_complemento VARCHAR(255)
);

-- Criação de um índice único no campo 'cpf' para garantir unicidade
CREATE UNIQUE INDEX idx_paciente_cpf ON pacientes (cpf);
