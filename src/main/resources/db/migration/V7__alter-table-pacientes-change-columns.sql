-- Renomeando colunas
ALTER TABLE pacientes
RENAME COLUMN endereco_logradouro TO logradouro;

ALTER TABLE pacientes
RENAME COLUMN endereco_bairro TO bairro;

ALTER TABLE pacientes
RENAME COLUMN endereco_cep TO cep;

ALTER TABLE pacientes
RENAME COLUMN endereco_cidade TO cidade;

ALTER TABLE pacientes
RENAME COLUMN endereco_uf TO uf;

ALTER TABLE pacientes
RENAME COLUMN endereco_numero TO numero;

ALTER TABLE pacientes
RENAME COLUMN endereco_complemento TO complemento;
