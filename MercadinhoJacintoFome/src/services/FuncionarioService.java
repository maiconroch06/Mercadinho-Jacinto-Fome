package services;

import classes.Funcionario;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class FuncionarioService {

    private final HashMap<String, Funcionario> listaDeFuncionarios = new HashMap<>();

    private boolean funcionariosAtualizados = false;

    public FuncionarioService() {}

    // ---------- 1. CADASTRO ----------
    public void cadastrar(String cpf, Funcionario funcionario) {
        listaDeFuncionarios.put(cpf, funcionario);
        funcionariosAtualizados = true;
    }

    // ----------- 2. CONSULTA -----------
    public Funcionario consultar(String cpf) {
        if (!listaDeFuncionarios.containsKey(cpf)) {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
            return null;
        }
        return listaDeFuncionarios.get(cpf);
    }
    
    public HashMap<String, Funcionario> listarTodos() {
        return listaDeFuncionarios;
    }
    
    // ----------- 3. REMOÇÃO -----------
    public void remover(String cpf) {
        listaDeFuncionarios.remove(cpf);
        funcionariosAtualizados = true;
    }

    // ----------- 4. ATUALIZAÇÃO -----------
    public boolean atualizar(String cpf, Funcionario funcionarioAtualizado) {
        if (!listaDeFuncionarios.containsKey(cpf)) {
            JOptionPane.showMessageDialog(null, "Dados não encontrados!");
            return false;
        }

        listaDeFuncionarios.put(cpf, funcionarioAtualizado);
        funcionariosAtualizados = true;
        return true;
    }

    // ----------- 5. PRÉ-CADASTROS -----------
    public void carregarFuncionariosPadrao() {
        cadastrar("111.111.111-11", new Funcionario("Danilo", "111.111.111-11"));
        cadastrar("222.222.222-22", new Funcionario("Gabriel", "222.222.222-22"));
    }

    // ----------- 6. GETTERS E SETTERS -----------
    public boolean isFuncionariosAtualizados() {
        return funcionariosAtualizados;
    }

    public void setFuncionariosAtualizados(boolean funcionariosAtualizados) {
        this.funcionariosAtualizados = funcionariosAtualizados;
    }
}
