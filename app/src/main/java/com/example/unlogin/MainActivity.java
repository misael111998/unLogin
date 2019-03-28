package com.example.unlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText EdCorreo, EdContraseña;
    Button BtEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdCorreo = findViewById(R.id.ed_correo);
        EdContraseña = findViewById(R.id.ed_contraseña);
        BtEntrar = findViewById(R.id.btn_entrar);
        BtEntrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        //Con JSONOBJECT
        JSONObject obj= new JSONObject();

        try {
            obj.put("correo",EdCorreo.getText().toString());
            obj.put("pwd",EdContraseña.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String url="http://unioncanina.mipantano.com/api/login";
    //RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response.isNull("nombre")){
                            Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                        }else{
                            startActivity(new Intent(getApplicationContext(),Inside.class));
                        }

                        try {
                           Log.e("nombre",response.getString("nombre")) ;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }}, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
                        }
                    });
                Volley.newRequestQueue(this).add(jsonObjectRequest);
                }


/*
        StringRequest st = new StringRequest(Request.Method.GET,
                "http://unioncanina.mipantano.com/api/login",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("Respuesta",response);
                        if (response.contains("1")) {
                            startActivity(new Intent(getApplicationContext(), Inside.class));
                            Toast.makeText(getApplicationContext(), "Esto es una respuesta con 1", Toast.LENGTH_LONG).show();
                        }
                        if(response.contains("0")){
                            Toast.makeText(getApplicationContext(), "Esto es una respuesta con 0", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.toString());
                Toast.makeText(getApplicationContext(), "Error response", Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("correo", EdCorreo.getText().toString());
                params.put("pwd", EdContraseña.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(st);
    }
    */}


