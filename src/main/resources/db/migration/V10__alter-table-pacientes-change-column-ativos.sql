UPDATE pacientes SET ativo = TRUE WHERE ativo IS NULL;
ALTER TABLE pacientes ALTER COLUMN ativo SET NOT NULL;