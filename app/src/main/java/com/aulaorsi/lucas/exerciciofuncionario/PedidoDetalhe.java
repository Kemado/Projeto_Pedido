package com.aulaorsi.lucas.exerciciofuncionario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lucas on 5/30/15.
 */

public class PedidoDetalhe extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose, btnItensPedido;
    EditText editTextMesa;
    EditText editTextGarcom;
    EditText editTextStatus;
    EditText editTextId;
    private int _Pedido_Id=0;
    private int _Itens_Pedido_Id=0;
    private int _idGarcom=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_detalhe);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnItensPedido = (Button) findViewById(R.id.btnItensPedido);

        editTextMesa = (EditText) findViewById(R.id.editTextMesa);
        editTextGarcom = (EditText) findViewById(R.id.editTextGarcom);
        editTextStatus = (EditText) findViewById(R.id.editTextStatus);
        editTextId = (EditText) findViewById(R.id.editTextId);


        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnItensPedido.setOnClickListener(this);

        _Pedido_Id = 0;
        Intent intent = getIntent();
        _Pedido_Id =intent.getIntExtra("pedido_Id", 0);
        PedidoRepo repo = new PedidoRepo(this);
        Pedido pedido = new Pedido();
        pedido = repo.getPedidoById(_Pedido_Id);

        editTextMesa.setText(String.valueOf(pedido.getMesa()));
        //editTextGarcom.setText(pedido.getGarcom());
        editTextStatus.setText(String.valueOf(pedido.getStatus()));

        Garcom garcom = new Garcom();
        garcom = repo.getGarcomById(_idGarcom);
        editTextGarcom.setText(garcom.getNome());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.funcionario_detail, menu);
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
            PedidoRepo repo = new PedidoRepo(this);
            Pedido pedido = new Pedido();
            pedido.setMesa(Integer.parseInt(editTextMesa.getText().toString()));
            pedido.setGarcom(editTextGarcom.getText().toString());
            pedido.setStatus(Integer.parseInt(editTextStatus.getText().toString()));
            //pedido.setId(Integer.parseInt(editTextId.getText().toString()));
            pedido.setId(_Pedido_Id);

            if (_Pedido_Id==0){
                _Pedido_Id = repo.insert(pedido);

                Toast.makeText(this, "Novo pedido inserido", Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(this, ItensPedidoActivity.class);
                intent.putExtra("_Pedido_Id", _Pedido_Id);
                startActivity(intent);




            }else{

                repo.update(pedido);
                Toast.makeText(this,"Pedido atualizado",Toast.LENGTH_SHORT).show();
            }



        }else if (view== findViewById(R.id.btnDelete)){
            PedidoRepo repo = new PedidoRepo(this);
            repo.delete(_Pedido_Id);
            Toast.makeText(this, "Pedido Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnItensPedido)){
            Intent intent = new Intent(this, ItensPedidoActivity.class);
            intent.putExtra("pedido_Id", 0);
            startActivity(intent);
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }

    }
}
