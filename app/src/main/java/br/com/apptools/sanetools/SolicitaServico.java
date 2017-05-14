package br.com.apptools.sanetools;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.apptools.sanetools.database.DataBaseHelper;
import br.com.apptools.sanetools.dominio.RepositorioOrdemServico;
import br.com.apptools.sanetools.dominio.entidades.Imovel;
import br.com.apptools.sanetools.dominio.entidades.OrdemServico;
import br.com.apptools.sanetools.dominio.RepositorioImovel;

public class SolicitaServico extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Declaração de variaveis para recuperação de dados
    Button mBtnGravarOS;
    Button mBtnCancelarOS;
    Spinner mSpnTipoServico;
    EditText mEdtObservacoes;
    EditText mEdtCEP;
    EditText mEdtNumero;
    EditText mEdtLogradouro;
    EditText mEdtCidade;
    EditText mEdtBairro;
    ArrayAdapter<String> mAdpTipoServico;

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase database;
    RepositorioOrdemServico repositorioOrdemServico;
    OrdemServico ordemServico;
    RepositorioImovel repositorioImovel;
    Imovel imovel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicita_servico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Código do menu superior e Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Fim do código do menu superior e Drawer

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

        ordemServico = new OrdemServico();
        imovel = new Imovel();

        try {
            dataBaseHelper = new DataBaseHelper(this);
            database = dataBaseHelper.getWritableDatabase();

            repositorioOrdemServico = new RepositorioOrdemServico(database);
            repositorioImovel = new RepositorioImovel(database);

        }catch (SQLException ex) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("Erro ao persistir o banco: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

        //Ação de gravar a ordem de serviço
        mBtnGravarOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserirImovel();
                inserirServico();
            }
        });

        //Ação de cancelar a solicitação de OS e fechar a tela de solicitação de OS
        mBtnCancelarOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Método de inserir dados do serviço no banco
    private void inserirServico () {

        try {
            ordemServico = new OrdemServico();

            //Inserção de dados do serviço

            //ordemServico.setDataGeracao();
            ordemServico.setObservacoes(mEdtObservacoes.getText().toString());
            //ordemServico.setCodServico(String.valueOf(mSpnTipoServico.getSelectedItemPosition()));
            ordemServico.setCodServico(String.valueOf(mSpnTipoServico.getSelectedItem()));
            //ordemServico.setDataLimite("48 horas após a geração");

            repositorioOrdemServico.inserirOS(ordemServico);

        }catch (Exception ex) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("Erro ao gravar os dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    //Função Inserir dados do imovel no banco
    private void inserirImovel () {

        try {
            imovel = new Imovel();

            //Inserção de dados do imovel
            imovel.setCep(mEdtCEP.getText().toString());
            imovel.setCidade(mEdtCidade.getText().toString());
            imovel.setTipoLogradouro(String.valueOf(mSpnTipoServico.getSelectedItemPosition()));
            imovel.setLogradouro(mEdtLogradouro.getText().toString());
            imovel.setNumero(mEdtNumero.getText().toString());
            imovel.setBairro(mEdtBairro.getText().toString());

            if (mEdtCEP == null || mEdtCidade == null || mEdtLogradouro == null || mEdtNumero == null || mEdtBairro == null) {
                Toast.makeText(getApplicationContext(),"Nenhum campo pode estar vazio",Toast.LENGTH_LONG).show();

            } else {

                repositorioImovel.inserirIM(imovel);

                mEdtCEP.getText().clear();
                mEdtCidade.getText().clear();
                mEdtLogradouro.getText().clear();
                mEdtNumero.getText().clear();
                mEdtBairro.getText().clear();
                mEdtObservacoes.getText().clear();
                Toast.makeText(getApplicationContext(),"Serviço solicitado com sucesso",Toast.LENGTH_LONG).show();
            }

        } catch (Exception ex) {

            android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(this);
            dlg.setMessage("Erro ao gravar os dados: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
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

    //Menu superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.solicita_servico, menu);
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
                    "       Versão do aplicativo: 1.0.0 \n\n" +
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

    //Menu Drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override

    //Lista de opções do menu lateral
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Abre a tela de solicitar serviço
       if (id == R.id.menuSolicitaServico) {
            Intent intent = new Intent(SolicitaServico.this, SolicitaServico.class);
            startActivity(intent);
            finish();

           //Abre a tela de consulta de serviços já solicitados
        } else if (id == R.id.menuConsultarServico) {
            Intent intent = new Intent(SolicitaServico.this, ConsultarOrdemServico.class);
            startActivity(intent);
            finish();

           //Abre a tela de consulta de activity_faturas
        } else if (id == R.id.menuConsultaFatura) {
            Intent intent = new Intent(SolicitaServico.this, ConsultarFatura.class);
            startActivity(intent);
            finish();

           //Abre a tela de consulta de imovel que já foram solicitados ordens de serviço
        } else if (id == R.id.menuConsultarImovel) {
            Intent intent = new Intent(SolicitaServico.this, ConsultarImovel.class);
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
           Intent intent = new Intent(SolicitaServico.this, Login.class);
           startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
