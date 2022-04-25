create table produto (
	codigo_interno int not null,
	codigo_ean bigint not null,
	nome_produto varchar(60),
	quantidade_estoque bigint,
	valor_venda double(16,2),
	primary key(codigo_interno));

create table ean (
	codigo_ean bigint not null,
	codigo_interno int not null,	
	primary key(codigo_ean),
	foreign key(codigo_interno) references produto(codigo_interno));

create table usuarios (
  login varchar(20) not null,
  senha varchar(100) not null);

create table fornecedores (
	nome varchar(60) not null,
	cnpj bigint not null,
	primary key(cnpj)
);

create table setores (
	nome varchar(60) not null,
	primary key(nome)
);

create table loja (
	codigo integer not null,
	nome varchar(60) not null,
	cnpj bigint not null,
	rua varchar(60) not null,
	numero integer not null,
	cep char(8) not null,
    primary key(codigo)
);