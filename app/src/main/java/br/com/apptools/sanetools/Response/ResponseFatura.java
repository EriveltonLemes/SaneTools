package br.com.apptools.sanetools.Response;

import java.util.ArrayList;
import java.util.List;

import br.com.apptools.sanetools.dominio.entidades.ImovelApp;

public class ResponseFatura {

    private List<ImovelApp> endereco = new ArrayList<ImovelApp>();

    public List<ImovelApp> getMenu() {
        return endereco;
    }
}