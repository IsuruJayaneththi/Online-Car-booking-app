package com.example.issa.pdm_project_2018.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.Interface.ItemClickListener;
import com.example.issa.pdm_project_2018.R;

public class OilChangeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView oil_id,oil_status,oiltype,oilamount,oillocation,oiltime,oildate;

    private ItemClickListener itemClickListener;

    public OilChangeViewHolder(View itemView) {
        super(itemView);

        oil_id= (TextView)itemView.findViewById(R.id.oil_id);
        oil_status = (TextView)itemView.findViewById(R.id.oil_status);
        oiltype = (TextView)itemView.findViewById(R.id.oiltype);
        oilamount = (TextView)itemView.findViewById(R.id.oilamount);
        oillocation = (TextView)itemView.findViewById(R.id.oillocation);
        oiltime = (TextView)itemView.findViewById(R.id.oiltime);
        oildate = (TextView)itemView.findViewById(R.id.oildate);

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
