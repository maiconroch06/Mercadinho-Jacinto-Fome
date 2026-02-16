package classes;

public class Funcionario extends Pessoa{

    public Funcionario() { super("", ""); } // chama Pessoa(nome, cpf) com valores vazios
    
    public Funcionario(String nome, String CPF) {
        super(nome, CPF);
    }
    
    @Override
    public tipoPessoa getTipo() {
        return tipoPessoa.FUNCIONARIO;
   }
    
}
