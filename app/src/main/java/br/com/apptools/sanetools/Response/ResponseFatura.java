package br.com.apptools.sanetools.Response;

import java.util.ArrayList;
import java.util.List;

import br.com.apptools.sanetools.dominio.entidades.FaturaApp;

public class ResponseFatura {

    private List<FaturaApp> fatura = new ArrayList<FaturaApp>();

    public List<FaturaApp> getMenu() {
        return fatura;
    }
}