package services;

public class Gerenciamento {

    private final ProdutoService produtoService = new ProdutoService();
    private final ClienteService clienteService = new ClienteService();
    private final FuncionarioService funcionarioService = new FuncionarioService();
    private final VendaService vendaService = new VendaService(produtoService);

    public ProdutoService produtos() {
        return produtoService;
    }

    public ClienteService clientes() {
        return clienteService;
    }

    public FuncionarioService funcionarios() {
        return funcionarioService;
    }

    public VendaService vendas() {
        return vendaService;
    }
}
