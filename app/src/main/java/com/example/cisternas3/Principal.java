package com.example.cisternas3;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class Principal extends AppCompatActivity {
    TextView nombreUser, nombreMatricula;

    EditText recogidaKM, entregaKm,situacion,recogidaFecha,entregaFecha, observaciones, limpieza, reparacion;
    int diaRecogida;
    int diaEntrega;
    private int anyo,mes,dia;
    boolean semaforo = false;
    String nombreVentana;
    String idNombreVentana;
    ArrayList<String> datosSQL = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        entregaKm = (EditText)findViewById(R.id.entregaKm);
        nombreUser = (TextView)findViewById(R.id.nombreUser);
        nombreMatricula = (TextView)findViewById(R.id.nombreMatricula);
        recogidaKM = (EditText)findViewById(R.id.recogidaKM);
        entregaFecha = (EditText)findViewById(R.id.entregaFecha);
        recogidaFecha = (EditText)findViewById(R.id.recogidaFecha);
        observaciones = (EditText)findViewById(R.id.observaciones);
        limpieza = (EditText)findViewById(R.id.limpieza);
        reparacion = (EditText)findViewById(R.id.reparacion);
        situacion = (EditText)findViewById(R.id.situacion);
        observaciones = (EditText)findViewById(R.id.observaciones);



        recogidaKM = (EditText)findViewById(R.id.recogidaKM);

        entregaKm = (EditText)findViewById(R.id.entregaKm);
        situacion = (EditText)findViewById(R.id.situacion);

        entregaFecha = (EditText)findViewById(R.id.entregaFecha);
        recogidaFecha = (EditText)findViewById(R.id.recogidaFecha);

        nombreVentana = getIntent().getExtras().getString("nombreUser");
        nombreUser.setText(nombreVentana);
        String matriculaNombre = getIntent().getExtras().getString("nombreMatricula");
        nombreMatricula.setText(matriculaNombre);
        idNombreVentana=getIntent().getExtras().getString("id");


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String recogidaFecha2 = "";
                String recogidaKm2 = "";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    recogidaFecha2 = jsonResponse.getString("recogidaFecha");
                    recogidaKm2 = jsonResponse.getString("recogidaKm");
                    if (success) {
                        Toast.makeText(getApplicationContext(), "BIEN " + recogidaFecha2, Toast.LENGTH_SHORT).show();
                        Log.e("ALGOVABIEN", "" + recogidaFecha2 + " recogidaKM: " + recogidaKm2);
                        datosSQL.add(recogidaFecha2);
                        datosSQL.add(recogidaKm2);
                        recogidaFecha.setText(datosSQL.get(0));
                        recogidaKM.setText(datosSQL.get(1));
                        semaforo=true;
                        //limpiamos las las cajas de texto
                        //}else{
                        //
                        //}

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    semaforo=false;
                }
            }
        };
            RecogidaRequest recogidarequest = new RecogidaRequest(nombreVentana, matriculaNombre, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Principal.this);
        queue.add(recogidarequest);
        /*DBHelper admin=new DBHelper(this,"Datos",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT recogidaFecha, recogidaKm FROM CisternasDatos WHERE nombre= '" + nombreVentana+ "' AND matricula = '" + matriculaNombre + "' AND entregaFecha = ''",  null);

        if (c.moveToFirst ()) {

            semaforo=true;

            do {
                //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                String recogidaFecha = c.getString(c.getColumnIndex("recogidaFecha"));
                String recogidaKm = c.getString(c.getColumnIndex("recogidaKm"));
                datosSQL.add(recogidaFecha);
                datosSQL.add(recogidaKm);

            } while (c.moveToNext());
            recogidaFecha.setText(datosSQL.get(0));
            recogidaKM.setText(datosSQL.get(1));
        }else{
            semaforo = false;
        }

        //Cerramos el cursor y la conexion con la base de datos
        c.close();
        db.close();*/

    }

    public void introducir(View v){
        DBHelper admin = new DBHelper (this,"Datos",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();


        String NombreUser = nombreUser.getText().toString();
        String guardarMatricula = nombreMatricula.getText().toString();

        String guardaRrecogidaFecha = recogidaFecha.getText().toString();
        String guardaRrecogidaKm = recogidaKM.getText().toString();
        String guardarEntregaFecha = entregaFecha.getText().toString();
        String guardarEntregaKm = entregaKm.getText().toString();
        String guardarSituacion = situacion.getText().toString();
        String guardarLimpieza = limpieza.getText().toString();
        String guardarReparacion = reparacion.getText().toString();
        String guardarObservaciones = observaciones.getText().toString();

        if(semaforo==false) {


            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        Log.e("ADIOS", "" + jsonResponse);
                        //el retorena una respuesta de tipo booleano en funcíon al succes del PHP para saber si se hace cambio o no.
                        boolean success = jsonResponse.getBoolean("success");
                        //importante para hacer lo de insertar o update.
                        if (success) {
                            Toast.makeText(getApplicationContext(), "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Principal.this);
                            builder.setMessage("No se a realizado la query o no hay niongun registro.")
                                    .setNegativeButton("Reintentar", null)
                                    .create().show();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("ADIOS", "" + response);
                    }
                }
            };

            InsertarCisternas1Request registerRequest = new InsertarCisternas1Request(NombreUser, guardarMatricula, guardaRrecogidaFecha, guardaRrecogidaKm, guardarEntregaFecha, guardarEntregaKm, guardarLimpieza, guardarReparacion, guardarSituacion, guardarObservaciones, responseListener);
            RequestQueue queue = Volley.newRequestQueue(Principal.this);
            queue.add(registerRequest);
            /*if (semaforo==false){*/
        }else{
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        //importante para hacer lo de insertar o update.
                        if (success) {
                            Toast.makeText(getApplicationContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Principal.this);
                            builder.setMessage("No se a realizado la query o no hay niongun registro.")
                                    .setNegativeButton("Reintentar", null)
                                    .create().show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

                ActualizarMatriculasRequest actualizarMatriculasRequest = new ActualizarMatriculasRequest(guardarEntregaFecha, guardarEntregaKm, guardarLimpieza, guardarReparacion, guardarSituacion, guardarObservaciones, NombreUser, guardarMatricula ,responseListener);
            RequestQueue queue = Volley.newRequestQueue(Principal.this);
            queue.add(actualizarMatriculasRequest);

        }

        /*
            ContentValues values = new ContentValues();
            values.put("nombre",NombreUser);
            values.put("matricula",guardarMatricula);
            values.put("recogidaFecha",guardaRrecogidaFecha);
            values.put("recogidaKm",guardaRrecogidaKm);
            values.put("entregaFecha",guardarEntregaFecha);
            values.put("entregaKm",guardarEntregaKm);
            values.put("limpieza",guardarLimpieza);
            values.put("reparacion",guardarReparacion);
            values.put("situacion",guardarSituacion);
            values.put("observaciones",guardarObservaciones);

            db.insert("CisternasDatos",null,values);
            db.close();

            Toast.makeText(getApplicationContext(),"SEMAFORO TRUE Datos insertados correctamente" , Toast.LENGTH_SHORT).show();

         }else{
            db.execSQL("UPDATE CisternasDatos SET entregaFecha='"+guardarEntregaFecha+"',entregaKm='"+guardarEntregaKm +"',limpieza='"+guardarLimpieza+"',reparacion='"+guardarReparacion+"',situacion='"+guardarSituacion+"',observaciones='"+guardarObservaciones+"'  WHERE nombre = '"+NombreUser+"' and matricula='"+guardarMatricula+"' and situacion=''");
            Toast.makeText(getApplicationContext(),"SEMAFORO FALSE Datos UPDATE correcto" , Toast.LENGTH_SHORT).show();
        }*/

    }



   /* public void recogidaEntregaIr(View v){
        Intent i=new Intent(this,RecogidaEntrega.class);
        startActivity(i);
    }*/


    public void fechaRecogida(View v){
        Calendar cal = Calendar.getInstance();
        anyo = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);

        String guardarFechaEntrega = entregaFecha.getText().toString();




      //  if (guardarFechaEntrega.matches("")){
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    recogidaFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    //diaRecogida= dayOfMonth;
                }
            },anyo,mes,dia);
            datePickerDialog.show();
       // }
        /*else{
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    recogidaFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    diaRecogida= dayOfMonth;
                }
            },anyo,mes,diaEntrega-1);
            datePickerDialog.show();

        }*/
    }

    public void fechaEntrega1(View v){
        Calendar cal = Calendar.getInstance();
        anyo = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);

        String guardarFechaRecogida = recogidaFecha.getText().toString();



        //if (guardarFechaRecogida.matches("")){
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    entregaFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                   // diaEntrega= dayOfMonth;
                }
            },anyo,mes,dia);
            datePickerDialog.show();
      //  }
       /* else{
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    entregaFecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    diaEntrega= dayOfMonth;
                }
            },anyo,mes,diaRecogida+1);
            datePickerDialog.show();

        }*/



    }
    public void chat(View view){


        Intent ven=new Intent(Principal.this,Chat.class);
        ven.putExtra("nombreUser", nombreVentana);
        ven.putExtra("idNombreVentana",idNombreVentana);
        //ven.putExtra("nombreMatricula", guardarCisterna);
        startActivity(ven);
    }

}