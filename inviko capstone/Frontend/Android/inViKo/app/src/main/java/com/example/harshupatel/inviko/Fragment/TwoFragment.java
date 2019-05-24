package com.example.harshupatel.inviko.Fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.harshupatel.inviko.Constants;
import com.example.harshupatel.inviko.HomePage;
import com.example.harshupatel.inviko.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TwoFragment extends Fragment{

    public TwoFragment() {
        // Required empty public constructor
    }
    ListView notification, annoucement;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview =  inflater.inflate(R.layout.fragment_two, container, false);
        notification = (ListView) rootview.findViewById(R.id.notification);
//        annoucement = (ListView) rootview.findViewById(R.id.annoucement);


        Notification();


//        Annoucement();
        return rootview;

    }



    public void Notification(){

        JSONObject postparams = new JSONObject();

        try {
            postparams.put("Role","Nurse");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonreq = new JsonObjectRequest(Request.Method.POST,Constants.GET_NOTIFICATION,postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            ArrayList<String> values= new ArrayList<String>();


                            JSONArray jsonArray = response.getJSONArray("Notifications");

                            JSONObject json_data ;


                            for(int i = 0; i < jsonArray.length(); i++) {

//                                values.add(jsonArray.get(i).);
//                                if(i%2 == 0) {
//                                    i.setc(Color.GREEN);
                                    json_data = jsonArray.getJSONObject(i);
                                    String name = String.valueOf(json_data.get("text"));

                                    values.add(name);
//                                }
//                                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                                Notification notify= null;
//                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                                    notify = new Notification.Builder
//                                            (getActivity()).setContentTitle(name).setSmallIcon(R.drawable.abc).build();
//                                }
//
//                                notify.flags |= Notification.FLAG_AUTO_CANCEL;
//                                notif.notify(0, notify);

//                                Toast.makeText(Homemenu.this, name, Toast.LENGTH_SHORT).show();




                            }


                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (getActivity(), R.layout.lv,R.id.text1, values){

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent){
                                    // Get the current item from ListView
                                    View view = super.getView(position,convertView,parent);
                                    if(position %2 == 1)
                                    {
                                        // Set a background color for ListView regular row/item
                                        view.setBackgroundColor(Color.parseColor("#2D80E3"));
                                    }
                                    else
                                    {
                                        // Set the background color for alternate row/item
                                        view.setBackgroundColor(Color.parseColor("#D3D3D3"));
                                    }
                                    return view;
                                }
                            };
//
//
                            notification.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(getActivity()).add(jsonreq);
    }




//    public void Annoucement(){
//
//        JSONObject postparams = new JSONObject();
//
//        try {
//            postparams.put("Role","Nurse");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        JsonObjectRequest jsonreq = new JsonObjectRequest(Request.Method.POST,Constants.GET_ANNOUCEMENT,postparams,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        try {
//
//                            ArrayList<String> values= new ArrayList<String>();
//
//
//                            JSONArray jsonArray = response.getJSONArray("Annoucement");
//
//                            JSONObject json_data ;
//
//
//                            for(int i = 0; i < jsonArray.length(); i++) {
//
////                                values.add(jsonArray.get(i).);
//
//                                json_data = jsonArray.getJSONObject(i);
//                                String name= String.valueOf(json_data.get("text"));
//
//                                values.add(name);
//
////                                Toast.makeText(Homemenu.this, name, Toast.LENGTH_SHORT).show();
//
//
//
//
//                            }
//
//
//                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                                    (getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
////
////
//                            annoucement.setAdapter(adapter);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        Volley.newRequestQueue(getActivity()).add(jsonreq);
//    }

}
