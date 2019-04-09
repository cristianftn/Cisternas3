package com.example.cisternas3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


import java.util.Map;

public class ListaUsuariosChatRequest extends StringRequest{

    private static final String ListaUsuariosChat_REQUEST="http://192.168.1.47/ListaUsuariosChat.php";
    private Map<String,String> params;

    public ListaUsuariosChatRequest(String nombre, Response.Listener<String> listener){
        super(Method.POST,ListaUsuariosChat_REQUEST,listener,null);
        params = new HashMap<>();
        params.put("nombre",nombre);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }




}
