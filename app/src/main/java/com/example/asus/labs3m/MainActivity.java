package com.example.asus.labs3m;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.labs3m.ItemMenu.ItemMenuStructure;
import com.example.asus.labs3m.ItemMenu.MenuBaseAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView LISTA;
private ArrayList<ItemMenuStructure> LISINF;
private MenuBaseAdapter ADAPTER;
private Context root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        root = this;
        LISINF = new ArrayList<ItemMenuStructure>();
        ADAPTER = new MenuBaseAdapter(root,LISINF);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       // loadData();
        cargarComponentes();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void loadData(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://181.114.179.122:7070/api/v1.0/food",new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                int a=1;
                try {
                    JSONArray lsinf= (JSONArray) response.get("info");
                    LISINF.clear();
                    for (int i = 0; i<lsinf.length();i++){
                        JSONObject itemjs= lsinf.getJSONObject(i);
                        String name = itemjs.getString("name");
                        String cantidad = itemjs.getString("quantity");
                        String img_cm = itemjs.getString("avatar");
                        String id = itemjs.getString("_id");

                        int cant = Integer.parseInt(cantidad);

                        ItemMenuStructure item = new ItemMenuStructure(name, img_cm,cant,id);
                        LISINF.add(item);



                    }
                    ADAPTER = new MenuBaseAdapter(root, LISINF);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response){

            }
            @Override
            public void onFailure(int statusCode, Header[] headers,Throwable throwable, JSONArray errorResponse){
                Toast.makeText(root, "FAIL", Toast.LENGTH_LONG).show();

            }
        });
    }
    private void cargarComponentes() {
        LISTA =(ListView)this.findViewById(R.id.foodlist);
       // LISTA.setAdapter(ADAPTER);
        /*EditText buscar = (EditText)this.findViewById(R.id.editText);
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String txt = s.toString();
                loadData(txt);
            }


        });*/
       // LISINF.add(new ItemMenuStructure("Anchoas del Cantábrico","http://181.114.179.122:7070/api/v1.0/foodimg/5b115e82c65ae20473506327",100,"5b115e82c65ae20473506327"));
        LISTA.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //String id = this.LISINF.get(position).getId();
        //Intent detallcomida = new Intent(this,)
    }
}
