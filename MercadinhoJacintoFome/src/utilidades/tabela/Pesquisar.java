package utilidades.tabela;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import classes.*;
import services.*;

public class Pesquisar {
    public static void pesqProduto(String codigo, DefaultTableModel modeloTableProduto, ProdutoService produtos) {
        modeloTableProduto.setRowCount(0);

        Produto p = produtos.consultar(codigo);

        if (p != null) {
            modeloTableProduto.addRow(new Object[]{
                p.getCodigoProduto(),
                p.getDescricao(),
                p.getQuantidade(),
                p.getValorUnitario()
            });
            
        } else {
            Carregar.tabelaProdutos(modeloTableProduto, produtos.listarTodos());
            JOptionPane.showMessageDialog(null, "Produto não encontrado");
        }
    }

    public static void pesqCliente(String cpf, DefaultTableModel modeloTableCliente, ClienteService clientes) {
        modeloTableCliente.setRowCount(0);

        Cliente c = clientes.consultar(cpf);

        if (c != null) {
            modeloTableCliente.addRow(new Object[]{
                c.getNome(),
                c.getCpf(),
                c.getEndereco(),
                c.getTelefone()
            });
            
        } else {
            Carregar.tabelaClientes(modeloTableCliente, clientes.listarTodos());
            JOptionPane.showMessageDialog(null, "Cliente não encontrado");
        }
    }

    public static void pesqFuncionario(String cpf, DefaultTableModel modeloTableFuncionario, FuncionarioService funcionarios) {
        modeloTableFuncionario.setRowCount(0);

        Funcionario f = funcionarios.consultar(cpf);

        if (f != null) {
            modeloTableFuncionario.addRow(new Object[]{
                f.getNome(),
                f.getCpf()
            });
            
        } else {
            Carregar.tabelaFuncionarios(modeloTableFuncionario, funcionarios.listarTodos());
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
        }
    }

    public static void pesqVenda(String id, DefaultTableModel modeloTableVenda, VendaService vendas) {
        modeloTableVenda.setRowCount(0);

        RegistroVenda v = vendas.consultar(id);

        if (v != null) {
            modeloTableVenda.addRow(new Object[]{
                v.getIdVenda(),
                String.format("R$ %.2f", v.getTotalValor()),
                v.getItensComprados().size()
            });
            
        } else {
            Carregar.tabelaVendas(modeloTableVenda, vendas.listarTodas());
            JOptionPane.showMessageDialog(null, "Venda não encontrada");
        }
    }
    
}
