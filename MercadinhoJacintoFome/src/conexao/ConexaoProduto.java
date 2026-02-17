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
            System.out.println("Inserido com sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro ao cadastrar produto");
            ex.printStackTrace();
        } finally {
            fecharConexao();
        }
    }

    public void atualizarProduto(Produto p) {
        Integer id_produto = buscarIdProdutoPorCod(p.getCodigoProduto());

        if (id_produto == null) {
            System.out.println("Código não encontrado");
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
            System.out.println("Erro ao atualizar produto");
            ex.printStackTrace();
        } finally {
            fecharConexao();
        }
    }

    public void atualizarQuantidade(String codigo, int quantidade) {
        Integer id_produto = buscarIdProdutoPorCod(codigo);

        if (id_produto == null) {
            System.out.println("Código não encontrado");
            return;
        }

        conectar();
        try {
            String sql = "UPDATE produto SET quantidade = ? WHERE id_produto = ?";
            estado = con.prepareStatement(sql);

            estado.setInt(1, quantidade);
            estado.setInt(2, id_produto);

            estado.executeUpdate();

            setProdutosAtualizados(true);

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar quantidade");
            ex.printStackTrace();
        } finally {
            fecharConexao();
        }
    }

    // 🔥 MÉTODO NOVO — verifica se produto tem vendas
    private boolean produtoTemVenda(int idProduto) {
        conectar();

        try {
            String sql = "SELECT 1 FROM item_venda WHERE id_produto = ?";
            estado = con.prepareStatement(sql);
            estado.setInt(1, idProduto);

            ResultSet rs = estado.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Erro ao verificar vendas do produto");
            return true;
        } finally {
            fecharConexao();
        }
    }

    public void removerProduto(String codigo) {
        Integer id_produto = buscarIdProdutoPorCod(codigo);

        if (id_produto == null) {
            System.out.println("Código não encontrado");
            return;
        }

        // 🔥 proteção lógica
        if (produtoTemVenda(id_produto)) {
            System.out.println("Produto já foi vendido — não pode excluir.");
            return;
        }

        conectar();
        try {
            String sql = "DELETE FROM produto WHERE id_produto = ?";
            estado = con.prepareStatement(sql);
            estado.setInt(1, id_produto);

            estado.executeUpdate();

            setProdutosAtualizados(true);
            System.out.println("Produto removido com sucesso");

        } catch (SQLException ex) {
            System.out.println("Erro ao remover produto");
            ex.printStackTrace();
        } finally {
            fecharConexao();
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
            System.out.println("Erro ao buscar produto");
        } finally {
            fecharConexao();
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
            System.out.println("Erro ao consultar produto");
        } finally {
            fecharConexao();
        }

        return null;
    }

    public ArrayList<Produto> consultarProdutos() {
        String sql = "SELECT * FROM produto";

        ArrayList<Produto> lista = new ArrayList<>();

        conectar();
        try {
            estado = con.prepareStatement(sql);
            ResultSet resultado = estado.executeQuery();

            while (resultado.next()) {
                Produto p = new Produto();

                p.setCodigoProduto(resultado.getString("codigo"));
                p.setDescricao(resultado.getString("descricao"));
                p.setQuantidade(resultado.getInt("quantidade"));
                p.setValorUnitario(resultado.getDouble("valorUnitario"));

                lista.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar produtos");
        } finally {
            fecharConexao();
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
