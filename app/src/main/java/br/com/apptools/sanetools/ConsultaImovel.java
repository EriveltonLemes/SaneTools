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

import br.com.apptools.sanetools.dominio.RepositorioImovel;
import br.com.apptools.sanetools.database.DataBaseHelper;
import br.com.apptools.sanetools.dominio.entidades.Imovel;

public class ConsultaImovel extends AppCompatActivity {

    //Declaração de variáveis para retorno de dados
    Button mBtnVoltarImoveis;
    ListView mLstvImoveis;
    ArrayAdapter<Imovel> adpIM;

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase database;
    private RepositorioImovel repositorioImovel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imoveis);

        mBtnVoltarImoveis = (Button) findViewById(R.id.btnVoltarImoveis);
        mLstvImoveis = (ListView) findViewById(R.id.lstvImoveis);

        //Função do botão de voltar. Fecha a tela de consulta de imovel e retorna a tela principal
        mBtnVoltarImoveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaImovel.this,HomeCliente.class);
                startActivity(intent);
                finish();
            }
        });

        //Método para carregar e listar os imovel cadastrados no banco
        try {
            dataBaseHelper = new DataBaseHelper(this);
            database = dataBaseHelper.getWritableDatabase();

            repositorioImovel = new RepositorioImovel(database);
            adpIM = repositorioImovel.buscaIM(this);
            mLstvImoveis.setAdapter(adpIM);

        }catch (SQLException ex) { //Código para retorno de errodo banco de dados

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro ao persistir o banco: " + ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        adpIM = repositorioImovel.buscaIM(this);
        mLstvImoveis.setAdapter(adpIM);
    }
}
