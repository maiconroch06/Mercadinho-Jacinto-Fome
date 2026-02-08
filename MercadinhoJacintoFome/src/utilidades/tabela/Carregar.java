package utilidades.tabela;

import java.util.HashMap;
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
    public static void tabelaProdutos(DefaultTableModel modelo, HashMap<String, Produto> produtos) {
        modelo.setRowCount(0);

        for (Produto p : produtos.values()) {
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
                v.getValorTotalProduto()
            });
        }
    }

    // CARREGAR CLIENTES
    public static void tabelaClientes(DefaultTableModel modeloTableProduto, HashMap<String, Pessoa> pessoas) {
        modeloTableProduto.setRowCount(0);
        
        for (Pessoa p : pessoas.values()) {
            if(p instanceof Cliente) {
                Cliente c = (Cliente) p;
                modeloTableProduto.addRow(new Object[]{
                    c.getNome(),
                    c.getCpf(),
                    c.getTelefone(),
                    c.getEndereco()
                });
            }
        }
    }

    // CARREGAR FUNCIONÁRIOS
    public static void tabelaFuncionarios(DefaultTableModel modeloTableFuncionario, HashMap<String, Pessoa> pessoas) {
        modeloTableFuncionario.setRowCount(0);

        for (Pessoa p : pessoas.values()) {
            if(p instanceof Funcionario) {
                Funcionario f = (Funcionario) p;
                modeloTableFuncionario.addRow(new Object[]{
                    f.getNome(),
                    f.getCpf()
                });
            }
        }
    }

    // CARREGAR VENDAS
    public static void tabelaVendas(DefaultTableModel modelo, HashMap<String, RegistroVenda> vendas) {
        modelo.setRowCount(0);

        for(RegistroVenda v : vendas.values()) {
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
