package com.example.issa.pdm_project_2018.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.Interface.ItemClickListener;
import com.example.issa.pdm_project_2018.R;

public class OtherServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView des,amount,location,time,date;

    private ItemClickListener itemClickListener;

    public OtherServiceViewHolder(View itemView) {
        super(itemView);

        des = (TextView)itemView.findViewById(R.id.des);
        amount = (TextView)itemView.findViewById(R.id.amount);
        location = (TextView)itemView.findViewById(R.id.location);
        time = (TextView)itemView.findViewById(R.id.time);
        date = (TextView)itemView.findViewById(R.id.date);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
