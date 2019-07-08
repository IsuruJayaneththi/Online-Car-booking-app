package com.example.issa.pdm_project_2018.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.Interface.ItemClickListener;
import com.example.issa.pdm_project_2018.R;

public class FillupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView fill_id,fill_status,vehiclename,location,startmeter,endmeter,cost,noofliter;

    private ItemClickListener itemClickListener;

    public FillupViewHolder(View itemView) {
        super(itemView);

        fill_id= (TextView)itemView.findViewById(R.id.fill_id);
        fill_status = (TextView)itemView.findViewById(R.id.fill_status);
        vehiclename = (TextView)itemView.findViewById(R.id.vehiclename);
        location = (TextView)itemView.findViewById(R.id.location);
        startmeter = (TextView)itemView.findViewById(R.id.startmeter);
        endmeter = (TextView)itemView.findViewById(R.id.endmeter);
        cost = (TextView)itemView.findViewById(R.id.cost);
        noofliter = (TextView)itemView.findViewById(R.id.noofliter);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
