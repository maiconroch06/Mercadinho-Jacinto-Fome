package conexao;

import classes.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexaoCliente extends ConexaoBD {
    
    private boolean clientesAtualizados = false;
    
    public void cadastrarCliente(Cliente c) {
        conectar();
        try {
            // 1) inserir pessoa
            String sqlPessoa = "INSERT INTO pessoa (nome, cpf) VALUES (?, ?)";
            estado = con.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS);
            estado.setString(1, c.getNome());
            estado.setString(2, c.getCpf());
            estado.executeUpdate();

            ResultSet rs = estado.getGeneratedKeys();
            rs.next();
            int idPessoa = rs.getInt(1);

            // 2) inserir cliente
            String sqlCliente = "INSERT INTO cliente (id_pessoa, telefone, endereco) VALUES (?, ?, ?)";
            estado = con.prepareStatement(sqlCliente);
            estado.setInt(1, idPessoa);
            estado.setString(2, c.getTelefone());
            estado.setString(3, c.getEndereco());
            estado.executeUpdate();

            clientesAtualizados = true;
            //JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
            System.out.println("Cliente cadastrado com sucesso");
            
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente");
            System.out.println("Erro ao cadastrar cliente");
        }
    }

    public void atualizarCliente(Cliente c) {
        Integer idPessoa = buscarIdPessoaPorCpf(c.getCpf());
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
            estado.setString(1, c.getNome());
            estado.setString(2, c.getCpf());
            estado.setInt(3, idPessoa);
            estado.executeUpdate();

            // cliente
            String sqlCliente = "UPDATE cliente SET telefone = ?, endereco = ? WHERE id_pessoa = ?";
            estado = con.prepareStatement(sqlCliente);
            estado.setString(1, c.getTelefone());
            estado.setString(2, c.getEndereco());
            estado.setInt(3, idPessoa);
            estado.executeUpdate();

            setClientesAtualizados(true);
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente");
            System.out.println("Erro ao atualizar cliente");
        }
    }

    
    public void removerCliente(String cpf){
        Integer id_Pessoa = buscarIdPessoaPorCpf(cpf);
        if (id_Pessoa == null) {
            //JOptionPane.showMessageDialog(null, "CPF não encontrado");
            System.out.println("CPF não encontrado");
            return;
        }

        conectar();
        try {
            // 1) remove cliente
            String sqlCliente = "DELETE FROM cliente WHERE id_pessoa = ?";
            estado = con.prepareStatement(sqlCliente);
            estado.setInt(1, id_Pessoa);
            estado.executeUpdate();
            
            // 2) remove pessoa
            String sqlPessoa = "DELETE FROM pessoa WHERE id_pessoa = ?";
            estado = con.prepareStatement(sqlPessoa);
            estado.setInt(1, id_Pessoa);
            estado.executeUpdate();
            
            setClientesAtualizados(true);

        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Erro ao remover cliente");
            System.out.println("Erro ao remover cliente");
        }
    }
    
    private Integer buscarIdPessoaPorCpf(String cpf) {
        String sql = "SELECT c.id_pessoa "
                + "FROM pessoa p join cliente c "
                + "on p.id_pessoa = c.id_pessoa "
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

    public Cliente consultarCliente(String cpf) {
        Integer id_Pessoa = buscarIdPessoaPorCpf(cpf);
        if (id_Pessoa == null) {
            //JOptionPane.showMessageDialog(null, "CPF não encontrado");
            System.out.println("CPF não encontrado");
            return null;
        }
        
        String sql = "SELECT p.nome, p.cpf, c.telefone, c.endereco " +
                    "FROM pessoa p " +
                    "JOIN cliente c ON c.id_pessoa = p.id_pessoa " +
                    "WHERE c.id_pessoa = ?";

        conectar();
        try {
            estado = con.prepareStatement(sql);
            estado.setInt(1, id_Pessoa);
            ResultSet rs = estado.executeQuery();

            if (rs.next()) {
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                return c;
            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao consultar cliente");
            System.out.println("Erro ao consultar cliente");
        }

        return null;
    }

    public ArrayList<Cliente> consultarClientes() {
        String sql = "SELECT p.nome, p.cpf, c.telefone, c.endereco " +
                    "FROM pessoa p JOIN cliente c ON c.id_pessoa = p.id_pessoa";

        ArrayList<Cliente> lista = new ArrayList<>();
        conectar();

        try {
            estado = con.prepareStatement(sql);
            ResultSet rs = estado.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                lista.add(c);
            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao listar clientes");
            System.out.println("Erro ao listar clientes - Pode está vazia");
        }

        return lista;
    }

    public boolean isClientesAtualizados() {
        return clientesAtualizados;
    }

    public void setClientesAtualizados(boolean clientesAtualizados) {
        this.clientesAtualizados = clientesAtualizados;
    }
    
}
