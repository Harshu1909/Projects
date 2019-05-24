package com.example.harshupatel.inviko.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshupatel.inviko.Model.List_Patient;
import java.util.ArrayList;
import com.example.harshupatel.inviko.R;

public class PatientList_Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<List_Patient> dataModelArrayList;

    public PatientList_Adapter(Context context, ArrayList<List_Patient> dataModelArrayList) {

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
                convertView = inflater.inflate(R.layout.lv_player, null, true);

                holder.lvfirstname = (TextView) convertView.findViewById(R.id.lv_firstname);
                holder.lvlastname = (TextView) convertView.findViewById(R.id.lv_lastname);
                holder.lvphone = (TextView) convertView.findViewById(R.id.lv_phone);

                convertView.setTag(holder);
            }else {
                // the getTag returns the viewHolder object set as a tag to the view
                holder = (ViewHolder)convertView.getTag();
            }
            holder.lvfirstname.setText(dataModelArrayList.get(position).getPatientFirstname());
            holder.lvlastname.setText(dataModelArrayList.get(position).getPatientLastname());
            holder.lvphone.setText(dataModelArrayList.get(position).getPhoneNo());

            return convertView;
        }

        private class ViewHolder {

            protected TextView lvfirstname, lvlastname, lvphone;
            protected ImageView iv;
        }

    }
