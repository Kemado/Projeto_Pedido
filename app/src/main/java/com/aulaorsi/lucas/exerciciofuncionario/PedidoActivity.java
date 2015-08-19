package com.aulaorsi.lucas.exerciciofuncionario;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class PedidoActivity extends ListActivity implements android.view.View.OnClickListener {

    Button btnAdd,btnGetAll, btnSearch;
    TextView pedido_Id;
    EditText codigoBusca;
    LinearLayout entrytexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        codigoBusca = (EditText) findViewById(R.id.codigoBusca);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trabalho, menu);
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

        if (view == findViewById(R.id.btnAdd)) {

            Intent intent = new Intent(this, PedidoDetalhe.class);
            intent.putExtra("pedido_Id", 0);
            startActivity(intent);

        } else if (view == findViewById(R.id.btnSearch)) {

            PedidoRepo repo = new PedidoRepo(this);

            ArrayList<HashMap<String, String>> pedidoList = repo.findPedidoById(Integer.parseInt(codigoBusca.getText().toString()));
            if (pedidoList.size() != 0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pedido_Id = (TextView) view.findViewById(R.id.pedido_Id);
                        String pedidoId = pedido_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), PedidoDetalhe.class);
                        objIndent.putExtra("pedido_Id", Integer.parseInt(pedidoId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(PedidoActivity.this, pedidoList, R.layout.view_pedido_entry, new String[]{"id", "nome"}, new int[]{R.id.pedido_Id, R.id.pedido_garcom});
                setListAdapter(adapter);
            } else {
                Toast.makeText(this, "Nenhum pedido encontrado!", Toast.LENGTH_SHORT).show();
            }


        } else {

            PedidoRepo repo = new PedidoRepo(this);

            ArrayList<HashMap<String, String>> pedidoList = repo.getPedidoList();
            if (pedidoList.size() != 0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        pedido_Id = (TextView) view.findViewById(R.id.pedido_Id);
                        String pedidoId = pedido_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), PedidoDetalhe.class);
                        objIndent.putExtra("pedido_Id", Integer.parseInt(pedidoId));
                        startActivity(objIndent);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(PedidoActivity.this, pedidoList, R.layout.view_pedido_entry, new String[]{"id", "garcom", "mesa", "status"}, new int[]{R.id.pedido_Id, R.id.pedido_garcom, R.id.pedido_mesa, R.id.pedido_status});


                //entrytexts = findViewById(R.id.entrytexts);

                //if (Pedido.COLUNA_STATUS.equals("1")){
                    //entrytexts =  R.layout.view_pedido_entry
                    ////entrytexts.setBackgroundColor(getResources().getColor(R.color.green));
                    //R.layout.view_pedido_entry(entrytexts.setBackgroundColor(getResources().getColor(R.id.green)));
                //}


                setListAdapter(adapter);
            } else {
                Toast.makeText(this, "Sem Pedidos!", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
