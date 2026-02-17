package services;

import classes.Cliente;
import classes.Funcionario;
import classes.Pessoa;
import java.util.HashMap;

/**
* Classe responsável pelo gerenciamento dos clientes e funcionarios do sistema.
* 
* Atua como camada de serviço (Service), concentrando:
* - Cadastro
* - Consulta
* - Atualização
* - Remoção
* - Controle de alterações
*/
public class PessoaService {

    private final HashMap<String, Pessoa> pessoas = new HashMap<>();
    private boolean pessoasAtualizados = false;

    // ================== 1. CADASTRO ==================

    /**
     * Cadastra um novo cliente no sistema.
     * 
     * @param cpf     CPF da pessoa (chave única)
     * @param pessoa objeto Pessoa com os dados completos
     * @return true se a pessoa foi cadastrado com sucesso
     *         false se já existir uma pessoa com o mesmo CPF
     */
    public boolean cadastrar(String cpf, Pessoa pessoa) {
        // Evita sobrescrever um cliente já existente
        if (pessoas.containsKey(cpf)) {
            return false;
        }

        pessoas.put(cpf, pessoa);

        // Marca que houve alteração nos dados
        pessoasAtualizados = true;
        return true;
    }

    // ================== 2. CONSULTA ==================

    /**
     * Consulta uma pessoa pelo CPF.
     * 
     * @param cpf CPF da pessoa
     * @return Pessoa correspondente ou null se não existir
     */
    public Pessoa consultar(String cpf) {
        return pessoas.get(cpf); // retorna null se não encontrar
    }

    /**
     * Retorna todos as pessoas cadastrados.
     * 
     * Atenção: retorna a referência direta do HashMap.
     * Ideal para leitura.
     * @return Retorna um hashmap cpm dados da venda ou null se não existir 
     */
    public HashMap<String, Pessoa> listarTodos() {
        return pessoas;
    }
    
    // ================== 3. REMOÇÃO ==================

    /**
     * Remove uma pessoa do sistema.
     * 
     * @param cpf CPF da pessoa a ser removido
     * @return true se a pessoa foi removido
     *         false se a pessoa não existir
     */
    public boolean remover(String cpf) {
        if (!pessoas.containsKey(cpf)) {
            return false;
        }
        pessoas.remove(cpf);
        pessoasAtualizados = true;
        return true;
    }

    // ================== 4. ATUALIZAÇÃO ==================

    /**
     * Atualiza os dados de uma pessoa já cadastrado.
     * 
     * @param cpf          CPF da pessoa (chave)
     * @param pessoaNovo  novo objeto Pessoa com os dados atualizados
     * @return true se a atualização foi realizada
     *         false se a pessoa não existir
     */
    public boolean atualizar(String cpf, Pessoa pessoaNovo) {
        if (!pessoas.containsKey(cpf)) {
            return false;
        }
        pessoas.put(cpf, pessoaNovo);
        pessoasAtualizados = true;
        return true;
    }

    // ================== 5. PRÉ-CADASTROS ==================

    public void carregarPessoasPadrao() {
        cadastrar("111.111.111-11", new Funcionario("Danilo", "111.111.111-11"));
        cadastrar("222.222.222-22", new Funcionario("Gabriel", "222.222.222-22"));
        cadastrar("333.333.333-33", new Cliente("Jackson", "333.333.333-33", "Rua C", "(33)93333-3333"));
        cadastrar("444.444.444-44", new Cliente("Laelson", "444.444.444-44", "Rua D", "(44)94444-4444"));
        cadastrar("555.555.555-55", new Cliente("Maicon", "555.555.555-55", "Rua E", "(55)95555-5555"));
        cadastrar("666.666.666-66", new Cliente("Ryan", "666.666.666-66", "Rua F", "(66)96666-6666"));
    }

    // ================== 6. GETTERS E SETTERS ==================

    public boolean isPessoasAtualizadas() {
        return pessoasAtualizados;
    }

    public void setPessoasAtualizadas(boolean pessoasAtualizados) {
        this.pessoasAtualizados = pessoasAtualizados;
    }
}
