package com.example.cisternas3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class RecogidaEntrega extends AppCompatActivity {
/*
    EditText recogidaFecha, fechaEntrega;

    private int day,month,year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recogida_entrega);

        recogidaFecha = (EditText) findViewById(R.id.recogidaFecha);

        fechaEntrega = (EditText) findViewById(R.id.fechaEntrega);
    }


    public void fechaRecogida(View v){

        Calendar cal = Calendar.getInstance();
        String diaActual = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());


        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);



        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                recogidaFecha.setText(dayOfMonth+"/"+(month+1)+"/"+ year);
            }
        },year,month,day);
        datePickerDialog.show();


        //  recogidaFecha.setText(diaActual);


    }
    public void fechaEntrega(View v) {

        Calendar cal = Calendar.getInstance();
       // String diaActual = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());


        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {



            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fechaEntrega.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);
        datePickerDialog.show();

    }



    public void confirmar(View v){

        String guardarEntrega = fechaEntrega.getText().toString();

        if (guardarEntrega.matches("") ){
            Toast.makeText(getApplicationContext(), "Selecciona una fecha", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "La fecha es: " + fechaEntrega.getText(), Toast.LENGTH_LONG).show();
        }
    }

    public void recogidaEntregaAPrincipal(View v){

        Intent p = new Intent(this,Principal.class);

        startActivity(p);

    }*/
}

