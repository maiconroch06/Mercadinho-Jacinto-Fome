package services;

/**
 * Classe responsável por centralizar o acesso aos serviços do sistema.
 * 
 * Atua como um ponto único de gerenciamento (Facade),
 * facilitando o uso dos serviços pelas interfaces gráficas.
 * 
 * Esta classe:
 * - Instancia todos os Services
 * - Evita múltiplas criações desnecessárias no main
 * - Garante o compartilhamento das mesmas instâncias
 * - Organiza o acesso à camada de negócio
 */
public class Gerenciamento {

    /**
     * Serviço responsável pelo gerenciamento de produtos.
     * 
     * Instância única utilizada em todo o sistema,
     * garantindo consistência dos dados.
     */
    private final ProdutoService produtoService = new ProdutoService();

    /**
     * Serviço responsável pelo gerenciamento de clientes.
     */
    private final ClienteService clienteService = new ClienteService();

    /**
     * Serviço responsável pelo gerenciamento de funcionários.
     */
    private final FuncionarioService funcionarioService = new FuncionarioService();

    /**
     * Serviço responsável pelo gerenciamento das vendas.
     * 
     * Recebe o ProdutoService por dependência,
     * permitindo:
     * - Controle de estoque
     * - Baixa automática de produtos vendidos
     */
    private final VendaService vendaService = new VendaService(produtoService);

    /**
     * Retorna o serviço de produtos.
     * 
     * @return instância de ProdutoService
     */
    public ProdutoService produtos() {
        return produtoService;
    }

    /**
     * Retorna o serviço de clientes.
     * 
     * @return instância de ClienteService
     */
    public ClienteService clientes() {
        return clienteService;
    }

    /**
     * Retorna o serviço de funcionários.
     * 
     * @return instância de FuncionarioService
     */
    public FuncionarioService funcionarios() {
        return funcionarioService;
    }

    /**
     * Retorna o serviço de vendas.
     * 
     * @return instância de VendaService
     */
    public VendaService vendas() {
        return vendaService;
    }
}
