package com.example.issa.pdm_project_2018.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.Interface.ItemClickListener;
import com.example.issa.pdm_project_2018.R;

public class HireinfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView customername,amount,time,date,distance,duration;

    private ItemClickListener itemClickListener;

    public HireinfoViewHolder(View itemView) {
        super(itemView);

        customername = (TextView)itemView.findViewById(R.id.customername);
        amount = (TextView)itemView.findViewById(R.id.amount);
        time = (TextView)itemView.findViewById(R.id.time);
        date = (TextView)itemView.findViewById(R.id.date);
        distance = (TextView)itemView.findViewById(R.id.distance);
        duration = (TextView)itemView.findViewById(R.id.duration);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }


}
