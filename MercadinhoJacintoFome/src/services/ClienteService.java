package services;

import classes.Cliente;
import java.util.HashMap;

/**
 * Classe responsável pelo gerenciamento dos clientes do sistema.
 * 
 * Atua como camada de serviço (Service), concentrando:
 * - Cadastro
 * - Consulta
 * - Atualização
 * - Remoção
 * - Controle de alterações
 */
public class ClienteService {

    /**
     * Estrutura que armazena os clientes cadastrados.
     * A chave é o CPF (único para cada cliente).
     */
    private final HashMap<String, Cliente> clientes = new HashMap<>();

    /**
     * Flag que indica se houve alterações nos dados dos clientes.
     * Pode ser usada para:
     * - Persistência
     * - Atualização de telas
     * - Controle de sincronização
     */
    private boolean clientesAtualizados = false;

    // ================== 1. CADASTRO ==================

    /**
     * Cadastra um novo cliente no sistema.
     * 
     * @param cpf     CPF do cliente (chave única)
     * @param cliente objeto Cliente com os dados completos
     * @return true se o cliente foi cadastrado com sucesso
     *         false se já existir um cliente com o mesmo CPF
     */
    public boolean cadastrar(String cpf, Cliente cliente) {
        // Evita sobrescrever um cliente já existente
        if (clientes.containsKey(cpf)) {
            return false;
        }

        clientes.put(cpf, cliente);

        // Marca que houve alteração nos dados
        clientesAtualizados = true;
        return true;
    }

    // ================== 2. CONSULTA ==================

    /**
     * Consulta um cliente pelo CPF.
     * 
     * @param cpf CPF do cliente
     * @return Cliente correspondente ou null se não existir
     */
    public Cliente consultar(String cpf) {
        return clientes.get(cpf); // retorna null se não encontrar
    }

    /**
     * Retorna todos os clientes cadastrados.
     * 
     * Atenção: retorna a referência direta do HashMap.
     * Ideal para leitura.
     */
    public HashMap<String, Cliente> listarTodos() {
        return clientes;
    }

    // ================== 3. REMOÇÃO ==================

    /**
     * Remove um cliente do sistema.
     * 
     * @param cpf CPF do cliente a ser removido
     * @return true se o cliente foi removido
     *         false se o cliente não existir
     */
    public boolean remover(String cpf) {

        if (!clientes.containsKey(cpf)) {
            return false;
        }

        clientes.remove(cpf);

        // Marca que houve alteração
        clientesAtualizados = true;
        return true;
    }

    // ================== 4. ATUALIZAÇÃO ==================

    /**
     * Atualiza os dados de um cliente já cadastrado.
     * 
     * @param cpf          CPF do cliente (chave)
     * @param clienteNovo  novo objeto Cliente com os dados atualizados
     * @return true se a atualização foi realizada
     *         false se o cliente não existir
     */
    public boolean atualizar(String cpf, Cliente clienteNovo) {

        if (!clientes.containsKey(cpf)) {
            return false;
        }

        clientes.put(cpf, clienteNovo);

        // Marca que houve alteração
        clientesAtualizados = true;
        return true;
    }

    // ================== 5. PRÉ-CADASTROS ==================

    /**
     * Carrega clientes padrão no sistema.
     * 
     * Método útil para:
     * - Testes
     * - Demonstrações
     * - Ambiente de desenvolvimento
     */
    public void carregarClientesPadrao() {

        cadastrar("333.333.333-33",
                new Cliente("Jackson", "333.333.333-33", "Rua C", "(33)93333-3333"));

        cadastrar("444.444.444-44",
                new Cliente("Laelson", "444.444.444-44", "Rua D", "(44)94444-4444"));

        cadastrar("555.555.555-55",
                new Cliente("Maicon", "555.555.555-55", "Rua E", "(55)95555-5555"));

        cadastrar("666.666.666-66",
                new Cliente("Ryan", "666.666.666-66", "Rua F", "(66)96666-6666"));
    }

    // ================== 6. GETTERS E SETTERS ==================

    /**
     * Indica se os dados dos clientes foram alterados.
     */
    public boolean isClientesAtualizados() {
        return clientesAtualizados;
    }

    /**
     * Permite controlar manualmente o estado de atualização.
     */
    public void setClientesAtualizados(boolean clientesAtualizados) {
        this.clientesAtualizados = clientesAtualizados;
    }
}
