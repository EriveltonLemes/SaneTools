package br.com.apptools.sanetools.dominio.entidades;

public class UsuarioApp {

    private String cpf_equipe;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String tipo;

    public String getCpf_equipe() {
        return cpf_equipe;
    }

    public void setCpf_equipe(String cpf_equipe) {
        this.cpf_equipe = cpf_equipe;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
