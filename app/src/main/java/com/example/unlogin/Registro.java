package com.example.unlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {
    TextView Ednombre,Edapat,Edamat,Edcorreo,Edpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Ednombre=findViewById(R.id.ed_nombre);
        Edapat=findViewById(R.id.ed_apat);
        Edamat=findViewById(R.id.ed_amat);
        Edcorreo=findViewById(R.id.ed_correo);
        Edpwd=findViewById(R.id.ed_contraseña);
    }

    public void registrar(View view) {
        //Con JSONOBJECT
        JSONObject obj= new JSONObject();

        try {
            obj.put("nombre","axel");
            obj.put("apat","serna");
            obj.put("amat","roman");
            obj.put("correo","axel2654");
            obj.put("pwd","axel");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("objeto", obj.toString());


        String url="http://unioncanina.mipantano.com/api/register";
        //RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Response", response.toString());
                    }}, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error response",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    public void irIniciar(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}

