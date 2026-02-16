package conexao;

public class Gerenciamento {

    private final ConexaoProduto conexProduto = new ConexaoProduto();
    private final ConexaoFuncionario conexFuncionario = new ConexaoFuncionario();
    private final ConexaoCliente conexCliente = new ConexaoCliente();
    private final ConexaoVenda conexVenda = new ConexaoVenda(conexProduto);

    public ConexaoProduto produtos() {
        return conexProduto;
    }

    public ConexaoFuncionario funcionarios() {
        return conexFuncionario;
    }

    public ConexaoCliente clientes() {
        return conexCliente;
    }

    public ConexaoVenda vendas() {
        return conexVenda;
    }
}
