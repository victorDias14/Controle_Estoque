create table produto (
	codigo_interno int not null,
	codigo_ean bigint not null,
	nome_produto varchar(60),
	quantidade_estoque bigint,
	valor_medio double(16,3),
	valor_venda double(16,2),
	primary key(codigo_interno));

create table usuarios (
  login varchar(20) not null,
  senha varchar(100) not null);