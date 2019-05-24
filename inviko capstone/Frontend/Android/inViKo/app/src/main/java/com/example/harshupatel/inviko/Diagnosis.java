package com.example.harshupatel.inviko;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.harshupatel.inviko.Fragment.OneFragment;
import com.example.harshupatel.inviko.Model.List_Doctor;
import com.example.harshupatel.inviko.Model.List_Patient;
import com.example.harshupatel.inviko.Responese.LoginResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.Delayed;

public class Diagnosis extends AppCompatActivity {

    String st;
    TextView Name,Phone,Email;
    ArrayList<List_Doctor> dataModelArrayList;

    EditText DiagnosisName,Pricription,Reports;
    String diagnosisname,prescription,reports;
    int appointment_id,patient_id,doctor_id;
    AlertDialog.Builder builder;
    ImageView delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        Intent i = getIntent();
        patient_id = i.getIntExtra("Patient_Id",0);
        appointment_id = i.getIntExtra("appointment_id",0);
        doctor_id = i.getIntExtra("doctor_id",0);
        st = String.valueOf(patient_id);

        Name = (TextView) findViewById(R.id.Appointment_name);
        Phone = (TextView) findViewById(R.id.Appointment_phone);
        Email = (TextView) findViewById(R.id.Appointment_email);
        delete = (ImageView) findViewById(R.id.deleteappointment);

        DiagnosisName = (EditText)findViewById(R.id.diagnosis_name);
        Pricription = (EditText)findViewById(R.id.priscription);
        Reports = (EditText)findViewById(R.id.reports);

        List_Patients();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_appointment();
            }
        });

    }

    private void List_Patients() {

        //showSimpleProgressDialog(this, "Loading...","Fetching Json",false);
        JSONObject postparams = new JSONObject();

        try {
            postparams.put("Patient_id",st);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Constants.PATIENT_DETAIL_BY_ID,postparams,
                new Response.Listener() {


                    @Override
                    public void onResponse(Object response) {

//                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(String.valueOf(response));
//                            if(obj.optString("status").equals(200)){



                            dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("Patients_Details");




                            JSONObject dataobj = dataArray.getJSONObject(0);

                            int id = dataobj.getInt("patient_id");
                            String  firstname = dataobj.getString("patient_firstname");
                            String lastname = dataobj.getString("patient_lastname");
                            String email = dataobj.getString("patient_email");
                            String phone = dataobj.getString("phone_no");

                            Name.setText(firstname + " " + lastname);
                            Email.setText(email);
                            Phone.setText(phone);




//                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(Diagnosis.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(Diagnosis.this);

        requestQueue.add(stringRequest);


    }

    public void add_diagnosis(View view) {

        diagnosisname=DiagnosisName.getText().toString();
        prescription = Pricription.getText().toString();
        reports = Reports.getText().toString();

        JSONObject postparams = new JSONObject();

        try {
            postparams.put("Patient_id", patient_id);
            postparams.put("Doctor_id", doctor_id);
            postparams.put("Appointment_id", appointment_id);
            postparams.put("Diagnosis_name", diagnosisname);
            postparams.put("Prescription", prescription);
            postparams.put("Reports", reports);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Constants.ADD_DIAGNOSIS, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        LoginResponse loginResponse = new Gson().fromJson(response.toString(), LoginResponse.class);


                        if (loginResponse.getStatus().equals("200")) {
                            delete_api();

                            Toast.makeText(Diagnosis.this, "Diagnosis added", Toast.LENGTH_SHORT).show();
                            Intent homemenu = new Intent(Diagnosis.this, HomePage.class);
                            startActivity(homemenu);

                        }
                        // Toast.makeText(Register.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Diagnosis.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(Diagnosis.this).add(jsonObjReq);


    }
    public void delete_appointment() {


        builder = new AlertDialog.Builder(Diagnosis.this);
        builder.setMessage("Do you want to delete this appointment ?")
                .setTitle("Delete Appointment")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        delete_api();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void delete_api(){


        JSONObject postparams = new JSONObject();

        try {

            postparams.put("Appointment_id", appointment_id);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Constants.DELETE_APPOINTMENT, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        LoginResponse loginResponse = new Gson().fromJson(response.toString(), LoginResponse.class);


                        if (loginResponse.getStatus().equals("200")) {

                            Toast.makeText(Diagnosis.this, "Delete Appointment", Toast.LENGTH_SHORT).show();
                            Intent homemenu = new Intent(Diagnosis.this, HomePage.class);
                            startActivity(homemenu);
                            finish();

                        }
                        // Toast.makeText(Register.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Diagnosis.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(Diagnosis.this).add(jsonObjReq);


    }

}
