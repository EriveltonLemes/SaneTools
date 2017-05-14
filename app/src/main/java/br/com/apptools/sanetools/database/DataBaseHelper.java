package br.com.apptools.sanetools.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "sanetoolsBD";
    private static final int VERSAO_BANCO = 1;

    public DataBaseHelper(Context context) {
        super(context,NOME_BANCO,null,VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Tabela Pessoa
        db.execSQL("CREATE TABLE Pessoa(_idPessoa INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT , cpf TEXT, senha TEXT, email TEXT);");

        //Tabela Ordem de serviço
        db.execSQL("CREATE TABLE OrdemServico(_idOS INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dataGeracao CURRENT_DATE , codServico INTEGER," +
                "dataLimite DATE, observacoes TEXT);");

        //Tabela Imovel
        db.execSQL("CREATE TABLE Imovel(_idIM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tipoLogradouro TEXT, logradouro TEXT, numero TEXT," +
                "bairro TEXT, cidade TEXT, CEP TEXT);");

        //Tabela Faturas
        db.execSQL("CREATE TABLE Fatura(_idFT INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "mesReferencia TEXT, consumo INTEGER, valor REAL (6,2), status TEXT);");

        //Inserção de activity_faturas
        db.execSQL("INSERT INTO Fatura (mesReferencia, consumo, valor, status) VALUES ('Jul/2016', 12 , 78.55 , 'Pago')");
        db.execSQL("INSERT INTO Fatura (mesReferencia, consumo, valor, status) VALUES ('Ago/2016', 15 , 99.47 , 'Pago')");
        db.execSQL("INSERT INTO Fatura (mesReferencia, consumo, valor, status) VALUES ('Set/2016', 11 , 72.80 , 'Em aberto')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Tabela Pessoa
    public static class Pessoa{

        public static final String TABELA_PESSOA = "Pessoa";
        public static final String COLUNA_IDPESSOA = "_idPessoa";
        public static final String COLUNA_NOME = "nome";
        public static final String COLUNA_CPF = "cpf";
        public static final String COLUNA_SENHA = "senha";
        public static final String COLUNA_EMAIL = "email";

        public static final String[] COLUNAS_PESSOA = new String[]{
                COLUNA_IDPESSOA, COLUNA_NOME, COLUNA_CPF, COLUNA_SENHA, COLUNA_EMAIL
        };
    }

    //Tabela Ordem de serviço
    public static class OrdemServico {

        public static final String TABELA_ORDEMSERVICO = "Ordemservico";
        public static final String COLUNA_IDORDEMSERVICO = "_idOS";
        public static final String COLUNA_DATAGERACAO = "dataGeracao";
        public static final String COLUNA_CODSERVICO = "codServico";
        public static final String COLUNA_DATALIMITE = "dataLimite";
        public static final String COLUNA_OBSERVACOES = "observacoes";

        public static final String[] COLUNAS_ORDEMSERVICO = new String[] {
                COLUNA_IDORDEMSERVICO, COLUNA_DATAGERACAO, COLUNA_CODSERVICO,
                COLUNA_DATALIMITE, COLUNA_OBSERVACOES
        };
    }

    //Tabela Fatura
    public static class Fatura {

        public static final String TABELA_FATURA = "Fatura";
        public static final String COLUNA_IDFATURA = "_idFT";
        public static final String COLUNA_MESREFERENCIA = "mesReferencia";
        public static final String COLUNA_CONSUMO = "consumo";
        public static final String COLUNA_VALOR = "valor";
        public static final String COLUNA_STATUS = "status";

        public static final String[] COLUNAS_FATURA = new String[] {
                COLUNA_IDFATURA, COLUNA_MESREFERENCIA, COLUNA_CONSUMO, COLUNA_VALOR, COLUNA_STATUS
        };
    }

    //Tabela Imovel
    public static class Imovel {

        public static final String TABELA_IMOVEL = "Imovel";
        public static final String COLUNA_IDIMOVEL = "_idIM";
        public static final String COLUNA_TIPOLOGRADOURO = "tipoLogradouro";
        public static final String COLUNA_LOGRADOURO = "logradouro";
        public static final String COLUNA_NUMERO = "numero";
        public static final String COLUNA_BAIRRO = "bairro";
        public static final String COLUNA_CIDADE = "cidade";
        public static final String COLUNA_CEP = "cep";

        public static final String[] COLUNAS_IMOVEL = new String[] {
                COLUNA_IDIMOVEL, COLUNA_TIPOLOGRADOURO, COLUNA_LOGRADOURO, COLUNA_NUMERO,
                COLUNA_BAIRRO, COLUNA_CIDADE, COLUNA_CEP
        };
    }
}
