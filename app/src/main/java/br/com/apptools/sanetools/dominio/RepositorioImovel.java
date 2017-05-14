package br.com.apptools.sanetools.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import br.com.apptools.sanetools.database.DataBaseHelper;
import br.com.apptools.sanetools.dominio.entidades.Imovel;

public class RepositorioImovel {

    SQLiteDatabase database;

    public RepositorioImovel(SQLiteDatabase database) {
        this.database = database;
    }

    //metodo de inserir dados no banco
    public void inserirIM (Imovel imovel){

            ContentValues values = new ContentValues();

            //values.put(DataBaseHelper.Imovel.COLUNA_TIPOLOGRADOURO, imovel.getTipoLogradouro());
            values.put(DataBaseHelper.Imovel.COLUNA_LOGRADOURO, imovel.getLogradouro());
            values.put(DataBaseHelper.Imovel.COLUNA_NUMERO, imovel.getNumero());
            values.put(DataBaseHelper.Imovel.COLUNA_BAIRRO, imovel.getBairro());
            values.put(DataBaseHelper.Imovel.COLUNA_CIDADE, imovel.getCidade());
            values.put(DataBaseHelper.Imovel.COLUNA_CEP, imovel.getCep());

            database.insertOrThrow(DataBaseHelper.Imovel.TABELA_IMOVEL, null, values);
        }

    public ArrayAdapter <Imovel> buscaIM(Context context) {

        ArrayAdapter<Imovel> adpIM = new ArrayAdapter <Imovel>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = database.query(DataBaseHelper.Imovel.TABELA_IMOVEL, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {

            cursor.moveToFirst();

            do {

                Imovel imovel = new Imovel();

                //imovel.getTipoLogradouro();
                imovel.setLogradouro(cursor.getString(2));
                imovel.setNumero(cursor.getString(3));
                imovel.setBairro(cursor.getString(4));
                imovel.setCidade(cursor.getString(5));
                imovel.setCep(cursor.getString(6));

                adpIM.add(imovel);

            } while (cursor.moveToNext());
        }

        return adpIM;
    }
}
