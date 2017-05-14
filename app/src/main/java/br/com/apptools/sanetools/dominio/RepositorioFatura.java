package br.com.apptools.sanetools.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import br.com.apptools.sanetools.database.DataBaseHelper;
import br.com.apptools.sanetools.dominio.entidades.Fatura;

public class RepositorioFatura {

    SQLiteDatabase database;

    public RepositorioFatura (SQLiteDatabase database) {
        this.database = database;
    }

    //metodo de inserir dados no banco
    public void InserirFatura(Fatura fatura){

        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.Fatura.COLUNA_MESREFERENCIA, fatura.getMesReferencia());
        values.put(DataBaseHelper.Fatura.COLUNA_CONSUMO, fatura.getConsumo());
        values.put(DataBaseHelper.Fatura.COLUNA_VALOR, fatura.getValor());
        values.put(DataBaseHelper.Fatura.COLUNA_STATUS, fatura.getStatus());

            database.insertOrThrow(DataBaseHelper.Fatura.TABELA_FATURA, null, values);
    }

    public ArrayAdapter<Fatura> buscaFT(Context context) {

        ArrayAdapter<Fatura> adpFT = new ArrayAdapter<Fatura>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = database.query(DataBaseHelper.Fatura.TABELA_FATURA, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            do {

                Fatura fatura = new Fatura();

                fatura.setMesReferencia(cursor.getString(1));
                fatura.setConsumo(cursor.getInt(2));
                fatura.setValor(cursor.getDouble(3));
                fatura.setStatus(cursor.getString(4));

                adpFT.add(fatura);

            } while (cursor.moveToNext());
        }

        return adpFT;
    }
}
