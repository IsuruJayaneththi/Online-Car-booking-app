package com.example.issa.pdm_project_2018.Manoj;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.Model.Fuel;
import com.example.issa.pdm_project_2018.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomerViewHolder> {

    Context mtex;
    List<Fuel> fuelList;
    private OnItemClickListner mListner;


    public CustomAdapter(Context context, List<Fuel> fuelList) {
        this.mtex = context;
        this.fuelList = fuelList;

    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View vie= LayoutInflater.from(mtex).inflate(R.layout.content_main,parent,false);
        CustomerViewHolder customerViewHolder=new CustomerViewHolder(vie);
        return  customerViewHolder;

    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, int position) {
        Fuel f=fuelList.get(position);
        holder.textName.setText("Name :"+f.getName());
        holder.textecon.setText("Fuel Economy:"+f.getEconomy());
        holder.textFuel.setText("Fuel Amount"+f.getFuel_Amount());
        holder.textDistance.setText("Distance:"+f.getDistance());


    }

    @Override
    public int getItemCount() {
        return fuelList.size();
    }





    class CustomerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener
    {
        TextView textName,textDistance,textFuel,textecon;




        public CustomerViewHolder(View itemView) {
            super(itemView);
            textName=itemView.findViewById(R.id.nameText);
            textDistance=itemView.findViewById(R.id.disText);
            textFuel=itemView.findViewById(R.id.fuelamntText);
            textecon=itemView.findViewById(R.id.EconText);

            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);


        }




        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("---Select Option---");
            MenuItem delete=contextMenu.add(Menu.NONE,1,1,  common.DELETE);
            // MenuItem delete=contextMenu.add(Menu.NONE,2,2, common.DELETE);

            //dowhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(mListner != null)
            {
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION)
                {
                    mListner.onItemClick(position);

                }
            }
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mListner != null)
            {
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION)
                {
                    switch (item.getItemId())
                    {
                        case 1:
                            mListner.onDeleteClick(position);
                            return true;
                        /* case  2:
                             mListner.onDeleteClick(position);
                             return true;*/


                    }

                }
            }
            return false;
        }
    }


    public interface OnItemClickListner
    {

        void onItemClick(int position);
        void onWhateverClick(int position);
        void onDeleteClick(int position);

    }

    public void setOnItemClickListner(OnItemClickListner listner)
    {
        mListner=listner;
    }


}
