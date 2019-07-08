package com.example.issa.pdm_project_2018.Manoj;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.R;

import java.util.ArrayList;

public class SearchAdapter  extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {


    Context contex;
    ArrayList<String> Names;
    ArrayList<String>Distances;
    ArrayList<String>Amounts;
    ArrayList<String>Economies;

    class SearchViewHolder extends RecyclerView.ViewHolder
    {
        TextView textName,textDistance,textFuel,textecon;
        public SearchViewHolder(View itemView) {
            super(itemView);
            textName=itemView.findViewById(R.id.nameText);
            textDistance=itemView.findViewById(R.id.disText);
            textFuel=itemView.findViewById(R.id.fuelamntText);
            textecon=itemView.findViewById(R.id.EconText);
        }
    }

    public SearchAdapter(Context contex, ArrayList<String> names, ArrayList<String> distances, ArrayList<String> amounts, ArrayList<String> economies) {
        this.contex = contex;
        Names = names;
        Distances = distances;
        Amounts = amounts;
        Economies = economies;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vie= LayoutInflater.from(contex).inflate(R.layout.content_main,parent,false);

        return  new SearchAdapter.SearchViewHolder(vie);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.textName.setText("Name :"+Names.get(position));
        holder.textDistance.setText("Distance:"+Distances.get(position));
        holder.textecon.setText("Fuel Economy:"+Economies.get(position));
        holder.textFuel.setText("Fuel Amount"+Amounts.get(position));

    }



    @Override
    public int getItemCount() {
        return Names.size();
    }
}
