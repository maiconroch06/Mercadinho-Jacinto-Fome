# Mercadinho Jacinto Fome 🛒 (Banco de Dados)

![Linguagem](https://img.shields.io/badge/language-Java-blue.svg)
![Status](https://img.shields.io/badge/status-%20Finalizado-green)

Sistema de gerenciamento de estoque e ponto de venda desenvolvido em Java na IDE NetBeans. O projeto foca na aplicação de conceitos de Programação Orientada a Objetos (POO) e **persistência de dados em Banco de Dados MySQL**. Esta é a versão com integração de banco de dados, para a versão com persistência em memória, acesse a [branch main](https://github.com/maiconroch06/Mercadinho-Jacinto-Fome).

## 🚀 Funcionalidades

- **Gerenciamento de Estoque**: Cadastro, consulta, atualização e exclusão de produtos.
- **Cadastro de Clientes e Funcionários**: Controle completo de dados de pessoas vinculadas ao mercadinho.
- **Sistema de Vendas**: Fluxo completo de venda, desde a seleção de produtos até o pagamento.
- **Múltiplas Formas de Pagamento**: PIX, Débito, Crédito e Espécie. (Sem API 😥)
- **Interface**: Utilização de atalhos de teclado para agilizar o atendimento.

## ▶ Demonstração do projeto
<div align="center">

  [![Assista ao vídeo](https://img.youtube.com/vi/cDnx86qTNS0/0.jpg)](https://www.youtube.com/watch?v=cDnx86qTNS0)

</div>
  Mercadinho Jacinto Fome | Gerenciamento de estoque e venda - Projeto acadêmico desenvolvido em Java

## 📂 Estrutura do Projeto (Disposição de Arquivos)

A estrutura de pacotes do projeto está organizada da seguinte forma:

- `MercadinhoJacintoFome/SQL/`: Contém os scripts SQL (`estoque.sql`) para criação do banco de dados e tabelas, além de dados iniciais.
- `src/classes/`: Contém as entidades de domínio, como `Produto`, `Cliente`, `Funcionario` e `RegistroVenda`.
- `src/conexao/`: Camada de persistência responsável pela comunicação com o banco de dados MySQL via JDBC.
- `src/interfaces/`: Interface gráfica do usuário (GUI) desenvolvida com Swing (NetBeans Matisse).
  - `atualizar/`: Formulários para edição de registros existentes.
  - `cadastrar/`: Formulários para criação de novos registros.
  - `venda/`: Telas relacionadas ao processo de venda e pagamento.
- `src/utilidades/`: Classes utilitárias para manipulação de tabelas, configuração de atalhos e outras funções auxiliares.

## ⌨️ Atalhos do Teclado

O sistema foi projetado para ser operado rapidamente através de atalhos:

### Tela Principal
- **F1**: Nova Venda
- **F2**: Cadastrar Produto
- **F3**: Cadastrar Cliente
- **F4**: Cadastrar Funcionário
- **F5**: Atualizar Produto
- **F6**: Atualizar Cliente
- **F7**: Atualizar Funcionário
- **F8**: Exibir Legenda de Atalhos
- **F11**: Alternar Tela Cheia
- **ESC**: Sair do Programa
- **Double Click (Tabelas)**: Abre a tela de edição/detalhes do item selecionado.

### Tela de Venda (PDV)
- **F1**: Ir para Pagamento
- **F8**: Legenda de Atalhos
- **ENTER (no campo Código)**: Pesquisa o produto.
- **ENTER (com produto selecionado)**: Adiciona o produto ao carrinho.
- **ESC**: Volta para a tela principal.
- **Double Click (Tabela Produtos)**: Adiciona ao carrinho.
- **Double Click (Tabela Carrinho)**: Remove do carrinho.

### Tela de Pagamento
- **F1**: Selecionar Atendente
- **F2**: Método PIX
- **F3**: Método Débito
- **F4**: Método Crédito
- **F5**: Método Espécie
- **ENTER**: Finalizar Venda
- **ESC**: Voltar para a tela de venda.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java 8
- **GUI**: Swing (NetBeans)
- **Banco de Dados**: MySQL

## 🏁 Como Executar

### Pré-requisitos
- Java JDK 8 ou superior instalado.
- Servidor MySQL (como XAMPP, WAMP ou instalação direta).
- Driver JDBC do MySQL (incluído nas bibliotecas do projeto NetBeans).

### Configuração do Banco de Dados
1. Certifique-se de que seu servidor MySQL está rodando.
2. Importe o script localizado em `MercadinhoJacintoFome/SQL/estoque.sql` para criar o banco de dados e as tabelas.
3. Se necessário, ajuste as credenciais de conexão em `MercadinhoJacintoFome/src/conexao/ConexaoBD.java`.

### Execução
1. Clone o repositório.
2. Abra o projeto no **NetBeans IDE**.
3. Clique em "Run" ou pressione F6.

---
Desenvolvido como projeto prático para a disciplina de Programação Orientada a Objetos durante a faculdade de TADS.
             
