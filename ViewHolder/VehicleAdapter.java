package com.example.issa.pdm_project_2018.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.Model.Cars;
import com.example.issa.pdm_project_2018.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Lenovo™ on 4/26/2018.
 */

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.vehicleviewholder> {

    Context ctex;
    List<Cars> vehiclelist;

    public VehicleAdapter(Context ctex, List<Cars> vehiclelist) {
        this.ctex = ctex;
        this.vehiclelist = vehiclelist;
    }

    @Override
    public VehicleAdapter.vehicleviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View vi= LayoutInflater.from(ctex).inflate(R.layout.license_layout,parent,false);
        return  new vehicleviewholder(vi);

    }

    @Override
    public void onBindViewHolder(VehicleAdapter.vehicleviewholder holder, int position) {

        Cars v=vehiclelist.get(position);
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fdate=dateFormat.parse(v.getLicenseExpDate());
            Date cdate=new Date();
            long diff=fdate.getTime()-cdate.getTime();
            long days=diff/(24*60*60*1000);
            int daysdiff=(int) days;
            String stdays=String.valueOf(daysdiff);
           // String stdays=String.format("%02",days);
            holder.vehiclename.setText(v.getName());
            holder.remainng.setText("Licence will expire in "+stdays+" days");

        } catch (ParseException e) {
            e.printStackTrace();
        }

       // holder.vehiclename.setText(v.getRegistered_No());
    }





    @Override
    public int getItemCount() {
        return vehiclelist.size();
    }

    class vehicleviewholder extends RecyclerView.ViewHolder
    {

        public TextView vehiclename,remainng;


        public vehicleviewholder(View itemView) {
            super(itemView);
            vehiclename=itemView.findViewById(R.id.nameText);
            remainng=itemView.findViewById(R.id.remaingdays);
        }
    }
}
























































































































































































































































































































































