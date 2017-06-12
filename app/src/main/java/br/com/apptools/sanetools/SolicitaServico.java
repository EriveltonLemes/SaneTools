package br.com.apptools.sanetools;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SolicitaServico extends AppCompatActivity{

    String url = "";
    String parametros = "";

    TextView mtxtLogradouroSel;

    Button mBtnGravarOS;
    Button mBtnCancelarOS;
    Spinner mSpnTipoServico;
    EditText mEdtObservacoes;
    ArrayAdapter<String> mAdpTipoServico;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicita_servico);

        mtxtLogradouroSel = (TextView) findViewById(R.id.txtLogradouroSel);
        mtxtLogradouroSel.setText(getIntent().getStringExtra("logradouro"));

        mBtnGravarOS = (Button) findViewById(R.id.btnGravarOS);
        mBtnCancelarOS = (Button) findViewById(R.id.btnCancelarOS);
        mEdtObservacoes = (EditText)findViewById(R.id.edtObservacoes);

        //Tipos de serviços disponiveis para geração da ordem de serviço
        mSpnTipoServico = (Spinner) findViewById(R.id.spnCodServico);
        mAdpTipoServico = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item);
        mAdpTipoServico.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpnTipoServico.setAdapter(mAdpTipoServico);
        mAdpTipoServico.add("Selecione o Tipo de Serviço:");
        mAdpTipoServico.add("100800 - Vazamento de água - Calçada");
        mAdpTipoServico.add("100801 - Vazamento de água - Asfalto");
        mAdpTipoServico.add("200800 - Vazamento de Esgoto - Calçada");
        mAdpTipoServico.add("200801 - Vazamento de Esgoto - Asfalto");
        mAdpTipoServico.add("300100 - Tamponamento a pedido");
        mAdpTipoServico.add("400100 - Religação água");


        mBtnGravarOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()) {

                    String observacao = mEdtObservacoes.getText().toString();
                    String logradouro = mtxtLogradouroSel.getText().toString();
                    String tipoServico = (String.valueOf(mSpnTipoServico.getSelectedItem()));


                    if (observacao.isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();

                    } else {

                        url = "http://192.168.1.99/apptools/sanetools/insereOS.php"; //Casa

                        parametros = "observacao=" + observacao + "&logradouro=" + logradouro + "&tipoServico=" + tipoServico;

                        new SolicitaServico.SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String resultado) {

            if(resultado.contains("servicoErro")) {
                Toast.makeText(getApplicationContext(), "Serviço já gerado para este endereço", Toast.LENGTH_LONG).show();

            }else if(resultado.contains("servicoOK")) {
                Toast.makeText(getApplicationContext(), "Ordem de serviço gerada com sucesso", Toast.LENGTH_LONG).show();

                Intent voltaCadastro = new Intent(SolicitaServico.this, HomeCliente.class);
                startActivity(voltaCadastro);

            } else {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro ao gerar a ordem de serviço", Toast.LENGTH_LONG).show();
            }
        }
    }
}
