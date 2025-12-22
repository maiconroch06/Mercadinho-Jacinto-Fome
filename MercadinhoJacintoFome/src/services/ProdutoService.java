package services;

import classes.Produto;
import java.util.HashMap;

public class ProdutoService {

    private final HashMap<String, Produto> produtos = new HashMap<>();

    private boolean produtosAtualizados = false;

    // ---------- 1. CADASTRO ----------
    public boolean cadastrar(String codigo, Produto produto) {
        if (produtos.containsKey(codigo)) {
            return false; // Produto já existe
        }
        produtos.put(codigo, produto);
        produtosAtualizados = true;
        return true;
    }

    // ---------- 2. CONSULTA ----------
    public Produto consultar(String codigo) {
        return produtos.get(codigo); // retorna null se não existir
    }

    public HashMap<String, Produto> listarTodos() {
        return produtos;
    }

    // ---------- 3. REMOÇÃO ----------
    public boolean remover(String codigo) {
        if (!produtos.containsKey(codigo)) {
            return false;
        }
        produtos.remove(codigo);
        produtosAtualizados = true;
        return true;
    }

    // ---------- 4. ATUALIZAÇÃO ----------
    public boolean atualizar(String codigo, Produto novoProduto) {
        if (!produtos.containsKey(codigo)) {
            return false;
        }
        produtos.put(codigo, novoProduto);
        produtosAtualizados = true;
        return true;
    }

    public boolean atualizarQuantidade(String codigo, int novaQtd) {
        Produto p = produtos.get(codigo);
        if (p == null) return false;

        p.setQuantidade(novaQtd);
        produtosAtualizados = true;
        return true;
    }

    // ---------- 5. ESTOQUE (para vendas) ----------
    public void darBaixaNoEstoque(String codigo, int quantidadeVendida) {
        Produto p = produtos.get(codigo);
        if (p == null) return;

        int novaQtd = p.getQuantidade() - quantidadeVendida;

        if (novaQtd < 0) novaQtd = 0;

        p.setQuantidade(novaQtd);
        produtosAtualizados = true;
    }

    // ---------- 6. PRODUTOS PADRÃO ----------
    public void carregarProdutosPadrao() {
        cadastrar("001", new Produto("001", "Arroz", 10, 5.99));
        cadastrar("002", new Produto("002", "Feijão", 20, 7.50));
        cadastrar("003", new Produto("003", "Macarrão", 15, 4.25));
        cadastrar("004", new Produto("004", "Açúcar", 18, 3.89));
        cadastrar("005", new Produto("005", "Café", 12, 14.90));
        cadastrar("006", new Produto("006", "Óleo de Soja", 9, 7.99));
        cadastrar("007", new Produto("007", "Leite 1L", 25, 5.49));
        cadastrar("008", new Produto("008", "Manteiga", 8, 8.90));
        cadastrar("009", new Produto("009", "Detergente", 30, 2.39));
        cadastrar("010", new Produto("010", "Refrigerante 2L", 14, 9.50));
        cadastrar("011", new Produto("011", "Achocolatado", 16, 7.99));
        cadastrar("012", new Produto("012", "Biscoito", 35, 3.50));
        cadastrar("013", new Produto("013", "Margarina", 20, 5.25));
        cadastrar("014", new Produto("014", "Detergente", 28, 2.10));
        cadastrar("015", new Produto("015", "Sabão em Pó", 17, 12.90));
        cadastrar("016", new Produto("016", "Papel Higiênico", 50, 13.50));
        cadastrar("017", new Produto("017", "Creme Dental", 27, 4.99));
        cadastrar("018", new Produto("018", "Shampoo", 19, 10.99));
        cadastrar("019", new Produto("019", "Shampoo", 5, 10.99));
        cadastrar("020", new Produto("020", "Shampoo", 7, 10.99));
    }

    // ---------- 7. GETTERS / SETTERS ----------
    public boolean isProdutosAtualizados() {
        return produtosAtualizados;
    }

    public void setProdutosAtualizados(boolean produtosAtualizados) {
        this.produtosAtualizados = produtosAtualizados;
    }
}
