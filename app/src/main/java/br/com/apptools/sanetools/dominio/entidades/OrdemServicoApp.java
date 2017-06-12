package br.com.apptools.sanetools.dominio.entidades;

public class OrdemServicoApp {

    private String idOrdemServico;
    private String idImovel;
    private String cpf_equipe;
    private String tipoServico;
    private String logradouro;
    private String numLogradouro;
    private String dataGeracao;
    private String dataBaixa;
    private String observacao;
    private String status;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumLogradouro() {
        return numLogradouro;
    }

    public void setNumLogradouro(String numLogradouro) {
        this.numLogradouro = numLogradouro;
    }

    public String getIdOrdemServico() {
        return idOrdemServico;
    }

    public void setIdOrdemServico(String idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }

    public String getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(String idImovel) {
        this.idImovel = idImovel;
    }

    public String getCpf_equipe() {
        return cpf_equipe;
    }

    public void setCpf_equipe(String cpf_equipe) {
        this.cpf_equipe = cpf_equipe;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(String dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public String getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(String dataBaixa) {
        this.dataBaixa = dataBaixa;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
