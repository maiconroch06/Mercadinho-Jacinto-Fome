package services;

import classes.ItemVenda;
import classes.RegistroVenda;
import classes.Produto;
import java.util.HashMap;
import java.util.List;

/**
 * Classe responsável por gerenciar as vendas do sistema.
 * 
 * Atua como camada de serviço (Service), centralizando:
 * - Cadastro de vendas
 * - Consulta e remoção
 * - Geração de ID
 * - Atualização de estoque
 * - Cálculo do total da venda
 */
public class VendaService {

    /**
     * Estrutura que armazena as vendas cadastradas.
     * A chave (String) representa o ID da venda.
     */
    private final HashMap<String, RegistroVenda> vendas = new HashMap<>();

    /**
     * Flag utilizada para indicar se houve alterações nas vendas.
     * Pode ser usada para:
     * - Persistência
     * - Atualização de tela
     * - Controle de sincronização
     */
    private boolean vendasAtualizadas = false;

    /**
     * Sequenciador simples para geração de IDs de venda.
     * Simula um auto-incremento.
     */
    private int seqVenda = 1;

    /**
     * Serviço responsável pelos produtos.
     * Necessário para:
     * - Consultar produtos
     * - Atualizar estoque
     * - Calcular valores
     */
    private ProdutoService produtoService;

    /**
     * Construtor vazio.
     * Útil quando o ProdutoService será injetado posteriormente.
     */
    public VendaService() {}

    /**
     * Construtor com injeção do ProdutoService.
     * Garante que a venda consiga acessar os dados dos produtos.
     */
    public VendaService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // ================== 1. CADASTRAR VENDA ==================

    /**
     * Cadastra uma nova venda no sistema.
     * 
     * @param id    identificador único da venda
     * @param venda objeto RegistroVenda com os dados da venda
     * @return true se a venda foi cadastrada com sucesso
     *         false se já existir uma venda com o mesmo ID
     */
    public boolean cadastrar(String id, RegistroVenda venda) {

        // Evita sobrescrever uma venda já existente
        if (vendas.containsKey(id)) {
            return false;
        }

        vendas.put(id, venda);

        // Marca que houve alteração no conjunto de vendas
        vendasAtualizadas = true;
        return true;
    }

    // ================== 2. CONSULTAR VENDA ==================

    /**
     * Consulta uma venda pelo ID.
     * 
     * @param id identificador da venda
     * @return RegistroVenda correspondente ou null se não existir
     */
    public RegistroVenda consultar(String id) {
        return vendas.get(id);
    }

    /**
     * Retorna todas as vendas cadastradas.
     * 
     * Atenção: retorna a referência direta do HashMap.
     * Ideal apenas para leitura.
     */
    public HashMap<String, RegistroVenda> listarTodas() {
        return vendas;
    }

    // ================== 3. REMOVER VENDA ==================

    /**
     * Remove uma venda do sistema.
     * 
     * @param id identificador da venda
     * @return true se a venda foi removida
     *         false se a venda não existir
     */
    public boolean remover(String id) {

        if (!vendas.containsKey(id)) {
            return false;
        }

        vendas.remove(id);
        vendasAtualizadas = true;
        return true;
    }

    // ================== 4. GERAR ID DE VENDA ==================

    /**
     * Gera um novo ID sequencial para a venda.
     * 
     * @return ID da venda em formato String
     */
    public String gerarIdVenda() {
        return String.valueOf(seqVenda++);
    }

    // ================== 5. ATUALIZAR ESTOQUE ==================

    /**
     * Atualiza o estoque dos produtos com base nos itens vendidos.
     * 
     * Para cada item da venda:
     * - Localiza o produto
     * - Subtrai a quantidade vendida
     * - Garante que o estoque não fique negativo
     * - Atualiza no ProdutoService
     * 
     * @param itens lista de itens vendidos
     */
    public void atualizarEstoque(List<ItemVenda> itens) {

        for (ItemVenda item : itens) {

            // Obtém o código do produto vendido
            String codigo = item.getCodigoProduto();

            // Consulta o produto no serviço de produtos
            Produto produto = produtoService.consultar(codigo);

            // Se o produto não existir, ignora o item
            if (produto == null) {
                continue;
            }

            // Calcula nova quantidade em estoque
            int novaQtd = produto.getQuantidade() - item.getQuantidade();

            // Impede estoque negativo
            if (novaQtd < 0) {
                novaQtd = 0;
            }

            // Atualiza o objeto produto
            produto.setQuantidade(novaQtd);

            // Atualiza o repositório de produtos
            produtoService.atualizarQuantidade(codigo, novaQtd);
        }

        // Marca que houve alteração relacionada às vendas
        vendasAtualizadas = true;
    }

    // ================== 6. CALCULAR TOTAL DA VENDA ==================

    /**
     * Calcula o valor total da venda com base nos itens vendidos.
     * 
     * O valor é calculado usando:
     * valor unitário do produto × quantidade vendida
     * 
     * @param itens lista de itens da venda
     * @return valor total da venda
     */
    public double calcularTotal(List<ItemVenda> itens) {

        double total = 0.0;

        for (ItemVenda item : itens) {

            // Consulta o produto para obter o valor unitário
            Produto produto = produtoService.consultar(item.getCodigoProduto());

            // Soma apenas se o produto existir
            if (produto != null) {
                total += produto.getValorUnitario() * item.getQuantidade();
            }
        }

        return total;
    }

    // ================== GETTERS E SETTERS ==================

    /**
     * Indica se houve alterações nas vendas.
     */
    public boolean isVendasAtualizadas() {
        return vendasAtualizadas;
    }

    /**
     * Permite controlar manualmente o estado de atualização.
     */
    public void setVendasAtualizadas(boolean vendasAtualizadas) {
        this.vendasAtualizadas = vendasAtualizadas;
    }

    /**
     * Retorna o serviço de produtos associado à venda.
     */
    public ProdutoService getProdutoService() {
        return produtoService;
    }

}
