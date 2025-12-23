package services;

import classes.Produto;
import java.util.HashMap;

/**
 * Classe responsável pelo gerenciamento dos produtos do sistema.
 * 
 * Atua como camada de serviço (Service), sendo responsável por:
 * - Cadastro de produtos
 * - Consulta individual e geral
 * - Atualização de dados
 * - Remoção
 * - Controle de estoque
 * - Controle de alterações para atualização da interface
 * 
 * Esta classe funciona como um repositório em memória,
 * utilizando um HashMap para armazenar os produtos.
 */
public class ProdutoService {

    /**
     * Estrutura que armazena os produtos cadastrados.
     * 
     * A chave (String) representa o código único do produto.
     * O valor (Produto) contém todas as informações do produto.
     */
    private final HashMap<String, Produto> produtos = new HashMap<>();

    /**
     * Flag que indica se houve alterações na lista de produtos.
     * 
     * Pode ser utilizada pela interface gráfica para:
     * - Recarregar JTable
     * - Evitar atualizações desnecessárias
     * - Controlar estado do sistema
     */
    private boolean produtosAtualizados = false;

    /**
     * Construtor padrão.
     */
    public ProdutoService() {}

    // ================== 1. CADASTRO ==================

    /**
     * Cadastra um novo produto no sistema.
     * 
     * @param codigo  código único que identifica o produto
     * @param produto objeto Produto contendo os dados do produto
     * @return true se o produto foi cadastrado com sucesso
     *         false se já existir um produto com o mesmo código
     * 
     * Observação:
     * O código do produto funciona como chave do HashMap,
     * garantindo unicidade.
     */
    public boolean cadastrar(String codigo, Produto produto) {

        // Verifica se já existe um produto com o código informado
        if (produtos.containsKey(codigo)) {
            return false;
        }

        // Armazena o produto no HashMap
        produtos.put(codigo, produto);

        // Marca que houve alteração nos dados
        produtosAtualizados = true;

        return true;
    }

    // ================== 2. CONSULTA ==================

    /**
     * Consulta um produto pelo código.
     * 
     * @param codigo código do produto
     * @return Produto correspondente ou null se não existir
     * 
     * Observação:
     * O método HashMap.get() retorna null automaticamente
     * caso a chave não seja encontrada.
     */
    public Produto consultar(String codigo) {
        return produtos.get(codigo);
    }

    /**
     * Retorna todos os produtos cadastrados.
     * 
     * Método utilizado principalmente para:
     * - Carregar tabelas
     * - Gerar relatórios
     * - Listagens gerais
     * 
     * @return HashMap contendo todos os produtos
     */
    public HashMap<String, Produto> listarTodos() {
        return produtos;
    }

    // ================== 3. REMOÇÃO ==================

    /**
     * Remove um produto do sistema.
     * 
     * @param codigo código do produto a ser removido
     * @return true se o produto foi removido
     *         false se o produto não existir
     */
    public boolean remover(String codigo) {

        // Verifica se o produto existe antes de remover
        if (!produtos.containsKey(codigo)) {
            return false;
        }

        // Remove o produto do HashMap
        produtos.remove(codigo);

        // Indica que houve alteração nos dados
        produtosAtualizados = true;

        return true;
    }

    // ================== 4. ATUALIZAÇÃO ==================

    /**
     * Atualiza todas as informações de um produto existente.
     * 
     * @param codigo      código do produto
     * @param novoProduto objeto Produto com os dados atualizados
     * @return true se a atualização foi realizada
     *         false se o produto não existir
     */
    public boolean atualizar(String codigo, Produto novoProduto) {

        // Se o produto não existir, não há o que atualizar
        if (!produtos.containsKey(codigo)) {
            return false;
        }

        // Substitui o produto antigo pelo novo
        produtos.put(codigo, novoProduto);

        // Marca que houve atualização
        produtosAtualizados = true;

        return true;
    }

    /**
     * Atualiza apenas a quantidade de um produto.
     * 
     * Método utilizado principalmente após:
     * - Vendas
     * - Ajustes de estoque
     * 
     * @param codigo código do produto
     * @param novaQtd nova quantidade do produto
     * @return true se atualizado | false se o produto não existir
     */
    public boolean atualizarQuantidade(String codigo, int novaQtd) {

        // Busca o produto pelo código
        Produto p = produtos.get(codigo);

        // Se o produto não existir, encerra o método
        if (p == null) {
            return false;
        }

        // Atualiza apenas o campo quantidade
        p.setQuantidade(novaQtd);

        // Marca que houve alteração
        produtosAtualizados = true;

        return true;
    }

    // ================== 5. ESTOQUE ==================

    /**
     * Dá baixa no estoque de um produto após uma venda.
     * 
     * Garante que a quantidade do produto
     * não fique negativa.
     * 
     * @param codigo código do produto
     * @param quantidadeVendida quantidade vendida
     */
    public void darBaixaNoEstoque(String codigo, int quantidadeVendida) {

        // Busca o produto pelo código
        Produto p = produtos.get(codigo);

        // Se o produto não existir, encerra o método
        if (p == null) {
            return;
        }

        // Calcula a nova quantidade
        int novaQtd = p.getQuantidade() - quantidadeVendida;

        // Evita estoque negativo
        if (novaQtd < 0) {
            novaQtd = 0;
        }

        // Atualiza a quantidade do produto
        p.setQuantidade(novaQtd);

        // Marca que houve alteração
        produtosAtualizados = true;
    }

    // ================== 6. PRODUTOS PADRÃO ==================

    /**
     * Carrega produtos pré-cadastrados no sistema.
     * 
     * Método utilizado para:
     * - Testes
     * - Demonstrações
     * - Ambiente de desenvolvimento
     */
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

    // ================== 7. GETTERS E SETTERS ==================

    /**
     * Indica se a lista de produtos foi alterada.
     * 
     * @return true se houve alterações | false caso contrário
     */
    public boolean isProdutosAtualizados() {
        return produtosAtualizados;
    }

    /**
     * Permite controlar manualmente o estado de atualização.
     * 
     * Geralmente utilizado após a interface
     * recarregar as tabelas.
     */
    public void setProdutosAtualizados(boolean produtosAtualizados) {
        this.produtosAtualizados = produtosAtualizados;
    }
}
