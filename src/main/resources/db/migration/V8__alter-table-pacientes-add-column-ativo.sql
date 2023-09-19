ALTER TABLE pacientes ADD COLUMN ativo SMALLINT;
UPDATE pacientes SET ativo = 1;