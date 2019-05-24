package com.example.harshupatel.inviko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.harshupatel.inviko.Responese.LoginResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Register extends AppCompatActivity {


    EditText FirstName,LastName,Email,Password,ConfirmPassword,Address1,Address2,City,PostalCode,Province,Height,Weight,Age,ContactNumber;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        FirstName = (EditText)findViewById(R.id.etfName);
        LastName = (EditText)findViewById(R.id.etlName);
        Email = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);
        ConfirmPassword = (EditText)findViewById(R.id.etPassword_confirm);
        Address1 = (EditText)findViewById(R.id.etAdd1);
        Address2 = (EditText)findViewById(R.id.etAdd2);
        City = (EditText)findViewById(R.id.etAdd2);
        PostalCode = (EditText)findViewById(R.id.etAdd2);
        Province = (EditText)findViewById(R.id.etAdd2);
        Height = (EditText)findViewById(R.id.etHeight);
        Weight = (EditText)findViewById(R.id.etWeight);
        Age = (EditText)findViewById(R.id.etAge);
        ContactNumber = (EditText)findViewById(R.id.etPhone);



    }

    public void Register(View view) {

        String Fname = FirstName.getText().toString();
        String Lname = LastName.getText().toString();
        String EmailString = Email.getText().toString();
        String PasswordString = Password.getText().toString();
        String CPassword = ConfirmPassword.getText().toString();
        String StAddress1 = Address1.getText().toString();
        String StAddress2 = Address2.getText().toString();
        String StCity = City.getText().toString();
        String StPostalcode = PostalCode.getText().toString();
        String StProvince = Province.getText().toString();
        String StHeight = Height.getText().toString();
        String StWeignt = Weight.getText().toString();
        String StAge = Age.getText().toString();
        String Number = ContactNumber.getText().toString();


        if (Fname.equals("") || Lname.equals("") || EmailString.equals("") || PasswordString.equals("") || CPassword.equals("") || StAddress1.equals("") || StAddress2.equals("") ||
                StCity.equals("") || StPostalcode.equals("") || StProvince.equals("") || StHeight.equals("") || StWeignt.equals("") || StAge.equals("") || Number.equals("")) {
            Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
            return;
        } else if (PasswordString.length() < 6) {
            Password.setError("Atleast 6 letters");
            return;
        } else if (!EmailString.matches(emailPattern)) {
            Email.setError("Invalid Email Address");
            return;
        } else if (Number.length() != 10) {
            ContactNumber.setError("Invalid Contact Number");
            return;
        }

        JSONObject postparams = new JSONObject();

        try {
            postparams.put("Firstname", Fname);
            postparams.put("Lastname", Lname);
            postparams.put("Email", EmailString);
            postparams.put("Password", PasswordString);
            postparams.put("ConfrimPassword", CPassword);
            postparams.put("Address1", StAddress1);
            postparams.put("Address2", StAddress2);
            postparams.put("City", StCity);
            postparams.put("PostalCode", StPostalcode);
            postparams.put("Province", StProvince);
            postparams.put("Height", StHeight);
            postparams.put("Weight", StWeignt);
            postparams.put("Age", StAge);
            postparams.put("Number", Number);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Constants.REGISTER_API, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        LoginResponse loginResponse = new Gson().fromJson(response.toString(), LoginResponse.class);


                        if (loginResponse.getStatus().equals("200")) {

                            Toast.makeText(Register.this, "Register Successfuly", Toast.LENGTH_SHORT).show();
                            Intent homemenu = new Intent(Register.this, Login.class);
                            startActivity(homemenu);

                        }
                        // Toast.makeText(Register.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(Register.this).add(jsonObjReq);


    }


}
