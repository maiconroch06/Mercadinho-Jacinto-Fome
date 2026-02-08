package services;

public class Gerenciamento {

    private final ProdutoService produtoService = new ProdutoService();
    private final PessoaService pessoaService = new PessoaService();
    private final VendaService vendaService = new VendaService(produtoService);

    public ProdutoService produtos() {
        return produtoService;
    }

    public PessoaService pessoas() {
        return pessoaService;
    }

    public VendaService vendas() {
        return vendaService;
    }
}
