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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText EdCorreo,EdContraseña;
    Button BtEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdCorreo=findViewById(R.id.ed_correo);
        EdContraseña=findViewById(R.id.ed_contraseña);
        BtEntrar=findViewById(R.id.btn_entrar);
        BtEntrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        StringRequest st=new StringRequest(Request.Method.GET,
                "http://unioncanina.mipantano.com/loginandroid",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        if(response.contains("1")){
                            startActivity(new Intent(getApplicationContext(),Inside.class));
                        }
                        Toast.makeText(getApplicationContext(),"Esto es una respuesta"+response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
                Toast.makeText(getApplicationContext(),"Ahora haber problema",Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("usuario",EdCorreo.getText().toString());
                params.put("contraseña",EdContraseña.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(st);
    }
}
