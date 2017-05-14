package br.com.apptools.sanetools.dominio.entidades;

public class OrdemServico {

    //Declaração de variaveis

    private Integer _idOS;
    private String dataGeracao;
    private String codServico;
    private String dataLimite;
    private String observacoes;

    //Método construtor vazio
    public OrdemServico () {

    }

    public OrdemServico (Integer _idOS, String dataGeracao, String codServico, String dataLimite, String observacoes) {

        this._idOS = _idOS;
        this.dataGeracao = dataGeracao;
        this.codServico = codServico;
        this.dataLimite = dataLimite;
        this.observacoes = observacoes;
    }

    //Get's e Set's

    public Integer get_idOS() {
        return _idOS;
    }

    public void set_idOS(Integer _idOS) {
        this._idOS = _idOS;
    }

    public String getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(String dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getCodServico() {
        return codServico;
    }

    public void setCodServico(String codServico) {
        this.codServico = codServico;
    }

    public String getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(String dataLimite) {
        this.dataLimite = dataLimite;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String toString() {

        return "Data Geração: 28/11/2016" + " - " + "Cod. Serv.: " + codServico + " - " + "Obs.: " + observacoes + " ";
    }
}
