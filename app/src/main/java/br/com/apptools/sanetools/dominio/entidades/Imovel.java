package br.com.apptools.sanetools.dominio.entidades;

public class Imovel {

    //Declaração de variaveis

    private Integer _idImovel;
    private String tipoLogradouro;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String cep;


    //Métodos construtores
    public Imovel (){
    }

    public Imovel(Integer _idImovel, String tipoLogradouro, String logradouro, String numero, String bairro, String cidade, String cep) {

        this._idImovel = _idImovel;
        this.tipoLogradouro = tipoLogradouro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    //Get's e Set's

    public Integer get_idImovel() {
        return _idImovel;
    }

    public void set_idImovel(Integer _idImovel) {
        this._idImovel = _idImovel;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String toString () {

        return logradouro + " " + ", " + numero + " - " + bairro + ", " + cidade;
    }
}
