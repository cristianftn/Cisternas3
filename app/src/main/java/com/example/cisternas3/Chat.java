package com.example.cisternas3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {
    ListView listaUsuarios;
    String guardarId;
    String nombreVentana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listaUsuarios = (ListView) findViewById(R.id.listaUsuarios);
        nombreVentana = getIntent().getExtras().getString("nombreUser");
        guardarId = getIntent().getExtras().getString("idNombreVentana");
        //Toast.makeText(getApplicationContext(),"id:  " + guardarId, Toast.LENGTH_SHORT).show();
        final ArrayList<String> usuarioslista = new ArrayList<>();

        Response.Listener<String> responseListener = new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                String species;
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray values = jsonResponse.getJSONArray("nombres");

                    for (int i = 0; i < values.length(); i++) {
                        JSONObject animal = values.getJSONObject(i);

                        //int id = animal.getInt("id");
                        species = animal.getString("nombre");
                        Log.e("BIENBIEN", "" + ":"+ " animales: " + animal);
                        usuarioslista.add(species);
                        //String name = animal.getString("name");
                        //println(id + ", " + species + ", " + name);
                    }
                    // String matricula = jsonResponse.getString("");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Chat.this, android.R.layout.simple_spinner_dropdown_item, usuarioslista);
                    listaUsuarios.setAdapter(adapter);
                   // Log.e("BIEN", "" + "response:"+ " aux: " + listaCisternas);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        };

        ListaUsuariosChatRequest listaUsuariosChatRequest = new ListaUsuariosChatRequest(nombreVentana,responseListener);
        RequestQueue queue = Volley.newRequestQueue(Chat.this);
        queue.add(listaUsuariosChatRequest);

       /* listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String guardarNum = Usuarioslista.get(position);
                Toast.makeText(getApplicationContext(),"guardarNum:  " + guardarNum, Toast.LENGTH_SHORT).show();

            }
        });*/

        listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String guardarNum = usuarioslista.get(position);
               // Toast.makeText(getApplicationContext(),"guardarNum: " + guardarNum, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent( Chat.this, ChatMensaje.class );
                intent.putExtra("usuOrigen", nombreVentana);
                intent.putExtra("usuDestino", guardarNum);
                startActivity(intent);

            }
        });


    }
}
