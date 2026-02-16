package conexao;

import classes.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexaoFuncionario extends ConexaoBD {
    
    private boolean funcionariosAtualizados = false;
        
    public boolean cadastrarFuncionario(Funcionario f) {
        conectar();
        try {
            // 1) inserir funcionario
            String sqlPessoa = "INSERT INTO pessoa (nome, cpf) VALUES (?,?)";
            estado = con.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
            estado.setString(1, f.getNome());
            estado.setString(2, f.getCpf());
            estado.executeUpdate();
            
            ResultSet rs = estado.getGeneratedKeys();
            rs.next();
            int id_Pessoa = rs.getInt(1);
            
            // 2) inseir funcionario
            String sqlFuncionario = "INSERT INTO funcionario (id_pessoa) VALUES (?)";
            estado = con.prepareStatement(sqlFuncionario);
            estado.setInt(1, id_Pessoa);
            estado.executeUpdate();
            
            setFuncionariosAtualizados(true);
            //JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso");
            System.out.println("Funcionario cadastrado com sucesso");
            return true;
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao cadastrar funcionario");
            System.out.println("Erro ao cadastrar funcionario");
            return false;
        }
    }
    
    public void atualizarFuncionario(Funcionario f){
        Integer idPessoa = buscarIdPessoaPorCpf(f.getCpf());
        if (idPessoa == null) {
            //JOptionPane.showMessageDialog(null, "CPF não encontrado");
            System.out.println("CPF não encontrado");
            return;
        }
        
        conectar();
        try {
            // pessoa
            String sqlPessoa = "UPDATE pessoa SET nome = ?, cpf = ? WHERE id_pessoa = ?";
            estado = con.prepareStatement(sqlPessoa);
            estado.setString(1, f.getNome());
            estado.setString(2, f.getCpf());
            estado.setInt(3, idPessoa);
            estado.executeUpdate();       
            
            setFuncionariosAtualizados(true);
            
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao atualizar");
            System.out.println("Erro ao atualizar");
        }
        
    }

    public void removerFuncionario(String cpf){
        Integer id_Pessoa = buscarIdPessoaPorCpf(cpf);
        if (id_Pessoa == null) {
            //JOptionPane.showMessageDialog(null, "CPF não encontrado");
            System.out.println("CPF não encontrado");
            return;
        }
        
        conectar();
        try {
            // 1) remove funcionario
            String sqlFuncionario= "DELETE FROM funcionario WHERE id_Pessoa = ?";
            estado = con.prepareStatement(sqlFuncionario);
            estado.setInt(1, id_Pessoa);
            estado.executeUpdate();

            // 2) remove pessoa
            String sqlPessoa= "DELETE FROM pessoa WHERE id_pessoa = ?";
            estado = con.prepareStatement(sqlPessoa);
            estado.setInt(1, id_Pessoa);
            estado.executeUpdate();

            setFuncionariosAtualizados(true);

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao remover");
            System.out.println("Erro ao remover");
        }
    }
        
    private Integer buscarIdPessoaPorCpf(String cpf) {
        String sql = "SELECT f.id_pessoa "
                + "FROM pessoa p join funcionario f "
                + "on p.id_pessoa = f.id_pessoa "
                + "WHERE cpf = ?";

        conectar();
        try {
            estado = con.prepareStatement(sql);
            estado.setString(1, cpf);
            ResultSet rs = estado.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_pessoa");
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Erro ao buscar pessoa");
            System.out.println("Erro ao buscar pessoa");
        }

        return null;
    }
    
    public Funcionario consultarFuncionario(String cpf) {
        Integer id_Pessoa = buscarIdPessoaPorCpf(cpf);
        if (id_Pessoa == null) {
            //JOptionPane.showMessageDialog(null, "CPF não encontrado");
            System.out.println("CPF não encontrado");
            return null;
        }
        
        String sql = "SELECT p.nome, p.cpf "
                + "FROM pessoa p join funcionario f "
                + "on p.id_pessoa = f.id_pessoa "
                + "WHERE f.id_Pessoa = ?";
        
        conectar();
        try {
            estado = con.prepareStatement(sql);
            estado.setInt(1, id_Pessoa);
            ResultSet rs = estado.executeQuery();

            if (rs.next()) {
                Funcionario f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                return f;
            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao consultar por CPF");
            System.out.println("Erro ao consultar por CPF");
        }

        return null;
    }

    
    public ArrayList<Funcionario> consultarFuncionarios(){
        String sql = "SELECT p.nome, p.cpf "
                + "FROM pessoa p join funcionario f "
                + "on p.id_pessoa = f.id_pessoa";
        
        ArrayList<Funcionario> lista = new ArrayList<>();
        conectar();
        try {
            estado = con.prepareStatement(sql);
            ResultSet rs = estado.executeQuery();
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setNome(rs.getString("nome"));
                f.setCpf(rs.getString("cpf"));
                lista.add(f);
            }
            
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao consultar");
            System.out.println("Erro ao consultar funcionarios - Pode está vazia");
        }
        
        return lista;
    }

    public boolean isFuncionariosAtualizados() {
        return funcionariosAtualizados;
    }

    public void setFuncionariosAtualizados(boolean funcionariosAtualizados) {
        this.funcionariosAtualizados = funcionariosAtualizados;
    }
}
