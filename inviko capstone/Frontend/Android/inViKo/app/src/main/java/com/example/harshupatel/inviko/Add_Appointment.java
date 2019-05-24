package com.example.harshupatel.inviko;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.harshupatel.inviko.Adapters.DoctorList_Adapter;
import com.example.harshupatel.inviko.Adapters.PatientList_Adapter;
import com.example.harshupatel.inviko.Model.List_Doctor;
import com.example.harshupatel.inviko.Model.List_Patient;
import com.example.harshupatel.inviko.Responese.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Add_Appointment extends AppCompatActivity {

    CalendarView AppointmentDate;
    TimePicker AppointmentTime;
    TextView Name,Email,Phone;
    ArrayList<List_Doctor> dataModelArrayList;
    ArrayList<String> CountryName;
    String st;
    DoctorList_Adapter doctorList_adapter;
    Spinner doctor_dropdown;
    int doctorid;



    String date,month1,year1;
    String AppointmentDateString;
    String AppointmentHours,AppointmentMinute,AppointmentTimeString;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__appointment);

        Intent i = getIntent();
        int id = i.getIntExtra("Patient_id",0);
        st = String.valueOf(id);
        Toast.makeText(this , st, Toast.LENGTH_SHORT).show();

        AppointmentDate = (CalendarView) findViewById(R.id.appointment_date);
        AppointmentTime = (TimePicker) findViewById(R.id.appointment_time);
        doctor_dropdown =(Spinner) findViewById(R.id.doctor_name);

        Name = (TextView) findViewById(R.id.Appointment_name);
        Phone = (TextView) findViewById(R.id.Appointment_phone);
        Email = (TextView) findViewById(R.id.Appointment_email);

        AppointmentDate.setOnDateChangeListener( new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                date = Integer.toString(dayOfMonth);
                month1 = "0"+Integer.toString(month);
                year1 = Integer.toString(year);

                AppointmentDateString = month1 + "/" + date + "/" + year1;




            }
        });

        int hour = AppointmentTime.getHour();
        int minute = AppointmentTime.getMinute();


        AppointmentHours = Integer.toString(hour);
        AppointmentMinute = Integer.toString(minute);

        AppointmentTimeString = AppointmentHours + ":" + AppointmentMinute;
        List_Patients();
        Doctor_Spinner();
        doctor_dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doctorid = dataModelArrayList.get(position).getDoctorId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });



    }




    private void Doctor_Spinner(){

        JSONObject postparams = new JSONObject();

        try {
            postparams.put("Email","123");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, Constants.LIST_DOCTOR,postparams,
                new Response.Listener() {



                    @Override
                    public void onResponse(Object response) {

//                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(String.valueOf(response));
//                            if(obj.optString("status").equals(200)){



                            dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray  = obj.getJSONArray("List_Doctor");




                            for (int i = 0; i < dataArray.length(); i++) {

                                List_Doctor playerModel = new List_Doctor();
                                JSONObject dataobj = dataArray.getJSONObject(i);

                                playerModel.setDoctorId(dataobj.getInt("doctor_id"));
                                playerModel.setDoctorFirstname(dataobj.getString("doctor_firstname"));
                                playerModel.setDoctorLastname(dataobj.getString("doctor_lastname"));
                                playerModel.setDoctorLastname(dataobj.getString("doctor_firstname"));

                                dataModelArrayList.add(playerModel);

                            }
                            setupListDoctor();
                            //doctor_dropdown.setAdapter(new ArrayAdapter<String>(Add_Appointment.this, android.R.layout.simple_spinner_dropdown_item, CountryName));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(Add_Appointment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(Add_Appointment.this);

        requestQueue.add(stringRequest);




    }

    private void setupListDoctor(){
        doctorList_adapter = new DoctorList_Adapter(Add_Appointment.this, dataModelArrayList);
        doctor_dropdown.setAdapter(doctorList_adapter);
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
                        Toast.makeText(Add_Appointment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(Add_Appointment.this);

        requestQueue.add(stringRequest);


    }

    public void add_appointment(View view) {

        Toast.makeText(this, AppointmentDateString + AppointmentTimeString, Toast.LENGTH_SHORT).show();

        JSONObject postparams = new JSONObject();

        try {
            postparams.put("Doctor_id", doctorid);
            postparams.put("Patient_id", st);
            postparams.put("Time", AppointmentTimeString);
            postparams.put("Date", AppointmentDateString);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, Constants.ADD_APPOINTMENT, postparams,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {

                        LoginResponse loginResponse = new Gson().fromJson(response.toString(), LoginResponse.class);


                        if (loginResponse.getStatus().equals("200")) {

                            Toast.makeText(Add_Appointment.this, "Appointment added", Toast.LENGTH_SHORT).show();
                            Intent homemenu = new Intent(Add_Appointment.this, HomePage.class);
                            startActivity(homemenu);

                        }
                        // Toast.makeText(Register.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Add_Appointment.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(Add_Appointment.this).add(jsonObjReq);


    }
}
