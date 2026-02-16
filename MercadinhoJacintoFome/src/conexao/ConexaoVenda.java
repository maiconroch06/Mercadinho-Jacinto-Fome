package conexao;

import classes.ItemVenda;
import classes.RegistroVenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexaoVenda extends ConexaoBD {

    ConexaoProduto conexProduto;
    private boolean vendasAtualizadas = false;

    public ConexaoVenda(ConexaoProduto conexProduto) {
        this.conexProduto = conexProduto;
    }
    
    public ConexaoVenda() {}
    
    public boolean cadastrarVendaCompleta(RegistroVenda venda) {

        conectar();

        try {

            con.setAutoCommit(false);

            Integer idFuncionario = buscarIdFuncionarioPorCpf(venda.getCpfFuncionario());
            Integer idCliente = buscarIdClientePorCpf(venda.getCpfCliente());

            if (idFuncionario == null || idCliente == null) {
                throw new SQLException("CPF inválido");
            }

            // ---- cadastra venda ----
            String sqlVenda = "INSERT INTO historicoVenda (id_funcionario, id_cliente, metodo) VALUES (?, ?, ?)";

            estado = con.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS);

            estado.setInt(1, idFuncionario);
            estado.setInt(2, idCliente);
            estado.setString(3, venda.getMetodo());

            estado.executeUpdate();

            ResultSet rs = estado.getGeneratedKeys();

            if (!rs.next()) throw new SQLException("Erro ao gerar id venda");

            int idVenda = rs.getInt(1);

            // ---- cadastra itens ----
            for (ItemVenda item : venda.getItensComprados()) {

                Integer idProduto = buscarIdProdutoPorCodigo(item.getCodigoProduto());

                if (idProduto == null)
                    throw new SQLException("Produto inválido");

                String sqlItem = "INSERT INTO item_venda (id_venda, id_produto, quantidade, valor_unitario) VALUES (?, ?, ?, ?)";

                estado = con.prepareStatement(sqlItem);

                estado.setInt(1, idVenda);
                estado.setInt(2, idProduto);
                estado.setInt(3, item.getQuantidade());
                estado.setDouble(4, item.getValorUnitario());

                estado.executeUpdate();
            }

            con.commit();

            setVendasAtualizadas(true);

            return true;

        } catch (SQLException e) {

            try { con.rollback(); } catch (Exception ignore) {}

            System.out.println("Erro venda completa:");
            e.printStackTrace();

            return false;

        } finally {

            try { con.setAutoCommit(true); } catch (Exception ignore) {}
        }
    }
    public int cadastrarVenda(RegistroVenda v) {
        conectar();
        try {
            Integer idFuncionario = buscarIdFuncionarioPorCpf(v.getCpfFuncionario());
            Integer idCliente = buscarIdClientePorCpf(v.getCpfCliente());

            if (idFuncionario == null || idCliente == null) {
                //JOptionPane.showMessageDialog(null, "CPF inválido");
                System.out.println("CPF inválido");
                return -1;
            }

            String sql = "INSERT INTO historicoVenda (id_funcionario, id_cliente, metodo) VALUES (?, ?, ?)";
            estado = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            estado.setInt(1, idFuncionario);
            estado.setInt(2, idCliente);
            estado.setString(3, v.getMetodo());
            
            estado.executeUpdate();

            ResultSet rs = estado.getGeneratedKeys();
            if (rs.next()) {
                setVendasAtualizadas(true);
                return rs.getInt(1); // retorna o idVenda
            }
            return -1;


        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao cadastrar venda");
            System.out.println("Erro ao cadastrar venda");
            return -1;
        }
    }

    public void cadastrarItemVenda(int idVenda, String codigoProduto, int qtd, double valor) {
        Integer idProduto = buscarIdProdutoPorCodigo(codigoProduto);
        if (idProduto == null) {
                //JOptionPane.showMessageDialog(null, "Codigo invalido inválido");
                System.out.println("Codigo invalido inválido");
                return;
            }
        
        conectar();
        try {
            String sql = "INSERT INTO item_venda (id_venda, id_produto, quantidade, valor_unitario) VALUES (?, ?, ?, ?)";
            estado = con.prepareStatement(sql);
            estado.setInt(1, idVenda);
            estado.setInt(2, idProduto);
            estado.setInt(3, qtd);
            estado.setDouble(4, valor);
            estado.executeUpdate();
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Erro ao inserir item da venda");
            System.out.println("Erro ao inserir item da venda");
        }
    }
    
    private Integer buscarIdProdutoPorCodigo(String codigo) {
        String sql = "SELECT id_produto FROM produto WHERE codigo = ?";

        conectar();
        try {
            estado = con.prepareStatement(sql);
            estado.setString(1, codigo);
            ResultSet rs = estado.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_produto");
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Erro ao buscar produto");
            System.out.println("Erro ao buscar produto");
        }

        return null;
    }
    
    public void atualizarVenda(RegistroVenda v){
        Integer idFuncionario = buscarIdFuncionarioPorCpf(v.getCpfFuncionario());
        Integer idCliente = buscarIdClientePorCpf(v.getCpfCliente());

        String sql = "UPDATE historicoVenda SET id_funcionario = ?, id_cliente = ?, metodo = ? WHERE id_venda = ?";
        
        conectar();
        try {
            estado = con.prepareStatement(sql);
            estado.setInt(1, idFuncionario);
            estado.setInt(2, idCliente);
            estado.setString(3, v.getMetodo());
            estado.setInt(4, Integer.parseInt(v.getIdVenda()));
            
            estado.executeUpdate();       
            
            setVendasAtualizadas(true);
            
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao atualizar");
            System.out.println("Erro ao atualizar");
        }
    }
    
    private Integer buscarIdFuncionarioPorCpf(String cpf) {
        String sql = "SELECT f.id_funcionario "
                + "FROM pessoa p join funcionario f "
                + "on p.id_pessoa = f.id_pessoa "
                + "WHERE cpf = ?";

        conectar();
        try {
            estado = con.prepareStatement(sql);
            estado.setString(1, cpf);
            ResultSet rs = estado.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_funcionario");
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Erro ao buscar pessoa");
            System.out.println("Erro ao buscar funcionario");
        }

        return null;
    }
    
    private Integer buscarIdClientePorCpf(String cpf) {
        String sql = "SELECT c.id_cliente "
                + "FROM cliente c join pessoa p "
                + "on c.id_pessoa = p.id_pessoa "
                + "WHERE cpf = ?";

        conectar();
        try {
            estado = con.prepareStatement(sql);
            estado.setString(1, cpf);
            ResultSet rs = estado.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_cliente");
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Erro ao buscar pessoa");
            System.out.println("Erro ao buscar pessoa");
        }

        return null;
    }
    
    public void removerVenda(String id_venda){
        String sql = "DELETE FROM historicoVenda WHERE id_venda = ?";
        
        conectar();
        try {
            estado = con.prepareStatement(sql);
            estado.setString(1, id_venda);

            estado.executeUpdate();

            setVendasAtualizadas(true);
            
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao remover");
            System.out.println("Erro ao remover");
        }
        
    }
    
    public RegistroVenda consultarVenda(String id_venda) {
        String sql = "SELECT "+
                "v.id_venda, " +
                "pf.cpf AS cpf_funcionario, " +
                "pc.cpf AS cpf_cliente, " +
                "v.metodo, " +
                "SUM(iv.quantidade) AS itens_totais, " +
                "SUM(iv.quantidade * iv.valor_unitario) AS total_valor " +
            "FROM historicoVenda v " +
            "JOIN funcionario f ON f.id_funcionario = v.id_funcionario " +
            "JOIN pessoa pf ON pf.id_pessoa = f.id_pessoa " +
            "JOIN cliente c ON c.id_cliente = v.id_cliente " +
            "JOIN pessoa pc ON pc.id_pessoa = c.id_pessoa " +
            "JOIN item_venda iv ON iv.id_venda = v.id_venda " +
            "WHERE v.id_venda = ? " +
            "GROUP BY v.id_venda, pf.cpf, pc.cpf, v.metodo";

        conectar();
        try {
            estado = con.prepareStatement(sql);
            estado.setInt(1, Integer.parseInt(id_venda));
            ResultSet rs = estado.executeQuery();

            if (rs.next()) {
                RegistroVenda v = new RegistroVenda();

                v.setIdVenda(rs.getString("id_venda"));
                v.setCpfFuncionario(rs.getString("cpf_funcionario"));
                v.setCpfCliente(rs.getString("cpf_cliente"));
                v.setMetodo(rs.getString("metodo"));
                v.setItensTotal(rs.getInt("itens_totais"));
                v.setTotalValor(rs.getDouble("total_valor"));

                v.setItensComprados(
                    buscarItensDaVenda(Integer.parseInt(id_venda))
                );

                return v;
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao consultar venda");
            System.out.println("Erro ao consultar venda");
        }
        return null;
    }

    public ArrayList<RegistroVenda> consultarVendas() {
        String sql = "SELECT "+
                "v.id_venda, " +
                "pf.cpf AS cpf_funcionario, " +
                "pc.cpf AS cpf_cliente, " +
                "v.metodo, " +
                "SUM(iv.quantidade) AS itens_totais, " +
                "SUM(iv.quantidade * iv.valor_unitario) AS total_valor " +
            "FROM historicoVenda v " +
            "JOIN funcionario f ON f.id_funcionario = v.id_funcionario " +
            "JOIN pessoa pf ON pf.id_pessoa = f.id_pessoa " +
            "JOIN cliente c ON c.id_cliente = v.id_cliente " +
            "JOIN pessoa pc ON pc.id_pessoa = c.id_pessoa " +
            "JOIN item_venda iv ON iv.id_venda = v.id_venda " +
            "GROUP BY v.id_venda, pf.cpf, pc.cpf, v.metodo";

        ArrayList<RegistroVenda> lista = new ArrayList<>();
        conectar();
        try {
            estado = con.prepareStatement(sql);
            ResultSet rs = estado.executeQuery();

            while (rs.next()) {
                RegistroVenda v = new RegistroVenda();
                v.setIdVenda(rs.getString("id_venda"));
                v.setCpfFuncionario(rs.getString("cpf_funcionario"));
                v.setCpfCliente(rs.getString("cpf_cliente"));
                v.setMetodo(rs.getString("metodo"));
                v.setItensTotal(rs.getInt("itens_totais"));
                v.setTotalValor(rs.getDouble("total_valor"));
                lista.add(v);
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao consultar vendas");
            System.out.println("Erro ao consultar vendas");
        }
        return lista;
    }
    
    public ArrayList<ItemVenda> buscarItensDaVenda(int idVenda) {

        ArrayList<ItemVenda> lista = new ArrayList<>();

        String sql =
            "SELECT p.codigo, p.descricao, iv.quantidade, iv.valor_unitario " +
            "FROM item_venda iv " +
            "JOIN produto p ON p.id_produto = iv.id_produto " +
            "WHERE iv.id_venda = ?";

        conectar();

        try {

            estado = con.prepareStatement(sql);
            estado.setInt(1, idVenda);

            ResultSet rs = estado.executeQuery();

            while (rs.next()) {

                ItemVenda item = new ItemVenda();

                item.setCodigoProduto(rs.getString("codigo"));
                item.setDescricao(rs.getString("descricao"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setValorUnitario(rs.getDouble("valor_unitario"));

                lista.add(item);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar itens da venda");

        }

        return lista;
    }

    
    public ConexaoProduto getConexProduto() {
        return conexProduto;
    }
    
    public boolean isVendasAtualizadas() {
        return vendasAtualizadas;
    }

    public void setVendasAtualizadas(boolean vendasAtualizadas) {
        this.vendasAtualizadas = vendasAtualizadas;
    }
    
}
