package br.com.apptools.sanetools.Response;

import java.util.ArrayList;
import java.util.List;

import br.com.apptools.sanetools.dominio.entidades.OrdemServicoApp;

public class ResponseOrdemServico {

    private List<OrdemServicoApp> ordem_servico = new ArrayList<OrdemServicoApp>();

    public List<OrdemServicoApp> getMenu() {
        return ordem_servico;
    }
}