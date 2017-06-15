package br.com.apptools.sanetools.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.apptools.sanetools.LoadJson.LoadOrdemServicoJson;
import br.com.apptools.sanetools.R;
import br.com.apptools.sanetools.dominio.entidades.OrdemServicoApp;

public class ActivityOrdemServico extends AppCompatActivity implements LoadOrdemServicoJson.Listener, AdapterView.OnItemClickListener{

    private static final String KEY_ID_ORDEMSERVICO = "idOrdemServico";
    private static final String KEY_ID_IMOVEL = "idImovel";
    private static final String KEY_CPFEQUIPE = "cpf_equipe";
    private static final String KEY_TIPOSERVICO = "tipoServico";
    private static final String KEY_LOGRADOURO = "logradouro";
    private static final String KEY_NUMLOGRADOURO = "numLogradouro";
    private static final String KEY_DATAGERACAO = "dataGeracao";
    private static final String KEY_DATABAIXA = "dataBaixa";
    private static final String KEY_STATUS = "status";
    private static final String KEY_OBSERVACAO = "observacao";

    public static final String URL = "http://172.24.149.230/apptools/sanetools/consultaOS.php"; //Casa

    private static final String TAG = "ActivityOrdemServico";
    private List<HashMap<String, String>> mOrdemServicoMapList = new ArrayList<>();

    ListView mLstvOrdemServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordem_servico);

        mLstvOrdemServico = (ListView) findViewById(R.id.lstvOrdemServico);
        mLstvOrdemServico.setOnItemClickListener(this);
        new LoadOrdemServicoJson(this).execute(URL);

    }

    @Override
    public void onLoaded(List <OrdemServicoApp> ordemServicoList) {
        Log.v(TAG, "OrdemServicoList:" + ordemServicoList);
        for (OrdemServicoApp ordemServico : ordemServicoList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_ID_ORDEMSERVICO, ordemServico.getIdOrdemServico());
            map.put(KEY_ID_IMOVEL, ordemServico.getIdImovel());
            map.put(KEY_CPFEQUIPE, ordemServico.getCpf_equipe());
            map.put(KEY_LOGRADOURO, ordemServico.getLogradouro());
            map.put(KEY_NUMLOGRADOURO, ordemServico.getNumLogradouro());
            map.put(KEY_TIPOSERVICO, ordemServico.getTipoServico());
            map.put(KEY_DATAGERACAO, ordemServico.getDataGeracao());
            map.put(KEY_DATABAIXA, ordemServico.getDataBaixa());
            map.put(KEY_OBSERVACAO, ordemServico.getObservacao());
            map.put(KEY_STATUS, ordemServico.getStatus());

            mOrdemServicoMapList.add(map);
        }
        loadListView();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Erro ao listar as Ordens de Servico", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mOrdemServicoMapList.get(i).get(KEY_ID_ORDEMSERVICO),Toast.LENGTH_LONG).show();

        /*TODO UM IDENTIFICADOR 'string' PARA CADA INTENT VINDO DA API (não podemos usar o id numero atual)*/
    }

    private void loadListView() {

        ListAdapter adapterOrdemServico = new SimpleAdapter(ActivityOrdemServico.this, mOrdemServicoMapList, R.layout.list_item_ordem_servico,

                new String[]{KEY_ID_ORDEMSERVICO, KEY_ID_IMOVEL, KEY_TIPOSERVICO, KEY_DATAGERACAO, KEY_DATABAIXA, KEY_OBSERVACAO, KEY_STATUS},
        new int[] {R.id.txtIdOrdemServico, R.id.txtIdImovel, R.id.txtTipoServico, R.id.txtDataGeracao, R.id.txtDataBaixa, R.id.txtObservacao, R.id.txtStatus});

        mLstvOrdemServico.setAdapter(adapterOrdemServico);
    }

}
