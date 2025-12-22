package services;

import classes.Cliente;
import java.util.HashMap;

public class ClienteService {

    private final HashMap<String, Cliente> clientes = new HashMap<>();
    private boolean clientesAtualizados = false;

    // ----------- 1. CADASTRO -----------
    public boolean cadastrar(String cpf, Cliente cliente) {
        if (clientes.containsKey(cpf)) {
            return false; // Já existe
        }
        clientes.put(cpf, cliente);
        clientesAtualizados = true;
        return true;
    }

    // ----------- 2. CONSULTA -----------
    public Cliente consultar(String cpf) {
        return clientes.get(cpf); // retorna null se não encontrar
    }

    public HashMap<String, Cliente> listarTodos() {
        return clientes;
    }

    // ----------- 3. REMOÇÃO -----------
    public boolean remover(String cpf) {
        if (!clientes.containsKey(cpf)) {
            return false;
        }
        clientes.remove(cpf);
        clientesAtualizados = true;
        return true;
    }

    // ----------- 4. ATUALIZAÇÃO -----------
    public boolean atualizar(String cpf, Cliente clienteNovo) {
        if (!clientes.containsKey(cpf)) {
            return false;
        }
        clientes.put(cpf, clienteNovo);
        clientesAtualizados = true;
        return true;
    }

    // ----------- 5. PRÉ-CADASTROS -----------
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

    // ----------- 6. GETTERS E SETTERS -----------
    public boolean isClientesAtualizados() {
        return clientesAtualizados;
    }

    public void setClientesAtualizados(boolean clientesAtualizados) {
        this.clientesAtualizados = clientesAtualizados;
    }
}

