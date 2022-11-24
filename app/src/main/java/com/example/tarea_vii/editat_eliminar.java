package com.example.tarea_vii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class editat_eliminar extends AppCompatActivity {

    EditText id, titulo, recordatorio, fecha;
    Button seleccionar, editar, eliminar, registrar;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editat_eliminar);

        id = findViewById(R.id.id);
        titulo = findViewById(R.id.titulo);
        recordatorio = findViewById(R.id.recordatorio);
        fecha = findViewById(R.id.fecha);

        seleccionar = findViewById(R.id.seleccionar);
        editar = findViewById(R.id.editar);
        eliminar = findViewById(R.id.eliminar);

        queue = Volley.newRequestQueue(this);

        seleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seleccionarID();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarData();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarData();
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarData();
            }
        });

    }

    public void seleccionarID(){
        String URL = "http://127.0.0.1/conexiones/seleccionar.php?id="+id.getText();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String txtTitulo, txtRecordatorio, txtFecha;

                        try {
                            txtTitulo = response.getString("titulo");
                            txtRecordatorio = response.getString("recordatorio");
                            txtFecha = response.getString("fecha");

                            titulo.setText(txtTitulo);
                            recordatorio.setText(txtRecordatorio);
                            fecha.setText(txtFecha);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void actualizarData(){
        String URL = "http://127.0.0.1/conexiones/editar.php?id="+id.getText()+"&titulo="+titulo.getText()+"&recordatorio="+recordatorio.getText()+"&fecha="+fecha.getText();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.PUT,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void eliminarData(){
        String URL = "http://127.0.0.1/conexiones/eliminar.php?id="+id.getText();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.DELETE,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void registrarData(){
        String URL = "http://127.0.0.1/conexiones/registrar.php?titulo="+titulo.getText()+"&recordatorio="+recordatorio.getText()+"&fecha="+fecha.getText();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(jsonObjectRequest);
    }

}