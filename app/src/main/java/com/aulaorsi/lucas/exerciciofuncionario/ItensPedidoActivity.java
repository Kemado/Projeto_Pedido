package com.aulaorsi.lucas.exerciciofuncionario;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aulaorsi.lucas.exerciciofuncionario.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ItensPedidoActivity extends Activity implements android.view.View.OnClickListener {

    EditText edIdPedido;
    EditText edProduto;

    Button btnSave;
    Button btnClose;
    Button btnDelete;

    private int _Itens_Pedido_Id=0;
    private int _Pedido_Id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_pedido);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        edIdPedido = (EditText) findViewById(R.id.edIdPedido);
        edProduto = (EditText) findViewById(R.id.edProduto);

        _Itens_Pedido_Id = 0;
        //Intent intent = getIntent();
        //_Itens_Pedido_Id =intent.getIntExtra("itens_pedido_Id", 0);
        //ItensPedidoRepo repo = new ItensPedidoRepo(this);
        //ItensPedido itenspedido = new ItensPedido();
        //itenspedido = repo.getItemPedidoById(_Itens_Pedido_Id);





        _Pedido_Id = 0;
        Intent intent = getIntent();
        _Pedido_Id =intent.getIntExtra("_Pedido_Id", 0);

        edIdPedido.setText(String.valueOf(_Pedido_Id));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_itens_pedido, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if (view == findViewById(R.id.btnSave)){
            ItensPedidoRepo repo = new ItensPedidoRepo(this);
            ItensPedido itenspedido = new ItensPedido();
            itenspedido.setIdPedido(Integer.parseInt(edIdPedido.getText().toString()));
            itenspedido.setProduto(edProduto.getText().toString());
            itenspedido.setId(_Itens_Pedido_Id);

            if (_Itens_Pedido_Id==0){
                _Itens_Pedido_Id = repo.insert(itenspedido);

                Toast.makeText(this, "Novo pedido inserido", Toast.LENGTH_SHORT).show();
            }//else{

                //repo.update(pedido);
                //Toast.makeText(this,"Pedido atualizado",Toast.LENGTH_SHORT).show();
            //}
        }else if (view== findViewById(R.id.btnDelete)){
            ItensPedidoRepo repo = new ItensPedidoRepo(this);
            repo.delete(_Itens_Pedido_Id);
            Toast.makeText(this, "Pedido Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }

    }
}
