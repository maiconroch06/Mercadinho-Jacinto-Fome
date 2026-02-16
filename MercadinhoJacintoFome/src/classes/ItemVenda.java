package classes;

public class ItemVenda extends Produto {
    
    private double valorTotal;
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public double getValorTotal() {
        return getQuantidade() * getValorUnitario();
    }
}
