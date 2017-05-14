package br.com.apptools.sanetools.dominio.entidades;

public class Pessoa {

    //Declaração de variaveis

    private Integer _idPessoa;
    private String cpf;
    private String nome;
    private String senha;
    private String email;

    //Métodos construtores

    public Pessoa () {

    }

    public Pessoa (Integer _idPessoa, String cpf, String nome, String senha, String email) {

        this._idPessoa = _idPessoa;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }

    //Get's e Set's

    public Integer get_idPessoa() {
        return _idPessoa;
    }

    public void set_idPessoa(Integer _idPessoa) {
        this._idPessoa = _idPessoa;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
