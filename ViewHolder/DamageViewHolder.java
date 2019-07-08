package com.example.issa.pdm_project_2018.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.Interface.ItemClickListener;
import com.example.issa.pdm_project_2018.R;

public class DamageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView dmglocation,dmgdescription,dmgtime,dmgdate;

    private ItemClickListener itemClickListener;

    public DamageViewHolder(View itemView) {

        super(itemView);

        dmglocation = (TextView)itemView.findViewById(R.id.dmglocation);
        dmgdescription = (TextView)itemView.findViewById(R.id.dmgdescription);
        dmgtime = (TextView)itemView.findViewById(R.id.dmgtime);
        dmgdate = (TextView)itemView.findViewById(R.id.dmgdate);

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
