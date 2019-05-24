package com.example.harshupatel.inviko.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshupatel.inviko.Model.List_Appointment;
import com.example.harshupatel.inviko.Model.List_Patient;
import com.example.harshupatel.inviko.R;

import java.util.ArrayList;

public class AppointmentList_Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<List_Appointment> dataModelArrayList;

    public AppointmentList_Adapter(Context context, ArrayList<List_Appointment> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

    }
        @Override
        public int getViewTypeCount() {
            return getCount();
        }
        @Override
        public int getItemViewType(int position) {

            return position;
        }

        @Override
        public int getCount() {
            return dataModelArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataModelArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.lv_appointment, null, true);

                holder.lvfirstname = (TextView) convertView.findViewById(R.id.lv_firstname);
                holder.lvlastname = (TextView) convertView.findViewById(R.id.lv_lastname);
                holder.lvdoctorname = (TextView) convertView.findViewById(R.id.lv_phone);
                holder.lvtime = (TextView) convertView.findViewById(R.id.lv_time);
                holder.lvdate = (TextView) convertView.findViewById(R.id.lv_date);

                convertView.setTag(holder);
            }else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder)convertView.getTag();
            }

            holder.lvfirstname.setText(String.valueOf(dataModelArrayList.get(position).getPatientFirstname()));
            holder.lvlastname.setText(String.valueOf(dataModelArrayList.get(position).getPatientLastname()));
            holder.lvdoctorname.setText(String.valueOf(dataModelArrayList.get(position).getDoctorFirstname()));
            holder.lvdate.setText(String.valueOf(dataModelArrayList.get(position).getAvailablityDate()));
            holder.lvtime.setText(String.valueOf(dataModelArrayList.get(position).getAvailablityTime()));

            return convertView;
        }

        private class ViewHolder {

            protected TextView lvfirstname, lvlastname, lvdoctorname, lvtime, lvdate;
            protected ImageView iv;
        }

    }
