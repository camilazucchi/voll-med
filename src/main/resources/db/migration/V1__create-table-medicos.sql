create table medicos(

    id serial primary key,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    especialidade varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(100) not null,
    complemento varchar(100),
    numero varchar(20),
    uf varchar(2) not null,
    cidade varchar(100) not null

);