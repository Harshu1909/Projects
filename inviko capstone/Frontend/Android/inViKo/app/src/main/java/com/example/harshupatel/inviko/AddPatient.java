package com.example.harshupatel.inviko;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.harshupatel.inviko.Fragment.FiveFragment;
import com.example.harshupatel.inviko.Responese.LoginResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class AddPatient extends AppCompatActivity {

    EditText FirstName,LastName,Email,Address1,Address2,PostalCode,Province,Height,Weight,Age,ContactNumber;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);




        FirstName = (EditText)findViewById(R.id.etPfName);
        LastName = (EditText)findViewById(R.id.etPlName);
        Email = (EditText)findViewById(R.id.etPEmail);
        Address1 = (EditText)findViewById(R.id.etPAddress1);
        Address2 = (EditText)findViewById(R.id.etPAddress2);
        PostalCode = (EditText)findViewById(R.id.etPPostalcode);
        Province = (EditText)findViewById(R.id.etPProvince);
        Address2 = (EditText)findViewById(R.id.etPAddress2);
        Height = (EditText)findViewById(R.id.etPHeight);
        Weight = (EditText)findViewById(R.id.etPWeight);
        Age = (EditText)findViewById(R.id.etPAge);
        ContactNumber = (EditText)findViewById(R.id.etPPhone);
    }

    public void PatientAdd(View view) {

        String Fname = FirstName.getText().toString();
        String Lname = LastName.getText().toString();
        String EmailString = Email.getText().toString();
        String StAddress1 = Address1.getText().toString();
        String StAddress2 = Address2.getText().toString();
        String PostalCodeString = PostalCode.getText().toString();
        String ProvinceString = Province.getText().toString();
        String StHeight = Height.getText().toString();
        String StWeight = Weight.getText().toString();
        String StAge = Age.getText().toString();
        String Number = ContactNumber.getText().toString();


        if (Fname.equals("") || Lname.equals("") || EmailString.equals("") ||StAddress1.equals("") || StAddress2.equals("") || PostalCodeString.equals("") || ProvinceString.equals("") ||StHeight.equals("") || StWeight.equals("") || StAge.equals("") || Number.equals("")) {
            Toast.makeText(getApplicationContext(), "Field Vacant", Toast.LENGTH_LONG).show();
            return;
        } else if (Number.length() != 10) {
            ContactNumber.setError("Invalid Contact Number");
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
            postparams.put("PatientFirstName", Fname);
            postparams.put("PatientLastName", Lname);
            postparams.put("PatientEmail", EmailString);
            postparams.put("PatientAdd1", StAddress1);
            postparams.put("PatientAdd2", StAddress2);
            postparams.put("PostalCode", PostalCodeString);
            postparams.put("Province", ProvinceString);
            postparams.put("PatientHeight", StHeight);
            postparams.put("PatientWeight", StWeight);
            postparams.put("PatientAge", StAge);
            postparams.put("Phone", Number);
            postparams.put("DoctorId", 1);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Constants.ADD_PATIENT, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        LoginResponse loginResponse = new Gson().fromJson(response.toString(), LoginResponse.class);


                        if (loginResponse.getStatus().equals("200")) {

                            Toast.makeText(AddPatient.this, "Patient has been added", Toast.LENGTH_SHORT).show();
                            Intent homepage = new Intent(AddPatient.this, FiveFragment.class);
                            startActivity(homepage);
                            finish();

                        }
                        // Toast.makeText(Register.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddPatient.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(AddPatient.this).add(jsonObjReq);


    }
}
