package classes;

public class Cliente extends Pessoa {
    private String endereco;
    private String telefone;

    public Cliente() { super("", ""); } // chama Pessoa(nome, cpf) com valores vazios
    
    public Cliente(String nome, String cpf, String endereco, String telefone) {
        super(nome, cpf);
        this.endereco = endereco;
        this.telefone = telefone;
    }
    
    @Override
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereço) {
        this.endereco = endereço;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public tipoPessoa getTipo(){
        return tipoPessoa.CLIENTE;
    }
    
}
