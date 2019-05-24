package com.example.harshupatel.inviko;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.harshupatel.inviko.Responese.LoginResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity{

    EditText Username, Password;
    ProgressDialog progressDialog;
    private RequestQueue requestQueue;
    private static Login mInstance;
    RequestQueue queue;
    private ProgressBar pb;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.etEmail);
        Password = findViewById(R.id.etPassword);
        queue = Volley.newRequestQueue(this);
    }


    public void login (View view) {

            String uname = Username.getText().toString();
            String password = Password.getText().toString();

        if(uname.equals("")||password.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Invalid Email/Password",Toast.LENGTH_LONG).show();
            return;
        }
//        else if (password.length() < 6) {
//            Password.setError("Atleast 6 letters");
//            return;
//        } else if (!uname.matches(emailPattern)) {
//            Username.setError("Invalid Email Address");
//            return;
//        }

            JSONObject postparams = new JSONObject();

            try {
                postparams.put("Email",uname);
                postparams.put("Password",password);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,Constants.LOGIN_API , postparams,
                    new Response.Listener() {
                        @Override
                        public void onResponse(Object response) {

                            LoginResponse loginResponse = new Gson().fromJson(response.toString(), LoginResponse.class);


                            if (loginResponse.getStatus().equals("200")){

                                Intent homemenu = new Intent(Login.this,HomePage.class);
                                startActivity(homemenu);
                                finish();

                            }
                            //Toast.makeText(Login.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();




                        }
                        },
                    new Response.ErrorListener() {
                        @Override
                            public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

           Volley.newRequestQueue(Login.this).add(jsonObjReq);

    }

    public void Registration(View view) {

        Intent register = new Intent(Login.this,Register.class);
        startActivity(register);
    }
}
