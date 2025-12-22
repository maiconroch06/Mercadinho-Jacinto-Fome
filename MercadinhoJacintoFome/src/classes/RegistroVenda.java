package classes;

import java.util.ArrayList;

public class RegistroVenda {
    private String idVenda;
    private String cpfFuncionario;
    private String cpfCliente;
    private double totalValor;
    private String metodo;
    private ArrayList<ItemVenda> itensComprados;
    
    public void setIdVenda(String idVenda) { 
        this.idVenda = idVenda; 
    }
    
    public String getIdVenda() { 
        return idVenda; 
    }
    
    public String getCpfCliente() {
        return cpfCliente;
    }
    
    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }
    
    public void setCpfFuncionario(String nomeFuncionario) {
        this.cpfFuncionario = nomeFuncionario;
    }
    
    public void setTotalValor(double totalValor) {
        this.totalValor = totalValor;
    }

    public double getTotalValor() { 
        return totalValor; 
    }
    
    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }
    
    public String getMetodo() {
        return metodo;
    }
    
    public ArrayList<ItemVenda> getItensComprados() {
        return itensComprados;
    }

    public void setItensComprados(ArrayList<ItemVenda> itensComprados) {
        this.itensComprados = itensComprados;
    }
    
    public int getItensTotal() {
        int soma = 0;
        for (ItemVenda iv : itensComprados) {
            soma += iv.getQuantidade();
        }
        return soma;
    }
    
}