package br.com.apptools.sanetools;

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

public class ActivityImoveis extends AppCompatActivity implements LoadImoveisJson.Listener, AdapterView.OnItemClickListener{

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

    private static final String TAG = "ActivityImoveis";
    private List<HashMap<String, String>> mEnderecoMapList = new ArrayList<>();

    ListView mLstvEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imoveis);

        mLstvEndereco = (ListView) findViewById(R.id.lstvEndereco);
        mLstvEndereco.setOnItemClickListener(this);
        new LoadImoveisJson(this).execute(URL);

    }

    @Override
    public void onLoaded(List<ImoveisApp> enderecoList) {
        Log.v(TAG, "EnderecoList:"+enderecoList);
        for (ImoveisApp endereco : enderecoList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_LOGRADOURO, endereco.getLogradouro());
            map.put(KEY_NUMERO, endereco.getNumero());
            map.put(KEY_BAIRRO, endereco.getBairro());
            map.put(KEY_CEP, endereco.getCep());
            map.put(KEY_CIDADE, endereco.getCidade());

            mEnderecoMapList.add(map);
        }
        loadListView();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Ops, verifique sua conexão!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mEnderecoMapList.get(i).get(KEY_LOGRADOURO),Toast.LENGTH_LONG).show();
        /*TODO UM IDENTIFICADOR 'string' PARA CADA INTENT VINDO DA API (não podemos usar o id numero atual)*/

    }

    private void loadListView() {

        ListAdapter adapterEndereco = new SimpleAdapter(ActivityImoveis.this, mEnderecoMapList, R.layout.list_item_imoveis,

                new String[]{KEY_LOGRADOURO, KEY_NUMERO, KEY_BAIRRO, KEY_CEP, KEY_CIDADE},
        new int[] {R.id.txtLogradouro, R.id.txtNumero, R.id.txtBairro, R.id.txtCep, R.id.txtCidade});

        mLstvEndereco.setAdapter(adapterEndereco);
    }

}
