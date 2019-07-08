package com.example.issa.pdm_project_2018.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExpsearchAdapter extends RecyclerView.Adapter<ExpsearchAdapter.Expsearchviewholder> {

    Context ctex;
    ArrayList<String> Names;
    ArrayList<String> dates;


    public ExpsearchAdapter(Context ctex, ArrayList<String> names, ArrayList<String> dates) {
        this.ctex = ctex;
        Names = names;
        this.dates = dates;
    }

    @Override
    public Expsearchviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi= LayoutInflater.from(ctex).inflate(R.layout.license_layout,parent,false);
        return  new ExpsearchAdapter.Expsearchviewholder(vi);
    }

    @Override
    public void onBindViewHolder(Expsearchviewholder holder, int position) {


        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fdate=dateFormat.parse(dates.get(position));
            Date cdate=new Date();
            long diff=fdate.getTime()-cdate.getTime();
            long days=diff/(24*60*60*1000);
            int daysdiff=(int) days;
            String stdays=String.valueOf(daysdiff);
            // String stdays=String.format("%02",days);
            if(daysdiff>0) {
                holder.vehiclename.setText(Names.get(position));
                holder.remainng.setText("Licence will expire in " + stdays + " days");
            }

            else
            {
                holder.vehiclename.setText(Names.get(position));
                holder.remainng.setText("Licence has Already Expired ");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return Names.size();
    }

    class Expsearchviewholder extends RecyclerView.ViewHolder
    {

        public TextView vehiclename,remainng;


        public Expsearchviewholder(View itemView) {
            super(itemView);
            vehiclename=itemView.findViewById(R.id.nameText);
            remainng=itemView.findViewById(R.id.remaingdays);
        }
    }


}