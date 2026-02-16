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
    public tipoPessoa getTipo(){
        return tipoPessoa.CLIENTE;
    }
    
    @Override
    public String getEndereco() {
        return endereco;
    }

    @Override
    public String getTelefone() {
        return telefone;
    }
    
    public void setEndereco(String endereço) {
        this.endereco = endereço;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
