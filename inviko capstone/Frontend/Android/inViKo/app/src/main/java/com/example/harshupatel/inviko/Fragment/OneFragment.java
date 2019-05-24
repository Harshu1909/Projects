package com.example.harshupatel.inviko.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.harshupatel.inviko.Adapters.AppointmentList_Adapter;
import com.example.harshupatel.inviko.Constants;
import com.example.harshupatel.inviko.Diagnosis;
import com.example.harshupatel.inviko.Model.List_Appointment;
import com.example.harshupatel.inviko.Take_Appointment;
import com.example.harshupatel.inviko.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class OneFragment extends Fragment {

    CardView TakeAppointment;
    ListView ListAppoitment;
    ArrayList<List_Appointment> dataModelArrayList;
    AppointmentList_Adapter appointmentList_adapter;
    AlertDialog.Builder builder;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_one, container, false);

        TakeAppointment = (CardView)rootview.findViewById(R.id.Take_Appointment);
        ListAppoitment = (ListView)rootview.findViewById(R.id.List_Appointment);
        List_Appointment();



        TakeAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takeappointment = new Intent(getActivity(),Take_Appointment.class);
                startActivity(takeappointment);
            }
        });

        ListAppoitment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position,long arg3) {

                int id = dataModelArrayList.get(position).getPatientId();
                int aid = dataModelArrayList.get(position).getAppointmentId();
                int did = dataModelArrayList.get(position).getDoctorId();



                Intent i = new Intent(getActivity(),Diagnosis.class);
                i.putExtra("Patient_Id", id);
                i.putExtra("appointment_id", aid);
                i.putExtra("doctor_id",did);
                startActivity(i);


            }
        });
        return rootview;
        }


    private void List_Appointment() {

        //showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.LIST_APPOINTMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
//                            if(obj.optString("status").equals(200)){

                            dataModelArrayList = new ArrayList<>();
                            JSONArray dataArray = obj.getJSONArray("List_Appointment");

                            if (dataArray.length() < 1) {

                                Toast.makeText(getActivity(), "No Appointments", Toast.LENGTH_SHORT).show();
                            } else {

                                for (int i = 0; i < dataArray.length(); i++) {

                                    List_Appointment playerModel = new List_Appointment();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setPatientId(dataobj.getInt("patient_id"));
                                    playerModel.setDoctorId(dataobj.getInt("doctor_id"));
                                    playerModel.setAppointmentId(dataobj.getInt("appointment_id"));

                                    playerModel.setDoctorFirstname(dataobj.getString("doctor_firstname"));
                                    playerModel.setDoctorLastname(dataobj.getString("doctor_lastname"));
                                    playerModel.setPatientFirstname(dataobj.getString("patient_firstname"));
                                    playerModel.setPatientLastname(dataobj.getString("patient_lastname"));

                                    playerModel.setAvailablityTime(dataobj.getString("availablity_time"));
                                    playerModel.setAvailablityDate(dataobj.getString("availablity_date"));


                                    dataModelArrayList.add(playerModel);

                                }

                                setupListview();
                            }

                            } catch(JSONException e){
                                e.printStackTrace();
                            }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);


    }

    private void setupListview(){
        appointmentList_adapter = new AppointmentList_Adapter(getActivity(), dataModelArrayList);
        ListAppoitment.setAdapter(appointmentList_adapter);
    }



}
