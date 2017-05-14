package br.com.apptools.sanetools.dominio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.apptools.sanetools.database.DataBaseHelper;
import br.com.apptools.sanetools.dominio.entidades.Pessoa;

public class RepositorioPessoa {

    SQLiteDatabase database;
    private DataBaseHelper dataBaseHelper;

    public RepositorioPessoa(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    private SQLiteDatabase getDatabase () {
        if (database == null) {
            database = dataBaseHelper.getWritableDatabase();
        }
        return database;
    }

    private Pessoa criarPessoa (Cursor cursor) {
        Pessoa user = new Pessoa(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Pessoa.COLUNA_IDPESSOA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Pessoa.COLUNA_NOME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Pessoa.COLUNA_CPF)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Pessoa.COLUNA_SENHA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Pessoa.COLUNA_EMAIL))
        );

        return user;
    }

    public List<Pessoa> listarPessoa () {
        Cursor cursor = getDatabase().query(DataBaseHelper.Pessoa.TABELA_PESSOA,
                DataBaseHelper.Pessoa.COLUNAS_PESSOA, null, null, null, null, null);

        List<Pessoa> pessoa = new ArrayList<Pessoa>();
        while (cursor.moveToNext()) {
            Pessoa user = criarPessoa(cursor);
            pessoa.add(user);
        }
        cursor.close();
        return pessoa;
    }

    public long salvarPessoa(Pessoa pessoa) {
        ContentValues values = new ContentValues();

        values.put(DataBaseHelper.Pessoa.COLUNA_CPF, pessoa.getCpf());
        values.put(DataBaseHelper.Pessoa.COLUNA_NOME, pessoa.getNome());
        values.put(DataBaseHelper.Pessoa.COLUNA_SENHA, pessoa.getSenha());
        values.put(DataBaseHelper.Pessoa.COLUNA_EMAIL, pessoa.getEmail());

        if (pessoa.get_idPessoa() != null) {
            return getDatabase().update(DataBaseHelper.Pessoa.TABELA_PESSOA, values,
                    "_idPessoa = ?", new String[]{pessoa.get_idPessoa().toString()});
        }

        return getDatabase().insert(DataBaseHelper.Pessoa.TABELA_PESSOA, null, values);
    }

    public boolean removerPessoa (int id) {
        return getDatabase().delete(DataBaseHelper.Pessoa.TABELA_PESSOA,
                "_idPessoa = ?", new String[]{Integer.toString(id)}) > 0;

    }

    public Pessoa buscaPessoaID (int id) {
        Cursor cursor = getDatabase().query(DataBaseHelper.Pessoa.TABELA_PESSOA,
                DataBaseHelper.Pessoa.COLUNAS_PESSOA, "_idPessoa = ?", new String[] {Integer.toString(id)}, null, null, null);

        if (cursor.moveToNext()) {
            Pessoa user = criarPessoa(cursor);
            cursor.close();
            return user;
        }
        return null;
    }

    public boolean logarPessoa (String cpf, String senha) {
        Cursor cursor = getDatabase().query(DataBaseHelper.Pessoa.TABELA_PESSOA,
                null, "cpf = ? AND senha = ?", new String[] {cpf, senha}, null, null, null);

        if (cursor.moveToFirst()) {
            return  true;
        }
        return false;
    }

    public boolean checkIFExistis(String cpf){
        database = dataBaseHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from " + DataBaseHelper.Pessoa.TABELA_PESSOA + " where " +
                DataBaseHelper.Pessoa.COLUNA_CPF + " = ? ", new String[]{cpf});

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        else{
            return true;
        }
    }

    public void fechar () {
        dataBaseHelper.close();
        database = null;
    }
}
