package br.com.apptools.sanetools;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import br.com.apptools.sanetools.dominio.RepositorioOrdemServico;
import br.com.apptools.sanetools.database.DataBaseHelper;
import br.com.apptools.sanetools.dominio.entidades.OrdemServico;

public class ConsultaOsCliente extends AppCompatActivity {

    //Declaração de variaveis para retorno de dados
    Button mBtnVoltarAcompOS;
    ListView mLstvOS;
    ArrayAdapter<OrdemServico> adpOS;

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase database;
    RepositorioOrdemServico repositorioOrdemServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_os);

        mBtnVoltarAcompOS = (Button) findViewById(R.id.btnVoltarAcompOS);
        mLstvOS = (ListView) findViewById(R.id.lstvOS);

        //Função que fecha a tela de consulta de ordens de serviço e retorna para a tela principal
        mBtnVoltarAcompOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaOsCliente.this,HomeCliente.class);
                startActivity(intent);
                finish();
            }
        });

        //Método que carrega e lista as ordens de serviço armazenadas no banco de dados
        try {
            dataBaseHelper = new DataBaseHelper(this);
            database = dataBaseHelper.getWritableDatabase();

            repositorioOrdemServico = new RepositorioOrdemServico(database);
            adpOS = repositorioOrdemServico.buscaOS(this);
            mLstvOS.setAdapter(adpOS);

            //Método de retorna erro de consulta no banco de dados, caso haja.
        }catch (SQLException ex) {

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao persistir o banco: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
}
