package br.com.apptools.sanetools.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import br.com.apptools.sanetools.database.DataBaseHelper;
import br.com.apptools.sanetools.dominio.entidades.OrdemServico;

public class RepositorioOrdemServico {

    SQLiteDatabase database;

    public RepositorioOrdemServico(SQLiteDatabase database) {
        this.database = database;
    }

    public void inserirOS (OrdemServico ordemServico) {

        ContentValues values = new ContentValues();

        //values.put(DataBaseHelper.OrdemServico.COLUNA_DATAGERACAO, ordemServico.getDataGeracao());
        values.put(DataBaseHelper.OrdemServico.COLUNA_CODSERVICO,ordemServico.getCodServico());
        values.put(DataBaseHelper.OrdemServico.COLUNA_DATALIMITE,ordemServico.getDataLimite());
        values.put(DataBaseHelper.OrdemServico.COLUNA_OBSERVACOES, ordemServico.getObservacoes());

        database.insertOrThrow(DataBaseHelper.OrdemServico.TABELA_ORDEMSERVICO, null, values);
    }

    public ArrayAdapter <OrdemServico> buscaOS(Context context) {

        ArrayAdapter<OrdemServico> adpOS = new ArrayAdapter<OrdemServico>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = database.query(DataBaseHelper.OrdemServico.TABELA_ORDEMSERVICO, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            do {

                OrdemServico ordemServico = new OrdemServico();

                //ordemServico.setDataGeracao(cursor.getString(1));
                ordemServico.setCodServico(cursor.getString(2));
                ordemServico.setDataLimite(cursor.getString(3));
                ordemServico.setObservacoes(cursor.getString(4));

                adpOS.add(ordemServico);

            } while (cursor.moveToNext());
        }

        return adpOS;
    }
}