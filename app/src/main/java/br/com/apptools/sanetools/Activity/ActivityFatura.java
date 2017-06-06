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

import br.com.apptools.sanetools.LoadJson.LoadFaturaJson;
import br.com.apptools.sanetools.R;
import br.com.apptools.sanetools.dominio.entidades.FaturaApp;

public class ActivityFatura extends AppCompatActivity implements LoadFaturaJson.Listener, AdapterView.OnItemClickListener{

    private static final String KEY_ID_FATURA = "idFatura";
    private static final String KEY_ID_IMOVEL = "idImovel";
    private static final String KEY_VALOR = "valor";
    private static final String KEY_MESREF = "mesRef";
    private static final String KEY_STATUS = "status";

    //public static final String URL = "http://172.24.152.169/apptools/sanetools/consultaFatura.php"; //unis
    //public static final String URL = "http://192.168.43.217/apptools/sanetools/consultaFatura.php"; //Xperia
    //public static final String URL = "http://192.168.1.30/apptools/sanetools/coconsultaFatura.php"; //Modem 4G
    //public static final String URL = "http://localhosl/apptools/sanetools/consultaFatura.php"; //Local
    public static final String URL = "http://192.168.1.99/apptools/sanetools/consultaFatura.php"; //Casa

    private static final String TAG = "ActivityFatura";
    private List<HashMap<String, String>> mFaturaMapList = new ArrayList<>();

    ListView mLstvFatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatura);

        mLstvFatura = (ListView) findViewById(R.id.lstvFatura);
        mLstvFatura.setOnItemClickListener(this);
        new LoadFaturaJson(this).execute(URL);

    }

    @Override
    public void onLoaded(List<FaturaApp> faturaList) {
        Log.v(TAG, "FaturaList:"+faturaList);
        for (FaturaApp fatura : faturaList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_ID_FATURA, fatura.getIdFatura());
            map.put(KEY_ID_IMOVEL, fatura.getIdImovel());
            map.put(KEY_VALOR, fatura.getValor());
            map.put(KEY_MESREF, fatura.getMesRef());
            map.put(KEY_STATUS, fatura.getStatus());

            mFaturaMapList.add(map);
        }
        loadListView();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Ops, verifique sua conexão!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mFaturaMapList.get(i).get(KEY_ID_FATURA),Toast.LENGTH_LONG).show();
        /*TODO UM IDENTIFICADOR 'string' PARA CADA INTENT VINDO DA API (não podemos usar o id numero atual)*/

    }

    private void loadListView() {

        ListAdapter adapterFatura = new SimpleAdapter(ActivityFatura.this, mFaturaMapList, R.layout.list_item_fatura,

                new String[]{KEY_ID_FATURA, KEY_ID_IMOVEL, KEY_MESREF, KEY_VALOR, KEY_STATUS},
        new int[] {R.id.txtIdFatura, R.id.txtMatImovel, R.id.txtMesRef, R.id.txtValorFatura, R.id.txtstatusFatura});

        mLstvFatura.setAdapter(adapterFatura);
    }

}
