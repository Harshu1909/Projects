package com.example.harshupatel.inviko.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.harshupatel.inviko.Adapters.PatientList_Adapter;
import com.example.harshupatel.inviko.Add_Appointment;
import com.example.harshupatel.inviko.Constants;
import com.example.harshupatel.inviko.ListHistory;
import com.example.harshupatel.inviko.Model.List_Patient;
import com.example.harshupatel.inviko.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ThreeFragment extends Fragment {

    ListView PatientsList;
    private static ProgressDialog mProgressDialog;
    ArrayList<List_Patient> dataModelArrayList;
    PatientList_Adapter patientList_adapter;
    public ThreeFragment() {
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
        View rootview =  inflater.inflate(R.layout.fragment_three, container, false);

        PatientsList = (ListView)rootview.findViewById(R.id.PatientsList);
        List_Patients();
        PatientsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position,long arg3) {

                List_Patient data = new List_Patient();
                int id = dataModelArrayList.get(position).getPatientId();



                Intent i = new Intent(getActivity(),ListHistory.class);
                i.putExtra("Patient_id", id);
                startActivity(i);


            }
        });

        return rootview;
    }

    private void List_Patients() {

        //showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.LIST_PATIENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
//                            if(obj.optString("status").equals(200)){

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("List_Patients");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    List_Patient playerModel = new List_Patient();
                                    JSONObject dataobj = dataArray.getJSONObject(i);
                                    playerModel.setPatientId(dataobj.getInt("patient_id"));
                                    playerModel.setPatientFirstname(dataobj.getString("patient_firstname"));
                                    playerModel.setPatientLastname(dataobj.getString("patient_lastname"));
                                    playerModel.setPhoneNo(dataobj.getString("phone_no"));

                                    dataModelArrayList.add(playerModel);

                                }

                                setupListview();

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
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);


    }

    private void setupListview(){
        patientList_adapter = new PatientList_Adapter(getActivity(), dataModelArrayList);
        PatientsList.setAdapter(patientList_adapter);
    }


}
