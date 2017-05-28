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

import br.com.apptools.sanetools.database.DataBaseHelper;
import br.com.apptools.sanetools.dominio.RepositorioFatura;
import br.com.apptools.sanetools.dominio.entidades.Fatura;

public class ConsultaFatura extends AppCompatActivity {

    //Declaração de variaveis para recuperação de dados
    Button mBtnVoltarFaturas;
    ListView mLstvFaturas;
    ArrayAdapter<Fatura> adpFT;

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase database;
    private RepositorioFatura repositorioFatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faturas);

        mBtnVoltarFaturas = (Button) findViewById(R.id.btnVoltarFaturas);
        mLstvFaturas = (ListView) findViewById(R.id.lstvFaturas);

        //Função de fechar dela de consulta de activity_faturas e voltar a tela principal
        mBtnVoltarFaturas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaFatura.this,HomeCliente.class);
                startActivity(intent);
                finish();
            }
        });

        try {
            dataBaseHelper = new DataBaseHelper(this);
            database = dataBaseHelper.getWritableDatabase();

            repositorioFatura = new RepositorioFatura(database);
            adpFT = repositorioFatura.buscaFT(this);
            mLstvFaturas.setAdapter(adpFT);

        }catch (SQLException ex) { //Código para retono de erro do banco de dados

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao criar o banco: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
}
