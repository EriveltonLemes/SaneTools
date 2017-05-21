package br.com.apptools.sanetools;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import br.com.apptools.sanetools.database.Conexao;
import br.com.apptools.sanetools.database.DataBaseHelper;
import br.com.apptools.sanetools.dominio.RepositorioPessoa;

public class Login extends AppCompatActivity {

    //Declaração de variaveis para retorno de dados
    EditText mEdtCPF;
    EditText mEdtSenha;
    Button mBtnLogar;
    Button mBtnLimpar;
    TextView mTxtCadastro;

    DataBaseHelper dataBaseHelper;
    RepositorioPessoa repositorioPessoa;

    //Váriaveis para teste de banco externo
    //String url = "";
    //String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        dataBaseHelper = new DataBaseHelper(this);
        mEdtCPF = (EditText) findViewById(R.id.edtCPF);
        mEdtSenha = (EditText) findViewById(R.id.edtSenha);
        mBtnLogar = (Button) findViewById(R.id.btnEntrar);
        mBtnLimpar = (Button) findViewById(R.id.btnLimparLogin);
        mTxtCadastro = (TextView) findViewById(R.id.txtCadastro);

        repositorioPessoa = new RepositorioPessoa(this);

        //Função de activity_login em base de dados externa (teste)
        /*mBtnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()) {

                    String cpf = mEdtCPF.getText().toString();
                    String senha = mEdtSenha.getText().toString();

                    if (cpf.isEmpty() || senha.isEmpty()) {

                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();
                    } else {

                        //url = "http://192.168.1.99/apptools/sanetools/logar.php";
                        //url = "http://172.24.152.185/apptools/sanetools/logar.php";
                        //url = "http://192.168.43.217/apptools/sanetools/logar.php";

                        parametros = "cpf_equipe="+cpf+"&senha="+senha;

                        new SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();
                }
            }
        });*/

        //Função do botão que limpa (apaga os caracteres) os campos de CPF e SENHA da tela de content_login
        mBtnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEdtCPF.getText().clear();
                mEdtSenha.getText().clear();

            }
        });

        //Função do TextView que abre a tela de activity_cadastro de novo usuário
        mTxtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CadastrarUsuario.class);
                startActivity(intent);
            }
        });
    }

    /*private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String resultado) {

            if(resultado.contains("login_ok")) {

                Intent abreHome = new Intent(Login.this, HomeCliente.class);
                startActivity(abreHome);

            } else {
                Toast.makeText(getApplicationContext(), "Usuário e/ou senha estão incorretos", Toast.LENGTH_LONG).show();
            }
        }
    }*/

    //Função de activity_login. Testa se o usuário existe e se os dados de activity_login estão corretos.
    public void logar (View view) {
        String cpf = mEdtCPF.getText().toString();
        String senha = mEdtSenha.getText().toString();

        boolean validacao = true;

        if (cpf == null || cpf.equals("")) {
            validacao = false;
            Toast.makeText(getApplicationContext(),"Nenhum campo pode estar vazio",Toast.LENGTH_LONG).show();
        }

        if (senha == null || senha.equals("")) {
            validacao = false;
            Toast.makeText(getApplicationContext(),"Nenhum campo pode estar vazio",Toast.LENGTH_LONG).show();
        }

        if (validacao) {
            if (repositorioPessoa.logarPessoa(cpf, senha)){
                startActivity(new Intent(this, HomeCliente.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(),"Usuário e/ou senha incorretos", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    //Menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    //Lista de opções do menu superior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Ação de click para opção de INFORMAÇÕES SOBRE O APLICATIVO
        if (id == R.id.acaoSobre) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("        Desenvolvido por:\n" +
                    "       AppTools Tecnologia\n\n" +
                    "       Versão da aplicação: 1.0.0\n\n" +
                    "       Contato:\n" +
                    "       apptools@apptools.com\n" +
                    "       (35)99999-9999");
            dlg.setNeutralButton("OK", null);
            dlg.show();

            return true;
        }

        //Ação de click para opção de AJUDA
        if (id == R.id.acaoAjuda) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("Para realizar o Login no aplicativo, é necessário o activity_cadastro de seus dados, como: CPF, nome, email e senha.\n\n" +
                    "Na tela de Login, clique na opção 'Primeiro Acesso? Cadastre-se' e preencha o formulário.");
            dlg.setNeutralButton("OK", null);
            dlg.show();

            return true;
        }

        //Ação de click para a opção de SAIR do App. Fecha o App
        if (id == R.id.acaoSair) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}