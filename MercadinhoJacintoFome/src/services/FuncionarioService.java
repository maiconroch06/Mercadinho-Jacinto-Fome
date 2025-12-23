package services;

import classes.Funcionario;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Classe responsável pelo gerenciamento dos funcionários do sistema.
 * 
 * Atua como camada de serviço (Service), centralizando:
 * - Cadastro
 * - Consulta
 * - Atualização
 * - Remoção
 * - Controle de alterações
 */
public class FuncionarioService {

    /**
     * Estrutura que armazena os funcionários cadastrados.
     * A chave é o CPF (identificador único).
     */
    private final HashMap<String, Funcionario> listaDeFuncionarios = new HashMap<>();

    /**
     * Flag que indica se houve alterações na lista de funcionários.
     * Pode ser utilizada para:
     * - Persistência
     * - Atualização de interfaces
     * - Controle de estado
     */
    private boolean funcionariosAtualizados = false;

    /**
     * Construtor padrão.
     */
    public FuncionarioService() {}

    // ================== 1. CADASTRO ==================

    /**
     * Cadastra um funcionário no sistema.
     * 
     * @param cpf CPF do funcionário (chave do HashMap)
     * @param funcionario objeto Funcionario contendo os dados
     * 
     * Observação:
     * Atualmente não verifica duplicidade de CPF.
     * Caso um CPF já exista, ele será sobrescrito.
     */
    public void cadastrar(String cpf, Funcionario funcionario) {
        listaDeFuncionarios.put(cpf, funcionario);
        funcionariosAtualizados = true;
    }

    // ================== 2. CONSULTA ==================

    /**
     * Consulta um funcionário pelo CPF.
     * 
     * @param cpf CPF do funcionário
     * @return Funcionario correspondente ou null se não encontrado
     * 
     * Observação:
     * Exibe uma mensagem na tela caso o funcionário não exista.
     */
    public Funcionario consultar(String cpf) {

        if (!listaDeFuncionarios.containsKey(cpf)) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
            return null;
        }

        return listaDeFuncionarios.get(cpf);
    }

    /**
     * Retorna todos os funcionários cadastrados.
     * 
     * @return HashMap contendo todos os funcionários
     */
    public HashMap<String, Funcionario> listarTodos() {
        return listaDeFuncionarios;
    }

    // ================== 3. REMOÇÃO ==================

    /**
     * Remove um funcionário do sistema.
     * 
     * @param cpf CPF do funcionário a ser removido
     * 
     * Observação:
     * Caso o CPF não exista, o método não gera erro.
     */
    public void remover(String cpf) {
        listaDeFuncionarios.remove(cpf);
        funcionariosAtualizados = true;
    }

    // ================== 4. ATUALIZAÇÃO ==================

    /**
     * Atualiza os dados de um funcionário existente.
     * 
     * @param cpf CPF do funcionário
     * @param funcionarioAtualizado objeto com os dados atualizados
     * @return true se a atualização foi realizada
     *         false se o funcionário não existir
     */
    public boolean atualizar(String cpf, Funcionario funcionarioAtualizado) {

        if (!listaDeFuncionarios.containsKey(cpf)) {
            JOptionPane.showMessageDialog(null, "Dados não encontrados!");
            return false;
        }

        listaDeFuncionarios.put(cpf, funcionarioAtualizado);
        funcionariosAtualizados = true;
        return true;
    }

    // ================== 5. PRÉ-CADASTROS ==================

    /**
     * Carrega funcionários padrão no sistema.
     * 
     * Método utilizado para:
     * - Testes
     * - Ambiente de desenvolvimento
     * - Demonstrações
     */
    public void carregarFuncionariosPadrao() {
        cadastrar("111.111.111-11", new Funcionario("Danilo", "111.111.111-11"));
        cadastrar("222.222.222-22", new Funcionario("Gabriel", "222.222.222-22"));
    }

    // ================== 6. GETTERS E SETTERS ==================

    /**
     * Indica se a lista de funcionários foi alterada.
     */
    public boolean isFuncionariosAtualizados() {
        return funcionariosAtualizados;
    }

    /**
     * Permite controlar manualmente o estado de atualização.
     */
    public void setFuncionariosAtualizados(boolean funcionariosAtualizados) {
        this.funcionariosAtualizados = funcionariosAtualizados;
    }
}
