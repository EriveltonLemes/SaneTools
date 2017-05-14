package br.com.apptools.sanetools.dominio.entidades;

public class Fatura {

    //Criação dos atributos
    private Integer _idFatura;
    private String mesReferencia;
    private double valor;
    private int consumo;
    private String status;

    //Métodos construtores
    public Fatura (){
    }

    public Fatura (Integer _idFatura, String mesReferencia, double valor, int consumo, String status) {

        this._idFatura = _idFatura;
        this.mesReferencia = mesReferencia;
        this.valor = valor;
        this.consumo = consumo;
        this.status = status;
    }

    //Get's e Set'


    public Integer get_idFatura() {
        return _idFatura;
    }

    public void set_idFatura(Integer _idFatura) {
        this._idFatura = _idFatura;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString () {

        return "Mês Ref.: " + mesReferencia + " " + " - " + "Consumo: " +
                consumo + " " + " m³" + " - " + "Valor: " + valor + " " + " - " + status + " ";
    }
}
