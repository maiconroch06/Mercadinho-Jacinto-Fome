package classes;

public abstract class Pessoa {
    private String cpf;
    private String nome;

    public Pessoa() {}

    public abstract tipoPessoa getTipo();
    
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEndereco() {
        return null;
    }

    public String getTelefone() {
        return null;
    }
    
    public enum tipoPessoa {
        CLIENTE,
        FUNCIONARIO
    }

}

