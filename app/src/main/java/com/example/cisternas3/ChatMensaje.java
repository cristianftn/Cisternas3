package com.example.cisternas3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class ChatMensaje extends AppCompatActivity {
String usuarioOrigen,usuarioDestino;
EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_mensaje);
        texto = (EditText)findViewById(R.id.texto);
        usuarioOrigen = getIntent().getExtras().getString("usuOrigen");
        usuarioDestino = getIntent().getExtras().getString("usuDestino");

        Toast.makeText(getApplicationContext(),"Usuario origen : " + usuarioOrigen + "\n Usuario origen "+usuarioDestino, Toast.LENGTH_SHORT).show();
    }

    public void Texto(View view){

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(texto, InputMethodManager.SHOW_IMPLICIT);
    }
}
