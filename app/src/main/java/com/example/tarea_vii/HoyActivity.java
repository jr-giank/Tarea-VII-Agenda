package com.example.tarea_vii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//import java.time.LocalDate;
import java.time.*;

import java.util.Calendar;
import java.util.Date;

public class HoyActivity extends AppCompatActivity {

    TextView recordatoriosHoy;
    RequestQueue queue;
    Button button;
    Date currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoy);

        currentTime = Calendar.getInstance().getTime();

        recordatoriosHoy = findViewById(R.id.recordatoriosH);

        button = findViewById(R.id.button);

        queue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordatoriosHoyData();
            }
        });
    }

    public void recordatoriosHoyData(){
        String URL = "http://127.0.0.1/conexiones/recordatoriosHoy.php?fecha="+currentTime;

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

                            recordatoriosHoy.append("Titulo: " + txtTitulo + " Recordatorio: " + txtRecordatorio + "Fecha: " + txtFecha);

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