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

public class ActivityEndereco extends AppCompatActivity implements LoadEnderecoJson.Listener, AdapterView.OnItemClickListener{


    private static final String KEY_LOGRADOURO = "logradouro";
    private static final String KEY_NUMERO = "numero";
    private static final String KEY_BAIRRO = "bairro";
    private static final String KEY_CEP = "cep";
    private static final String KEY_CIDADE = "cidade";


    public static final String URL = "http://172.24.149.230/apptools/sanetools/imoveis.php";
    private static final String TAG = "ActivityEndereco";

    private List<HashMap<String, String>> mEnderecoMapList = new ArrayList<>();


    ListView mLstvTesteBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_banco);

        mLstvTesteBanco = (ListView) findViewById(R.id.lstvTesteBanco);
        mLstvTesteBanco.setOnItemClickListener(this);
        new LoadEnderecoJson(this).execute(URL);

    }

    @Override
    public void onLoaded(List<EnderecoApp> enderecoList) {
        Log.v(TAG, "ListAndroid:"+enderecoList);
        for (EnderecoApp endereco : enderecoList) {

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
        Toast.makeText(this, "Ops, verifique sua conexÃ£o!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mEnderecoMapList.get(i).get(KEY_LOGRADOURO),Toast.LENGTH_LONG).show();
        /*TODO UM IDENTIFICADOR 'string' PARA CADA INTENT VINDO DA API (nÃ£o podemos usar o id numero atual)*/

    }

    private void loadListView() {

        ListAdapter adapterEndereco = new SimpleAdapter(ActivityEndereco.this, mEnderecoMapList, R.layout.list_item_endereco,

                new String[]{KEY_LOGRADOURO, KEY_NUMERO},
        new int[] {R.id.txtLogradouro, R.id.txtNumero});

        mLstvTesteBanco.setAdapter(adapterEndereco);
    }

}
