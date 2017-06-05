package br.com.apptools.sanetools.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import br.com.apptools.sanetools.LoadJson.LoadImovelJson;
import br.com.apptools.sanetools.R;
import br.com.apptools.sanetools.dominio.entidades.ImovelApp;

public class ActivityImovel extends AppCompatActivity implements LoadImovelJson.Listener, AdapterView.OnItemClickListener{

    private static final String KEY_LOGRADOURO = "logradouro";
    private static final String KEY_NUMERO = "numero";
    private static final String KEY_BAIRRO = "bairro";
    private static final String KEY_CEP = "cep";
    private static final String KEY_CIDADE = "cidade";


    //public static final String URL = "http://172.24.149.230/apptools/sanetools/imoveis.php"; //unis
    //public static final String URL = "http://192.168.43.217/apptools/sanetools/imoveis.php"; //Xperia
    //public static final String URL = "http://192.168.1.30/apptools/sanetools/imoveis.php"; //Modem 4G
    //public static final String URL = "http://localhosl/apptools/sanetools/imoveis.php"; //Local
    public static final String URL = "http://192.168.1.99/apptools/sanetools/imoveis.php"; //Casa

    private static final String TAG = "ActivityImovel";
    private List<HashMap<String, String>> mImovelMapList = new ArrayList<>();

    ListView mLstvImovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imovel);

        mLstvImovel = (ListView) findViewById(R.id.lstvImovel);
        mLstvImovel.setOnItemClickListener(this);
        new LoadImovelJson(this).execute(URL);

    }

    @Override
    public void onLoaded(List<ImovelApp> imovelList) {
        Log.v(TAG, "EnderecoList:"+imovelList);
        for (ImovelApp imovel : imovelList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_LOGRADOURO, imovel.getLogradouro());
            map.put(KEY_NUMERO, imovel.getNumero());
            map.put(KEY_BAIRRO, imovel.getBairro());
            map.put(KEY_CEP, imovel.getCep());
            map.put(KEY_CIDADE, imovel.getCidade());

            mImovelMapList.add(map);
        }
        loadListView();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Ops, verifique sua conexão!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mImovelMapList.get(i).get(KEY_LOGRADOURO),Toast.LENGTH_LONG).show();
        /*TODO UM IDENTIFICADOR 'string' PARA CADA INTENT VINDO DA API (não podemos usar o id numero atual)*/

    }

    private void loadListView() {

        ListAdapter adapterImovel = new SimpleAdapter(ActivityImovel.this, mImovelMapList, R.layout.list_item_imovel,

                new String[]{KEY_LOGRADOURO, KEY_NUMERO, KEY_BAIRRO, KEY_CEP, KEY_CIDADE},
        new int[] {R.id.txtLogradouro, R.id.txtNumero, R.id.txtBairro, R.id.txtCep, R.id.txtCidade});

        mLstvImovel.setAdapter(adapterImovel);
    }

}
