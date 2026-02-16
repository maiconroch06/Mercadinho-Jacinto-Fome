package conexao;

import classes.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexaoProduto extends ConexaoBD {
    
    private boolean produtosAtualizados = false;
    
    public void cadastrarProduto(Produto p) {
        conectar();
        try {
            String sql = "INSERT INTO produto (codigo, descricao, quantidade, valorUnitario) VALUES (?,?,?,?)";
            estado = con.prepareStatement(sql);
            estado.setString(1, p.getCodigoProduto());
            estado.setString(2, p.getDescricao());
            estado.setInt(3, p.getQuantidade());
            estado.setDouble(4, p.getValorUnitario());
            
            estado.executeUpdate();
            
            setProdutosAtualizados(true);
            //JOptionPane.showMessageDialog(null, "Inserido com sucesso");
            System.out.println("Inserido com sucesso");
            
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto");
            System.out.println("Erro ao cadastrar produto");
        }
    }
    
    public void atualizarProduto(Produto p){
        Integer id_produto = buscarIdProdutoPorCod(p.getCodigoProduto());
        if(id_produto == null){
            //JOptionPane.showMessageDialog(null, "Codigo não encontrado");
            System.out.println("Codigo não encontrado");
            return;
        }
        
        conectar();
        try {
            String sql = "UPDATE produto SET codigo = ?, descricao = ?, quantidade = ?, valorUnitario = ? WHERE id_produto = ?";
            estado = con.prepareStatement(sql);
            estado.setString(1, p.getCodigoProduto());
            estado.setString(2, p.getDescricao());
            estado.setInt(3, p.getQuantidade());
            estado.setDouble(4, p.getValorUnitario());
            estado.setInt(5, id_produto);
            
            estado.executeUpdate();       
            
            setProdutosAtualizados(true);
            
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao atualizar");
            System.out.println("Erro ao atualizar");
        }
    }
    
    public void atualizarQuantidade(String codigo, int quantidade){
        Integer id_produto = buscarIdProdutoPorCod(codigo);
        if(id_produto == null){
            //JOptionPane.showMessageDialog(null, "Codigo não encontrado");
            System.out.println("Codigo não encontrado");
            return;
        }
        
        conectar();
        try {
            String sql= "UPDATE produto SET quantidade = ? WHERE id_produto = ?";
            estado = con.prepareStatement(sql);
            estado.setInt(1, quantidade);
            estado.setInt(2, id_produto);
            
            estado.executeUpdate();       
            
            setProdutosAtualizados(true);
            
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao atualizar");
            System.out.println("Erro ao atualizar");
        }
    }
    
    public void removerProduto(String codigo){
        Integer id_produto = buscarIdProdutoPorCod(codigo);
        if(id_produto == null){
            //JOptionPane.showMessageDialog(null, "Codigo não encontrado");
            System.out.println("Codigo não encontrado");
            return;
        }
        
        conectar();
        try {
            String sql= "DELETE FROM produto WHERE id_produto = ?";
            estado = con.prepareStatement(sql);
            estado.setInt(1, id_produto);

            estado.execute();

            setProdutosAtualizados(true);
            
            System.out.println("produto removido");
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao remover");
            System.out.println("Erro ao remover");
        }
        
    }
    
    private Integer buscarIdProdutoPorCod(String codigo) {
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

    
    public Produto consultarProduto(String codigo) {
        String sql = "SELECT * FROM produto WHERE codigo = ?";
        conectar();

        try {
            estado = con.prepareStatement(sql);
            estado.setString(1, codigo);
            ResultSet resultado = estado.executeQuery();

            if (resultado.next()) {
                Produto p = new Produto();
                p.setCodigoProduto(resultado.getString("codigo"));
                p.setDescricao(resultado.getString("descricao"));
                p.setQuantidade(resultado.getInt("quantidade"));
                p.setValorUnitario(resultado.getDouble("valorUnitario"));
                return p;
            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao consultar pelo CODIGO");
            System.out.println("Erro ao consultar pelo CODIGO");
        }

        return null;
    }

    
    public ArrayList<Produto> consultarProdutos(){
        String sql = "SELECT * FROM produto";
        ResultSet resultado;
        ArrayList<Produto> lista = new ArrayList<>();
        conectar();
        
        try {
            estado = con.prepareStatement(sql);
            resultado = estado.executeQuery();
            
            while(resultado.next()){
                Produto p = new Produto();
                p.setCodigoProduto(resultado.getString("codigo"));
                p.setDescricao(resultado.getString("descricao"));
                p.setQuantidade(resultado.getInt("quantidade"));
                p.setValorUnitario(resultado.getDouble("valorUnitario"));
                
                lista.add(p);
            }
            
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao consultar");
            System.out.println("Erro ao consultar");
        }
        
        return lista;
    }

    public boolean isProdutosAtualizados() {
        return produtosAtualizados;
    }

    public void setProdutosAtualizados(boolean produtosAtualizados) {
        this.produtosAtualizados = produtosAtualizados;
    }
}
