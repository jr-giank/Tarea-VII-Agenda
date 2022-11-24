package com.example.tarea_vii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TodosActivity extends AppCompatActivity {

    TextView recordatorio;
    RequestQueue queue;
    Button button, acciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);
        recordatorio = findViewById(R.id.recordatorios);
        button = findViewById(R.id.button);
        acciones = findViewById(R.id.acciones);

        queue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordatoriosData();
            }
        });

        acciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TodosActivity.this, editat_eliminar.class);
                startActivity(i);
            }
        });
    }

    public void recordatoriosData(){
        String URL = "http://127.0.0.1/conexiones/recordatorios.php";

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

                            recordatorio.append("Titulo: " + txtTitulo + " Recordatorio: " + txtRecordatorio + "Fecha: " + txtFecha);

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
}