package br.com.apptools.sanetools;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import br.com.apptools.sanetools.database.Conexao;

public class HomeCliente extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String url = "";
    String parametros = "";

    ImageButton mImgBtnSolicitaServico;
    ImageButton mImgBtnConsultaServico;
    ImageButton mImgBtnConsultaFatura;
    ImageButton mImgBtnConsultaImovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mImgBtnSolicitaServico = (ImageButton) findViewById(R.id.imgbtnSolicitaServico);
        mImgBtnConsultaServico = (ImageButton) findViewById(R.id.imgbtnConsultaServico);
        mImgBtnConsultaFatura = (ImageButton) findViewById(R.id.imgbtnConsultaFatura);
        mImgBtnConsultaImovel = (ImageButton) findViewById(R.id.imgbtnConsultaImovel);

        mImgBtnSolicitaServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent solicitaServico = new Intent(HomeCliente.this, SolicitaServico.class);
                startActivity(solicitaServico);
            }
        });

        mImgBtnConsultaServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultaServico = new Intent(HomeCliente.this, ConsultaOsCliente.class);
                startActivity(consultaServico);
            }
        });

        mImgBtnConsultaFatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultaFatura = new Intent(HomeCliente.this, ConsultaFatura.class);
                startActivity(consultaFatura);
            }
        });

        mImgBtnConsultaImovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultaImovel = new Intent(HomeCliente.this, ConsultaImovel.class);

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if(networkInfo != null && networkInfo.isConnected()) {

                        String logradouro, numero, bairro, cep, cidade;

                        //Trabalhar neste toast
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();

                    } else {
                        url = "http://192.168.1.99/apptools/sanetools/teste.php";
                        //url = "http://172.24.152.185/apptools/sanetools/registrar.php";
                        //url = "http://192.168.43.217/apptools/sanetools/registrar.php";

                        parametros = "logradouro=" + logradouro + "&numero=" + numero;

                        new SolicitaDados().execute(url);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhuma conexão foi detectada", Toast.LENGTH_LONG).show();

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

            if(resultado.contains("imovel_erro")) {
                Toast.makeText(getApplicationContext(), "Nenhum imóvel cadastrado", Toast.LENGTH_LONG).show();

            }else if(resultado.contains("imovel_ok")) {
                Intent listaImovel = new Intent(HomeCliente.this, ConsultaImovel.class);
                startActivity(listaImovel);

            } else {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro ao consultar", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_cliente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Abre a tela de informações sobre o App
        if (id == R.id.acaoSobre) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("        Desenvolvido por: \n" +
                    "       AppTools Tecnologia \n\n" +
                    "       Versão do aplicativo: 1.1.0 \n\n" +
                    "       Contato: \n" +
                    "       apptoolstecnologia@gmail.com \n" +
                    "       (35)99999-9999");
            dlg.setNeutralButton("OK", null);
            dlg.show();

            return true;
        }

        //Abre a tela com tópicos de ajuda sobre o App
        if (id == R.id.acaoAjuda) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("Para realizar o Login no aplicativo, é necessário o activity_cadastro de seus dados, como: CPF, nome, email e senha. \n\n" +
                    "Na tela de Login, clique na opção 'Primeiro Acesso? Cadastre-se' e preencha o formulário.");
            dlg.setNeutralButton("OK", null);
            dlg.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        //Abre a tela de solicitar serviço
        if (id == R.id.menuSolicitaServico) {
            Intent intent = new Intent(HomeCliente.this, SolicitaServico.class);
            startActivity(intent);
            finish();

            //Abre a tela de consulta de serviços já solicitados
        } else if (id == R.id.menuConsultarServico) {
            Intent intent = new Intent(HomeCliente.this, ConsultaOsCliente.class);
            startActivity(intent);
            finish();

            //Abre a tela de consulta de activity_faturas
        } else if (id == R.id.menuConsultaFatura) {
            Intent intent = new Intent(HomeCliente.this, ConsultaFatura.class);
            startActivity(intent);
            finish();

            //Abre a tela de consulta de imovel que já foram solicitados ordens de serviço
        } else if (id == R.id.menuConsultarImovel) {
            Intent intent = new Intent(HomeCliente.this, ConsultaImovel.class);
            startActivity(intent);
            finish();

            //Verificar possibilidade de compartilhamento de informações
        } else if (id == R.id.menuCompartilhar) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("Será implementado o método 'Compartilhar', com o intuiuto de que o usuário " +
                    "indique o App a outras pessoas. Será enviado ao destinatário o link para download do App juntamente com a descrição.");
            dlg.setNeutralButton("OK", null);
            dlg.show();

        } else if (id == R.id.menuSugestoes) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage(" Envie dúvidas e sugestões \n\n" +
                    "        Contato: \n" +
                    "        apptoolstecnologia@gmail.com \n" +
                    "        (35)99999-9999");
            dlg.setNeutralButton("OK", null);
            dlg.show();

            //Fecha a aplicação
        } else if (id == R.id.menuSair) {
            Intent intent = new Intent(HomeCliente.this, Login.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
