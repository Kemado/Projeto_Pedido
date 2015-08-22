package com.aulaorsi.lucas.exerciciofuncionario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lucas on 5/30/15.
 */
public class BDPedido extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "bancoexemplo";
    private static final int VERSAO_BANCO = 10;
    public static final String TABELA_PEDIDO = "pedido";
    public static final String TABELA_CREATE =
            "create table " + TABELA_PEDIDO + " ( " + " _id INTEGER primary key autoincrement, " +
                    " mesa INTEGER , " +
                    " garcom varchar(50) , " +
                    " status INTEGER );";

    public static final String TABELA_GARCOM = "tabelagarcom";
    public static final String TABELA_CREATE_GARCOM =
            "create table " + TABELA_GARCOM + " ( " + " _id INTEGER primary key autoincrement, " +
                    " nome varchar(50) );";

    public static final String TABELA_ITENSPEDIDO = "itenspedido";
    public static final String TABELA_CREATE_ITENSPEDIDO =
            "create table " + TABELA_ITENSPEDIDO + " ( " + " _id INTEGER primary key autoincrement, " +
                    " idpedido INTEGER, " +
                    " produto varchar(50) );";

    public BDPedido(Context context) {

        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABELA_CREATE);
        db.execSQL(TABELA_CREATE_GARCOM);
        db.execSQL(TABELA_CREATE_ITENSPEDIDO);
        db.execSQL("insert into tabelagarcom (nome) VALUES ('Lucas') " );
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(BDPedido.class.getName(),
                "Atualizando banco de dados a partir da versao " + oldVersion + " para "
                        + newVersion + ",que ira destruir os dados antigos");

        db.execSQL("DROP TABLE IF EXISTS " + TABELA_PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_GARCOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_ITENSPEDIDO);
        onCreate(db);
    }
}
