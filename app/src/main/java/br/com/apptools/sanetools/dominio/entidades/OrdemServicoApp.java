package br.com.apptools.sanetools.dominio.entidades;

public class OrdemServicoApp {

    private String idOrdemServico;
    private String idImovel;
    private String dataGeracao;
    private String dataBaixa;
    private String tipoServico;
    private String status;

    public String getIdOrdemServico() {
        return idOrdemServico;
    }

    public String getIdImovel() {
        return idImovel;
    }

    public String getDataGeracao() {
        return dataGeracao;
    }

    public String getDataBaixa() {
        return dataBaixa;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public String getStatus() {
        return status;
    }
}
