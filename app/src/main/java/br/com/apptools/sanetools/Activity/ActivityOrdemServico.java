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
    private static final String KEY_DATA_GERACAO = "dataGeracao";
    private static final String KEY_DATA_BAIXA = "dataBaixa";
    private static final String KEY_TIPO_SERVICO = "tipoServico";
    private static final String KEY_LOGRADOURO = "logradouro";
    private static final String KEY_NUMERO = "numero";
    private static final String KEY_STATUS = "status";


    //public static final String URL = "http://172.24.152.169/apptools/sanetools/consultaOrdemServico.php"; //unis
    //public static final String URL = "http://192.168.43.217/apptools/sanetools/consultaOrdemServico.php"; //Xperia
    //public static final String URL = "http://192.168.1.30/apptools/sanetools/consultaOrdemServico.php"; //Modem 4G
    //public static final String URL = "http://localhosl/apptools/sanetools/consultaOrdemServico.php"; //Local
    public static final String URL = "http://192.168.1.99/apptools/sanetools/consultaOrdemServico.php"; //Casa

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
    public void onLoaded(List<OrdemServicoApp> ordemServicoList) {
        Log.v(TAG, "OdemServicoList:"+ordemServicoList);
        for (OrdemServicoApp ordemServico : ordemServicoList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_ID_ORDEMSERVICO, ordemServico.getIdOrdemServico());
            map.put(KEY_ID_IMOVEL, ordemServico.getIdImovel());
            map.put(KEY_DATA_GERACAO, ordemServico.getDataGeracao());
            map.put(KEY_DATA_BAIXA, ordemServico.getDataBaixa());
            map.put(KEY_TIPO_SERVICO, ordemServico.getTipoServico());
            map.put(KEY_LOGRADOURO, ordemServico.getLogradouro());
            map.put(KEY_NUMERO, ordemServico.getNumero());
            map.put(KEY_STATUS, ordemServico.getStatus());

            mOrdemServicoMapList.add(map);
        }
        loadListView();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Ops, verifique sua conexão!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mOrdemServicoMapList.get(i).get(KEY_ID_ORDEMSERVICO),Toast.LENGTH_LONG).show();
        /*TODO UM IDENTIFICADOR 'string' PARA CADA INTENT VINDO DA API (não podemos usar o id numero atual)*/

    }

    private void loadListView() {

        ListAdapter adapterOrdemServico = new SimpleAdapter(ActivityOrdemServico.this, mOrdemServicoMapList, R.layout.list_item_ordem_servico,

                new String[]{KEY_ID_ORDEMSERVICO, KEY_ID_IMOVEL, KEY_LOGRADOURO, KEY_NUMERO,
                        KEY_DATA_GERACAO, KEY_DATA_BAIXA, KEY_STATUS},
        new int[] {R.id.txtIdOrdemServico, R.id.txtIdImovel, R.id.txtLogradouro, R.id.txtNumero, R.id.txtDataGeracao,
                    R.id.txtDataBaixa, R.id.txtStatus});

        mLstvOrdemServico.setAdapter(adapterOrdemServico);
    }

}
