# DEVS que pariciparam do projeto #
insert into pessoa (nome, cpf) values
('Danilo', '11111111111'),
('Gabriel', '22222222222'),
('Jackson', '33333333333'),
('Laelson', '44444444444'),
('Maicon', '55555555555'),
('Ryan', '66666666666');

# Funcionarios #
insert into funcionario (id_pessoa) values
(1), -- Danilo
(2); -- Gabriel

# Clientes #
insert into cliente (id_pessoa, telefone, endereco) values
(3, "11111111111", "Rua A"), -- Jackson
(4, "22222222222", "Rua B"), -- Laelson
(5, "33333333333", "Rua C"), -- Maicon
(6, "44444444444", "Rua D"); -- Ryan

# 18 produtos #
insert into produto (codigo, descricao, quantidade, valorUnitario) values
('001', 'Arroz', 10, 5.99),
('002', 'Feijão', 20, 7.50),
('003', 'Macarrão', 15, 4.25),
('004', 'Açúcar', 18, 3.89),
('005', 'Café', 12, 14.90),
('006', 'Óleo de Soja', 9, 7.99),
('007', 'Leite 1L', 25, 5.49),
('008', 'Manteiga', 8, 8.90),
('009', 'Detergente', 30, 2.39),
('010', 'Refrigerante 2L', 14, 9.50),
('011', 'Achocolatado', 16, 7.99),
('012', 'Biscoito', 35, 3.50),
('013', 'Margarina', 20, 5.25),
('014', 'Detergente', 28, 2.10),
('015', 'Sabão em Pó', 17, 12.90),
('016', 'Papel Higiênico', 50, 13.50),
('017', 'Creme Dental', 27, 4.99),
('018', 'Shampoo', 19, 10.99);
