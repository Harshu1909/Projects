package com.example.harshupatel.inviko.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harshupatel.inviko.AddPatient;
import com.example.harshupatel.inviko.R;


public class FiveFragment extends Fragment {

    CardView First, Third, Fourth, Fifth;

    public FiveFragment() {
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
        View rootview =  inflater.inflate(R.layout.fragment_five, container, false);

        First = (CardView)rootview.findViewById(R.id.Add_Patient);
        Third = (CardView)rootview.findViewById(R.id.View_Appointment);
        Fourth = (CardView)rootview.findViewById(R.id.viewprofile);
        Fifth = (CardView)rootview.findViewById(R.id.logout);

        First.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent AddPatient = new Intent(getActivity(),com.example.harshupatel.inviko.AddPatient.class);
                startActivity(AddPatient);
            }
        });


        Third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ViewAppointment = new Intent(getActivity(),com.example.harshupatel.inviko.ViewAppointment.class);
                startActivity(ViewAppointment);
            }
        });

        Fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ViewProfile = new Intent(getActivity(),com.example.harshupatel.inviko.ViewProfile.class);
                startActivity(ViewProfile);
            }
        });

        Fifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ViewProfile = new Intent(getActivity(),com.example.harshupatel.inviko.Login.class);
                startActivity(ViewProfile);
                getActivity().finish();
            }
        });
        return rootview;
    }
}
