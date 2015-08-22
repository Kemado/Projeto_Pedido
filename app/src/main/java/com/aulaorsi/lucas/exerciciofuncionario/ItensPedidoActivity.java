package com.aulaorsi.lucas.exerciciofuncionario;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.aulaorsi.lucas.exerciciofuncionario.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ItensPedidoActivity extends Activity implements android.view.View.OnClickListener {

    EditText edIdPedido;
    EditText edProduto;

    TextView itenspedido_Id;

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

            //if (_Itens_Pedido_Id==0){
            _Itens_Pedido_Id = repo.insert(itenspedido);
            edProduto.setText("");
            Toast.makeText(this, "Novo Item inserido", Toast.LENGTH_SHORT).show();

            /*ItensPedidoRepo repoit = new ItensPedidoRepo(this);

            ArrayList<HashMap<String, String>> itenspedidoList = repoit.findItemPedidoById(Integer.parseInt(edIdPedido.getText().toString()));
            if (itenspedidoList.size() != 0) {
                //ListView lv = getListView();
                ListView lv = (ListView) findViewById(R.id.ListItens);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        itenspedido_Id = (TextView) view.findViewById(R.id.itenspedido_Id);
                        String itenspedidoId = itenspedido_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), PedidoDetalhe.class);
                        objIndent.putExtra("itenspedido_Id", Integer.parseInt(itenspedidoId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(ItensPedidoActivity.this, itenspedidoList, R.layout.view_itempedido_entry, new String[]{"id", "produto"}, new int[]{R.id.itenspedido_Id, R.id.itenspedido_produto});
                lv.setAdapter(adapter);
                //setListAdapter(adapter);*/


                ItensPedidoRepo repoit = new ItensPedidoRepo(this);

                ArrayList<HashMap<String, String>> itenspedidoList = repoit.getItemPedidoList(Integer.parseInt(edIdPedido.getText().toString()));
                if (itenspedidoList.size() != 0) {
                    //ListView lv = getListView(); para usar esse método voce tem que herdar de ListActivity
                    ListView lv = (ListView) findViewById(R.id.ListItens);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            itenspedido_Id = (TextView) view.findViewById(R.id.itenspedido_Id);
                            String itenspedidoId = itenspedido_Id.getText().toString();
                            Intent objIndent = new Intent(getApplicationContext(), PedidoDetalhe.class);
                            objIndent.putExtra("itenspedido_Id", Integer.parseInt(itenspedidoId));
                            startActivity(objIndent);
                        }
                    });
                    ListAdapter adapter = new SimpleAdapter(ItensPedidoActivity.this, itenspedidoList, R.layout.view_itempedido_entry, new String[]{"id", "produto"}, new int[]{R.id.itenspedido_Id, R.id.itenspedido_produto});
                    lv.setAdapter(adapter);
                    //setListAdapter(adapter); para usar esse método voce tem que herdar de ListActivity


            } else {
                Toast.makeText(this, "Nenhum item encontrado!", Toast.LENGTH_SHORT).show();
            }



            //}else{

            //    repo.update(itenspedido);
            //    Toast.makeText(this,"Pedido atualizado",Toast.LENGTH_SHORT).show();
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
