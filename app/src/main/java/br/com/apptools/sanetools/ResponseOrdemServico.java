package br.com.apptools.sanetools;

import java.util.ArrayList;
import java.util.List;

import br.com.apptools.sanetools.dominio.entidades.OrdemServicoApp;

public class ResponseOrdemServico {

    private List<OrdemServicoApp> ordemServico = new ArrayList<OrdemServicoApp>();

    public List<OrdemServicoApp> getMenu() {
        return ordemServico;
    }
}