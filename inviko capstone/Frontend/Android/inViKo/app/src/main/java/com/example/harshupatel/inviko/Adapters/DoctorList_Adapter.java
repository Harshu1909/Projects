package com.example.harshupatel.inviko.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harshupatel.inviko.Model.List_Doctor;
import com.example.harshupatel.inviko.Model.List_Patient;
import com.example.harshupatel.inviko.R;

import java.util.ArrayList;

public class DoctorList_Adapter extends BaseAdapter {


    private Context context;
    private ArrayList<List_Doctor> dataModelArrayList;

    public DoctorList_Adapter(Context context, ArrayList<List_Doctor> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;

    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public int getItemViewType(int position) {

        return position%1;
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
        DoctorList_Adapter.ViewHolder holder;

        if (convertView == null) {
            holder = new DoctorList_Adapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_spinner, null, true);

            holder.lvfirstname = (TextView) convertView.findViewById(R.id.sp_firstname);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (DoctorList_Adapter.ViewHolder)convertView.getTag();
        }
        holder.lvfirstname.setText(dataModelArrayList.get(position).getDoctorFirstname());
        //holder.lvlastname.setText(dataModelArrayList.get(position).getDoctorLastname());

        return convertView;
    }

    private class ViewHolder {

        protected TextView lvfirstname, lvlastname;
    }

}
