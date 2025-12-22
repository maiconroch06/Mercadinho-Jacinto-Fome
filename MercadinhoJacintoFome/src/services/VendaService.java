package services;

import classes.ItemVenda;
import classes.RegistroVenda;
import classes.Produto;
import java.util.HashMap;
import java.util.List;

public class VendaService {

    private final HashMap<String, RegistroVenda> vendas = new HashMap<>();

    private boolean vendasAtualizadas = false;

    private int seqVenda = 1; // auto-incremento de ID

    private ProdutoService produtoService;
    
    public VendaService() {}

    public VendaService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // ----------- 1. CADASTRAR VENDA -----------
    public boolean cadastrar(String id, RegistroVenda venda) {
        if (vendas.containsKey(id)) {
            return false; // venda já existe
        }
        vendas.put(id, venda);
        vendasAtualizadas = true;
        return true;
    }

    // ----------- 2. CONSULTAR VENDA -----------
    public RegistroVenda consultar(String id) {
        return vendas.get(id);
    }

    public HashMap<String, RegistroVenda> listarTodas() {
        return vendas;
    }

    // ----------- 3. REMOVER VENDA -----------
    public boolean remover(String id) {
        if (!vendas.containsKey(id)) {
            return false;
        }
        vendas.remove(id);
        vendasAtualizadas = true;
        return true;
    }

    // ----------- 4. GERAR ID DE VENDA -----------
    public String gerarIdVenda() {
        return String.valueOf(seqVenda++);
    }

    // ----------- 5. BAIXA DE ESTOQUE -----------
    public void atualizarEstoque(List<ItemVenda> itens) {
        for (ItemVenda item : itens) {

            String codigo = item.getCodigoProduto();
            Produto produto = produtoService.consultar(codigo);

            if (produto == null) {
                continue; // produto inexistente
            }

            int novaQtd = produto.getQuantidade() - item.getQuantidade();

            if (novaQtd < 0)
                novaQtd = 0;

            produto.setQuantidade(novaQtd);

            // atualiza no repositório
            produtoService.atualizarQuantidade(codigo, novaQtd);
        }

        vendasAtualizadas = true;
    }

    // ----------- 6. CALCULAR TOTAL DA VENDA -----------
    public double calcularTotal(List<ItemVenda> itens) {
        double total = 0.0;

        for (ItemVenda item : itens) {
            Produto produto = produtoService.consultar(item.getCodigoProduto());

            if (produto != null) {
                total += produto.getValorUnitario() * item.getQuantidade();
            }
        }

        return total;
    }

    // ----------- + GETTERS E SETTERS -----------
    public boolean isVendasAtualizadas() {
        return vendasAtualizadas;
    }

    public void setVendasAtualizadas(boolean vendasAtualizadas) {
        this.vendasAtualizadas = vendasAtualizadas;
    }

    public ProdutoService getProdutoService() {
        return produtoService;
    }
    
}

