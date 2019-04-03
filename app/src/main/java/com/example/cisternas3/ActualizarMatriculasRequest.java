package com.example.cisternas3;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
public class ActualizarMatriculasRequest extends StringRequest {

    private static final String insertar1_REQUEST="http://www.innovabilbao.com/app/actualizarMatriculas.php";

    private Map<String,String> params;

    public ActualizarMatriculasRequest(String entregaFecha, String entregaKm, String limpieza, String reparacion, String situacion, String observaciones, String nombre, String matricula, Response.Listener<String> listener){
        super(Request.Method.POST,insertar1_REQUEST,listener,null);

        params = new HashMap<>();
        params.put("entregaFecha",entregaFecha);
        params.put("entregaKm",entregaKm);
        params.put("limpieza",limpieza);
        params.put("reparacion",reparacion);
        params.put("situacion",situacion);
        params.put("observaciones",observaciones);
        params.put("limpieza",limpieza);
        params.put("reparacion",reparacion);
        params.put("observaciones",observaciones);
        params.put("nombre",nombre);
        params.put("matricula",matricula);

        //se puede cambiar un int  a string  params.put("situacion",situacion + ""); tener en cuenta q donde se asigna el dato este con Integer.parseInt

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}

