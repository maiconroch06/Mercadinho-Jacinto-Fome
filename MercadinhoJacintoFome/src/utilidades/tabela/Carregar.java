package utilidades.tabela;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import classes.*;
import java.util.ArrayList;

public class Carregar {
    
    // CARREGAR ORDENAÇÃO NA TABELA
    public static void ordenacao(JTable tabela){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabela.getModel());
        tabela.setRowSorter(sorter);
        sorter.toggleSortOrder(0);
    }
    
    // CARREGAR ORDENAÇÃO NA TABELA - SELECIONANDO COLUNA
    public static void ordenacao(JTable tabela, int coluna) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabela.getModel());
        tabela.setRowSorter(sorter);
        sorter.toggleSortOrder(coluna);
    }
    
    // CARREGAR PRODUTOS
    public static void tabelaProdutos(DefaultTableModel modelo, ArrayList<Produto> produtos) {
        modelo.setRowCount(0);

        for (Produto p : produtos) {
            modelo.addRow(new Object[]{
                p.getCodigoProduto(),
                p.getDescricao(),
                p.getQuantidade(),
                p.getValorUnitario()
            });
        }
    }
    
    // CARREGAR PRODUTOS DA VENDA
    public static void tabelaProdutosVenda(DefaultTableModel modelo, ArrayList<ItemVenda> itensComprados) {
        modelo.setRowCount(0);
        
        for (ItemVenda v : itensComprados) {
            modelo.addRow(new Object[]{
                v.getCodigoProduto(),
                v.getDescricao(),
                v.getQuantidade(),
                v.getValorUnitario(),
                v.getValorTotal()
            });
        }
    }

    // CARREGAR CLIENTES
    public static void tabelaClientes(DefaultTableModel modeloTableProduto, ArrayList<Cliente> clientes) {
        modeloTableProduto.setRowCount(0);
        
        for (Cliente c : clientes) {
            modeloTableProduto.addRow(new Object[]{
                c.getNome(),
                c.getCpf(),
                c.getEndereco(),
                c.getTelefone()
            });
        }
    }

    // CARREGAR FUNCIONÁRIOS
    public static void tabelaFuncionarios(DefaultTableModel modeloTableFuncionario, ArrayList<Funcionario> funcionarios) {
        modeloTableFuncionario.setRowCount(0);

        for (Funcionario f : funcionarios) {
            modeloTableFuncionario.addRow(new Object[]{
                f.getNome(),
                f.getCpf()
            });
        }
    }

    // CARREGAR VENDAS
    public static void tabelaVendas(DefaultTableModel modelo, ArrayList<RegistroVenda> vendas) {
        modelo.setRowCount(0);

        for(RegistroVenda v : vendas) {
            modelo.addRow(new Object[]{
                v.getIdVenda(),
                v.getCpfFuncionario(),
                v.getCpfCliente(),
                v.getItensTotal(),
                String.format("R$ %.2f", v.getTotalValor())
            });
        }
    }
}
