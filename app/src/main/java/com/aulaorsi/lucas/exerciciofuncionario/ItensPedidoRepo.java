package com.aulaorsi.lucas.exerciciofuncionario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lucas on 17/08/15.
 */
public class ItensPedidoRepo {

    private BDPedido dbHelper;

    public ItensPedidoRepo(Context context) {
        dbHelper = new BDPedido(context);
    }

    private ContentValues preencherDados(ItensPedido aluno) {
        ContentValues values = new ContentValues();
        values.put(ItensPedido.COLUNA_IDPEDIDO,aluno.getIdPedido());
        values.put(ItensPedido.COLUNA_PRODUTO,aluno.getProduto());
        return values;
    }

    public int insert(ItensPedido itenspedido) {
        //Abre conexao para escrever dados
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = preencherDados(itenspedido);
        // Inserindo linha
        long aluno_Id = db.insert(dbHelper.TABELA_ITENSPEDIDO, null, values);
        db.close();
        return (int) aluno_Id;
    }

    public void update(ItensPedido itenspedido) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); ContentValues values = preencherDados(itenspedido);
        db.update(dbHelper.TABELA_ITENSPEDIDO, values, Pedido.COLUNA_ID + "= ?", new String[] { String.valueOf(itenspedido.getId()) });
        db.close();
    }

    public void delete(int itens_pedido_Id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABELA_ITENSPEDIDO, ItensPedido.COLUNA_ID + "= ?", new String[] { String.valueOf(itens_pedido_Id) });
        db.close();
    }

    public ArrayList<HashMap<String, String>> getItemPedidoList() {

        SQLiteDatabase db = dbHelper.getReadableDatabase(); String selectQuery = "SELECT " +
                Pedido.COLUNA_ID + "," + ItensPedido.COLUNA_IDPEDIDO + "," + ItensPedido.COLUNA_PRODUTO +
                " FROM " + dbHelper.TABELA_ITENSPEDIDO;
        ArrayList<HashMap<String, String>> itenspedidoList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> itenspedido = new HashMap<String, String>();
                itenspedido.put("id", cursor.getString(cursor.getColumnIndex(ItensPedido.COLUNA_ID)));
                itenspedido.put("idpedido", cursor.getString(cursor.getColumnIndex(ItensPedido.COLUNA_IDPEDIDO)));
                itenspedido.put("produto", cursor.getString(cursor.getColumnIndex(ItensPedido.COLUNA_PRODUTO))); //teste
                itenspedidoList.add(itenspedido);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return itenspedidoList;
    }

    public ArrayList<HashMap<String, String>> findItemPedidoById(int Id) {

        SQLiteDatabase db = dbHelper.getReadableDatabase(); String selectQuery = "SELECT " +
                Pedido.COLUNA_ID + "," + ItensPedido.COLUNA_IDPEDIDO + "," + ItensPedido.COLUNA_PRODUTO +
                " FROM " + dbHelper.TABELA_ITENSPEDIDO + " WHERE " + Pedido.COLUNA_ID + " = " + Id;

        ArrayList<HashMap<String, String>> itenspedidoList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> itenspedido = new HashMap<String, String>();
                itenspedido.put("id", cursor.getString(cursor.getColumnIndex(ItensPedido.COLUNA_ID)));
                itenspedido.put("idpedido", cursor.getString(cursor.getColumnIndex(ItensPedido.COLUNA_IDPEDIDO)));
                itenspedido.put("produto", cursor.getString(cursor.getColumnIndex(ItensPedido.COLUNA_PRODUTO)));
                itenspedidoList.add(itenspedido);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return itenspedidoList;
    }

    public ItensPedido getItemPedidoById(int Id){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                ItensPedido.COLUNA_ID + "," + ItensPedido.COLUNA_IDPEDIDO + "," + ItensPedido.COLUNA_PRODUTO +
                " FROM " + dbHelper.TABELA_ITENSPEDIDO + " WHERE " +
                Pedido.COLUNA_ID + "=?";

        int iCount =0;
        ItensPedido itenspedido = new ItensPedido();
        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                itenspedido.setId(cursor.getInt(cursor.getColumnIndex(Pedido.COLUNA_ID)));
                itenspedido.setIdPedido(cursor.getInt(cursor.getColumnIndex(Pedido.COLUNA_MESA)));
                itenspedido.setProduto(cursor.getString(cursor.getColumnIndex(Pedido.COLUNA_GARCOM)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return itenspedido;
    }

}
