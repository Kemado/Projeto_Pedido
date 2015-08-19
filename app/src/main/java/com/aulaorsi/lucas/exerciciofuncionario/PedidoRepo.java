package com.aulaorsi.lucas.exerciciofuncionario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lucas on 5/30/15.
 */
public class PedidoRepo {

    private BDPedido dbHelper;

    public PedidoRepo(Context context) {
        dbHelper = new BDPedido(context);
    }

    private ContentValues preencherDados(Pedido aluno) {
        ContentValues values = new ContentValues();
        values.put(Pedido.COLUNA_MESA,aluno.getMesa());
        values.put(Pedido.COLUNA_GARCOM,aluno.getGarcom());
        values.put(Pedido.COLUNA_STATUS,aluno.getStatus());
        return values;
    }

    public int insert(Pedido pedido) {
        //Abre conexao para escrever dados
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = preencherDados(pedido);
        // Inserindo linha
        long aluno_Id = db.insert(dbHelper.TABELA_PEDIDO, null, values);
        db.close();
        return (int) aluno_Id;
    }

    public void update(Pedido pedido) {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); ContentValues values = preencherDados(pedido);
        db.update(dbHelper.TABELA_PEDIDO, values, Pedido.COLUNA_ID + "= ?", new String[] { String.valueOf(pedido.getId()) });
        db.close();
    }

    public void delete(int pedido_Id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABELA_PEDIDO, Pedido.COLUNA_ID + "= ?", new String[]{String.valueOf(pedido_Id)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getPedidoList() {

        SQLiteDatabase db = dbHelper.getReadableDatabase(); String selectQuery = "SELECT " +
                Pedido.COLUNA_ID + "," + Pedido.COLUNA_MESA + "," + Pedido.COLUNA_GARCOM + "," + Pedido.COLUNA_STATUS +
                " FROM " + dbHelper.TABELA_PEDIDO;
        ArrayList<HashMap<String, String>> pedidoList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> pedido = new HashMap<String, String>();
                pedido.put("id", cursor.getString(cursor.getColumnIndex(Pedido.COLUNA_ID)));
                pedido.put("mesa", cursor.getString(cursor.getColumnIndex(Pedido.COLUNA_MESA)));
                pedido.put("garcom", cursor.getString(cursor.getColumnIndex(Pedido.COLUNA_GARCOM)));
                pedido.put("status", cursor.getString(cursor.getColumnIndex(Pedido.COLUNA_STATUS)));
                pedidoList.add(pedido);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return pedidoList;
    }

    public ArrayList<HashMap<String, String>> findPedidoById(int Id) {

        SQLiteDatabase db = dbHelper.getReadableDatabase(); String selectQuery = "SELECT " +
                Pedido.COLUNA_ID + "," + Pedido.COLUNA_MESA + "," + Pedido.COLUNA_GARCOM + "," + Pedido.COLUNA_STATUS +
                " FROM " + dbHelper.TABELA_PEDIDO + " WHERE " + Pedido.COLUNA_ID + " = " + Id;

        ArrayList<HashMap<String, String>> pedidoList = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> pedido = new HashMap<String, String>();
                pedido.put("id", cursor.getString(cursor.getColumnIndex(Pedido.COLUNA_ID)));
                pedido.put("mesa", cursor.getString(cursor.getColumnIndex(Pedido.COLUNA_MESA)));
                pedido.put("garcom", cursor.getString(cursor.getColumnIndex(Pedido.COLUNA_GARCOM)));
                pedidoList.add(pedido);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return pedidoList;
    }

    public Pedido getPedidoById(int Id){

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " +
                Pedido.COLUNA_ID + "," + Pedido.COLUNA_MESA + "," + Pedido.COLUNA_GARCOM + "," + Pedido.COLUNA_STATUS +
                " FROM " + dbHelper.TABELA_PEDIDO + " WHERE " +
                Pedido.COLUNA_ID + "=?";

        int iCount =0;
        Pedido pedido = new Pedido();
        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                pedido.setId(cursor.getInt(cursor.getColumnIndex(Pedido.COLUNA_ID)));
                pedido.setMesa(cursor.getInt(cursor.getColumnIndex(Pedido.COLUNA_MESA)));
                pedido.setGarcom(cursor.getString(cursor.getColumnIndex(Pedido.COLUNA_GARCOM)));
                pedido.setStatus(cursor.getInt(cursor.getColumnIndex(Pedido.COLUNA_STATUS)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return pedido;
    }
}
