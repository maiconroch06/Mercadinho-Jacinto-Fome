package utilidades.tabela;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.*;

public class Deletar {
    
    public static void deletarProduto(DefaultTableModel modeloTableProduto, int linha, String chave, ProdutoService produtos){
        if (JOptionPane.showConfirmDialog(null,
                "Deseja excluir o produto " + chave + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)

            produtos.remover(chave);
            Carregar.tabelaProdutos(modeloTableProduto, produtos.listarTodos());
    }
    
    public static void deletarCliente(DefaultTableModel modeloTableCliente, int linha, String chave, ClienteService clientes){
        if (JOptionPane.showConfirmDialog(null,
                "Deseja excluir o cliente CPF: " + chave + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            clientes.remover(chave);
            Carregar.tabelaClientes(modeloTableCliente, clientes.listarTodos());
        }
    }
    
    public static void deletarFuncionario(DefaultTableModel modeloTableFuncionario, int linha, String chave, FuncionarioService funcionarios){
        if (JOptionPane.showConfirmDialog(null,
                "Deseja excluir o funcionário CPF: " + chave + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            funcionarios.remover(chave);
            Carregar.tabelaFuncionarios(modeloTableFuncionario, funcionarios.listarTodos());
        }
    }
    
    public static void deletarVenda(DefaultTableModel modeloTabelaVenda, int linha, String chave, VendaService vendas){
        if (JOptionPane.showConfirmDialog(null,
                "Deseja excluir a venda ID: " + chave + "?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            vendas.remover(chave);
            Carregar.tabelaVendas(modeloTabelaVenda, vendas.listarTodas());
        }
    }
}
