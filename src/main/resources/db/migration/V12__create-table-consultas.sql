CREATE TABLE consulta (
    id SERIAL PRIMARY KEY,
    medico_id BIGINT,
    paciente_id BIGINT,
    data TIMESTAMP,
    FOREIGN KEY (medico_id) REFERENCES medicos(id),
    FOREIGN KEY (paciente_id) REFERENCES pacientes(id)
);