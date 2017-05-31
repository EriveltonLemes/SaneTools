package br.com.apptools.sanetools;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import br.com.apptools.sanetools.database.Conexao;
//import br.com.apptools.sanetools.database.DataBaseHelper;
//import br.com.apptools.sanetools.dominio.RepositorioPessoa;
//import br.com.apptools.sanetools.dominio.entidades.Pessoa;

public class CadastraUsuario extends AppCompatActivity {

    //Teste de activity_login
    String url = "";
    String parametros = "";

    //Declaração de variaveis para recuperação de dados
    EditText mEdtInserirCPF;
    EditText mEdtInserirNome;
    EditText mEdtInserirSenha;
    EditText mEdtInserirSenha1;
    EditText mEdtInserirEmail;
    EditText mEdtInserirTelefone;
    Button mBtnSalvarCad;
    Button mBtnCancelarCad;

    //RepositorioPessoa repositorioPessoa;
    //Pessoa pessoa;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mEdtInserirCPF = (EditText) findViewById(R.id.edtInserirCPF);
        mEdtInserirNome = (EditText) findViewById(R.id.edtInserirNome);
        mEdtInserirSenha = (EditText) findViewById(R.id.edtInserirSenha);
        mEdtInserirSenha1 = (EditText) findViewById(R.id.edtInserirSenha1);
        mEdtInserirEmail = (EditText) findViewById(R.id.edtInserirEmail);
        mEdtInserirTelefone = (EditText) findViewById(R.id.edtInserirTelefone);
        mBtnSalvarCad = (Button) findViewById(R.id.btnSalvarCad);
        mBtnCancelarCad = (Button) findViewById(R.id.btnCancelarCad);

                mBtnSalvarCad.setOnClickListener(new View.OnClickListener() {
            //Função do botão de gravar o activity_cadastro do usuário
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()) {

                    String cpf = mEdtInserirCPF.getText().toString();
                    String nome = mEdtInserirNome.getText().toString();
                    String email = mEdtInserirEmail.getText().toString();
                    String senha = mEdtInserirSenha.getText().toString();
                    String senha1 = mEdtInserirSenha1.getText().toString();
                    String telefone = mEdtInserirTelefone.getText().toString();

                    if (cpf.isEmpty() || nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty() || senha1.isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();

                    } else {
                        //url = "http://192.168.1.99/apptools/sanetools/registrar.php";
                        //url = "http://172.24.149.230/apptools/sanetools/registrar.php";
                        url = "http://192.168.43.217/apptools/sanetools/registrar.php";

                        parametros = "cpf_equipe=" + cpf + "&nome=" + nome + "&telefone" + telefone + "&email=" + email + "&senha=" + senha;

                        new SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Função do botão de cancelar o activity_cadastro do usuário
        mBtnCancelarCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastraUsuario.this, Login.class);
                startActivity(intent);
                finish();
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

            if(resultado.contains("cpf_erro")) {
                Toast.makeText(getApplicationContext(), "CPF já cadastrado", Toast.LENGTH_LONG).show();

            }else if(resultado.contains("registroOK")) {
            Toast.makeText(getApplicationContext(), "Usuário Cadstrado com sucesso", Toast.LENGTH_LONG).show();
                Intent voltaCadastro = new Intent(CadastraUsuario.this, Login.class);
                startActivity(voltaCadastro);

            } else {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro ao registrar", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
