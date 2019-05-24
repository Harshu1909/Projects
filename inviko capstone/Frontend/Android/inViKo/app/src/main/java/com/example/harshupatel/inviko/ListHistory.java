package com.example.harshupatel.inviko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListHistory extends AppCompatActivity {

    String st;
    TextView Name,Email,Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_history);

        Name = (TextView) findViewById(R.id.Appointment_name);
        Phone = (TextView) findViewById(R.id.Appointment_phone);
        Email = (TextView) findViewById(R.id.Appointment_email);

        Intent i = getIntent();
        int id = i.getIntExtra("Patient_id",0);
        st = String.valueOf(id);
        Toast.makeText(this , st, Toast.LENGTH_SHORT).show();

        List_Patients();
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



                           // dataModelArrayList = new ArrayList<>();
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
                        Toast.makeText(ListHistory.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(ListHistory.this);

        requestQueue.add(stringRequest);


    }
}
