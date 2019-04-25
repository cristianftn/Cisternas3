package com.example.cisternas3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import android.os.Handler;

public class ChatMensaje extends AppCompatActivity {
String usuarioOrigen, usuarioDestino, fecha;
EditText texto;
ListView verMensaje;
int tamanoAnterior = 0;
boolean semaforo = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_mensaje);
        texto = (EditText)findViewById(R.id.texto);
        usuarioOrigen = getIntent().getExtras().getString("usuOrigen");
        usuarioDestino = getIntent().getExtras().getString("usuDestino");
        verMensaje = (ListView) findViewById(R.id.verMensaje);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                obtenerMensaje();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy  HH:mm ", Locale.getDefault());
                Date date = new Date();



                fecha = dateFormat.format(date);
                handler.postDelayed(this, 1000);
            }
        }, 1);

        //Toast.makeText(getApplicationContext(),fecha, Toast.LENGTH_SHORT).show();
    }

    public void Texto(View view){

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(texto, InputMethodManager.SHOW_IMPLICIT);
    }

    public void InsertarMensaje(View view){
        String guardarMensaje = texto.getText().toString();
        texto.setText("");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        };

        insertarMensajeRequest insertarMensajeRequest=new insertarMensajeRequest(usuarioOrigen,usuarioDestino,guardarMensaje, fecha,responseListener);
        RequestQueue queue = Volley.newRequestQueue(ChatMensaje.this);
        queue.add(insertarMensajeRequest);
    }

    public void obtenerMensaje() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            String guardarIdMensaje, guardarUsuOrigen, guardarUsuDestino, guardarMensaje, guardarFechaHora;
            String guardarFilaCompleta;
            final ArrayList<String> guardarMensajes = new ArrayList<>();
            final ArrayList<MensajesPlantilla> listaMensajes = new ArrayList<MensajesPlantilla>();


            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray values = jsonResponse.getJSONArray("mensajes");

                    for (int i = 0; i < values.length(); i++) {
                        JSONObject animal = values.getJSONObject(i);

                        //int id = animal.getInt("id");
                        guardarIdMensaje = animal.getString("idMensaje");
                        guardarUsuOrigen = animal.getString("usuarioOrigen");
                        guardarUsuDestino = animal.getString("usuarioDestino");
                        guardarMensaje = animal.getString("mensaje");
                        guardarFechaHora = animal.getString("FechaHora");
                        //guardarFilaCompleta = guardarUsuOrigen + "\n" + guardarMensaje + "\n" + fecha;
                        guardarMensajes.add(guardarFilaCompleta);
                       // Toast.makeText(getApplicationContext(), guardarFilaCompleta, Toast.LENGTH_SHORT).show();
                       // Log.e("BIENBIEN", "" + ":"+ " animales: " + animal);
                       // usuarioslista.add(species);
                        //String name = animal.getString("name");
                        //println(id + ", " + species + ", " + name);

                        listaMensajes.add(new MensajesPlantilla(usuarioOrigen,guardarMensaje,guardarFechaHora));
                    }
                    if (tamanoAnterior == guardarMensajes.size()){
                        semaforo=false;
                    }else{
                        semaforo=true;
                    }
                    if (semaforo==true){
                       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChatMensaje.this, android.R.layout.simple_spinner_dropdown_item, listaMensajes);
                        AdaptadorMensaje miAdaptador = new AdaptadorMensaje(getApplicationContext(),listaMensajes);
                        verMensaje.setAdapter(miAdaptador);
                    }
                    tamanoAnterior  =  guardarMensajes.size();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        ObtenerMensajeRequest obtenerMensajeRequest = new ObtenerMensajeRequest(usuarioOrigen,usuarioDestino, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ChatMensaje.this);
        queue.add(obtenerMensajeRequest);

    }
    public void chooseFile(View view){
        Intent myVentanaFile;
        myVentanaFile = new Intent(Intent.ACTION_GET_CONTENT);
        myVentanaFile.setType("*/*");
        startActivityForResult(myVentanaFile,10);




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode){
            case 10:
                if(resultCode == RESULT_OK){
                    String path = data.getData().getPath();
                    String guardarRutaArchivo = path;
                }
                break;
        }
    }
}
