package com.example.issa.pdm_project_2018.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.R;

import java.util.ArrayList;

/**
 * Created by Lenovo™ on 5/1/2018.
 */

public  class MaintanancesearchAdapter extends RecyclerView.Adapter<MaintanancesearchAdapter.maintainSearchViewHolder> {
    Context contex;
    ArrayList<String> Names;
    ArrayList<String>works;
    ArrayList<String>dates;
    ArrayList<String>prices;

    @Override
    public maintainSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vie= LayoutInflater.from(contex).inflate(R.layout.detailscard,parent,false);

        return  new MaintanancesearchAdapter.maintainSearchViewHolder(vie);
    }

    @Override
    public void onBindViewHolder(maintainSearchViewHolder holder, int position) {

        holder.textName.setText(Names.get(position));
        holder. textDate.setText(dates.get(position));
        holder.textprice.setText(prices.get(position));
        holder.textjob.setText(works.get(position));
    }

    @Override
    public int getItemCount() {
        return Names.size();
    }


    class maintainSearchViewHolder extends RecyclerView.ViewHolder
    {
        TextView textName,textDate,textjob,textprice;
        public maintainSearchViewHolder(View itemView) {
            super(itemView);
            textName=itemView.findViewById(R.id.nameText);
            textDate=itemView.findViewById(R.id.dateText);
            textjob=itemView.findViewById(R.id.jobdone);
            textprice=itemView.findViewById(R.id.pricetxt);
        }
    }

    public MaintanancesearchAdapter(Context contex, ArrayList<String> names, ArrayList<String> works, ArrayList<String> dates, ArrayList<String> prices) {
        this.contex = contex;
        Names = names;
        this.works = works;
        this.dates = dates;
        this.prices = prices;
    }
}
