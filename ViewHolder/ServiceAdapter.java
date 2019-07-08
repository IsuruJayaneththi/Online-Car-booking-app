package com.example.issa.pdm_project_2018.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.Model.Service;
import com.example.issa.pdm_project_2018.R;

import java.util.List;

/**
 * Created by Lenovo™ on 5/1/2018.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.Serviceviewholder> {


    Context ctex;
    List<Service> Serviceelist;
    private OnItemClickListner mListner;

    public ServiceAdapter(Context ctex, List<Service> serviceelist) {
        this.ctex = ctex;
        Serviceelist = serviceelist;
    }

    @Override
    public Serviceviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vi= LayoutInflater.from(ctex).inflate(R.layout.detailscard,parent,false);
        return  new Serviceviewholder(vi);
    }

    @Override
    public void onBindViewHolder(Serviceviewholder holder, int position) {

        Service s=Serviceelist.get(position);
        holder.vehiclename.setText(s.getVehicle_Name());
        holder.Date.setText(s.getDate());
        holder.job.setText(s.getJob());
        holder.price.setText(s.getPrice());


    }

    @Override
    public int getItemCount() {
        return Serviceelist.size();
    }

    class Serviceviewholder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener
    {

        public TextView vehiclename,job,Date,price;


        public Serviceviewholder(View itemView) {
            super(itemView);
            vehiclename=itemView.findViewById(R.id.nameText);
            job=itemView.findViewById(R.id.jobdone);
            Date=itemView.findViewById(R.id.dateText);
            price=itemView.findViewById(R.id.pricetxt);

            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            if(mListner!=null)
            {
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION)
                {
                    switch (menuItem.getItemId())
                    {
                        case 1:
                            mListner.onDeleteClick(position);
                            return  true;
                    }
                }
            }
            return false;
        }

        @Override
        public void onClick(View view) {

            if(mListner != null)
            {
                int position=getAdapterPosition();
                if (position!= RecyclerView.NO_POSITION)
                {
                    mListner.onItemClick(position);

                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

            contextMenu.setHeaderTitle("---Select Option---");
           // MenuItem dowhatever=contextMenu.add(Menu.NONE,1,1, "Do Whatever");
            MenuItem delete=contextMenu.add(Menu.NONE,1,1, "Delete");

           // dowhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
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
