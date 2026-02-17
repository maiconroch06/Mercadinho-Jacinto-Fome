create database estoque;

use estoque;

create table pessoa (
	id_pessoa int auto_increment primary key,
    nome varchar(200) not null,
    cpf char(14) not null unique
);

create table funcionario (
	id_funcionario int auto_increment primary key,
	id_pessoa int not null,
    foreign key (id_pessoa) references pessoa(id_pessoa)
);

create table cliente (
    id_cliente int auto_increment primary key,
    id_pessoa int not null,
    telefone varchar(20),
    endereco varchar(50),
    foreign key (id_pessoa) references pessoa(id_pessoa)
);

create table produto (
	id_produto int auto_increment primary key,
    codigo varchar(100) not null unique,
    descricao varchar(150) not null,
    quantidade int unsigned not null,
    valorUnitario decimal(10,2) not null
);

create table historicoVenda (
	id_venda int auto_increment primary key,
    
    id_funcionario int,
    id_cliente int,
    
    metodo enum('PIX','DEBITO','CREDITO', 'ESPECIE'),

    foreign key (id_funcionario) references funcionario(id_funcionario),
    foreign key (id_cliente) references cliente(id_cliente)
);

create table item_venda (
	id_item int auto_increment primary key,

	id_venda int not null,
	id_produto int not null,

	quantidade int not null,
	valor_unitario decimal(10,2) not null,

	foreign key (id_venda) references historicoVenda(id_venda)
    on delete cascade,
	foreign key (id_produto) references produto(id_produto)
);

select 
	v.id_venda,
	pf.cpf as cpf_funcionario,
    pc.cpf as cpf_cliente,
    v.metodo,
    sum(iv.quantidade) as itens_totais,
    sum(iv.valor_unitario * iv.quantidade) as valor_total
from historicoVenda v
join funcionario f on f.id_funcionario = v.id_funcionario
join pessoa pf on pf.id_pessoa = f.id_pessoa
join cliente c on c.id_cliente = v.id_cliente
join pessoa pc on pc.id_pessoa = c.id_pessoa
join item_venda iv on iv.id_venda = v.id_venda
group by v.id_venda, pf.cpf, pc.cpf;