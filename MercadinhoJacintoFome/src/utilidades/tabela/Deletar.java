package utilidades.tabela;

import conexao.ConexaoCliente;
import conexao.ConexaoFuncionario;
import conexao.ConexaoProduto;
import conexao.ConexaoVenda;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Deletar {
    
    public static void deletarProduto(DefaultTableModel modeloTableProduto, ConexaoProduto conexProduto, String chave) {
        if (JOptionPane.showConfirmDialog(null,
                "Deseja excluir o produto " + chave + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            conexProduto.removerProduto(chave);
            Carregar.tabelaProdutos(modeloTableProduto, conexProduto.consultarProdutos());
        }
    }
    
    public static void deletarCliente(DefaultTableModel modeloTableCliente, ConexaoCliente conexCliente, String chave) {
        if (JOptionPane.showConfirmDialog(null,
                "Deseja excluir o cliente CPF: " + chave + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            conexCliente.removerCliente(chave);
            Carregar.tabelaClientes(modeloTableCliente, conexCliente.consultarClientes());
        }
    }
    
    public static void deletarFuncionario(DefaultTableModel modeloTableFuncionario, ConexaoFuncionario conexFuncionario, String chave) {
        if (JOptionPane.showConfirmDialog(null,
                "Deseja excluir o funcionário CPF: " + chave + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            conexFuncionario.removerFuncionario(chave);
            Carregar.tabelaFuncionarios(modeloTableFuncionario, conexFuncionario.consultarFuncionarios());
        }
    }
    
    public static void deletarVenda(DefaultTableModel modeloTabelaVenda, ConexaoVenda conexVendas, String chave) {
        if (JOptionPane.showConfirmDialog(null,
                "Deseja excluir a venda ID: " + chave + "?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            conexVendas.removerVenda(chave);
            Carregar.tabelaVendas(modeloTabelaVenda, conexVendas.consultarVendas());

        }
    }
    
}
