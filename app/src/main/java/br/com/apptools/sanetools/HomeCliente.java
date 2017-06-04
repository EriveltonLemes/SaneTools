package br.com.apptools.sanetools;

import android.content.Intent;
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

import br.com.apptools.sanetools.Activity.ActivityImovel;
import br.com.apptools.sanetools.Activity.ActivityOrdemServico;

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


            }
        });

        mImgBtnConsultaServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultaServico = new Intent(HomeCliente.this, ActivityOrdemServico.class);
                startActivity(consultaServico);
            }
        });

        mImgBtnConsultaFatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mImgBtnConsultaImovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultaImovel = new Intent(HomeCliente.this, ActivityImovel.class);
                startActivity(consultaImovel);
            }

        });

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


            //Abre a tela de consulta de serviços já solicitados
        } else if (id == R.id.menuConsultarServico) {


            //Abre a tela de consulta de activity_faturas
        } else if (id == R.id.menuConsultaFatura) {


            //Abre a tela de consulta de imovel que já foram solicitados ordens de serviço
        } else if (id == R.id.menuConsultarImovel) {
            Intent intent = new Intent(HomeCliente.this, ActivityImovel.class);
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
