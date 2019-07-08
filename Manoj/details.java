package com.example.issa.pdm_project_2018.Manoj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.issa.pdm_project_2018.Model.Service;
import com.example.issa.pdm_project_2018.R;
import com.example.issa.pdm_project_2018.ViewHolder.MaintanancesearchAdapter;
import com.example.issa.pdm_project_2018.ViewHolder.ServiceAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class details extends AppCompatActivity implements ServiceAdapter.OnItemClickListner {

    private RecyclerView mRecyclerView;
    private DatabaseReference db;
    DatabaseReference dm;
    private ServiceAdapter sa;

    MaintanancesearchAdapter ms;
    MaterialSearchBar materialSearchBar;
    List<Service> serviceList;
    ArrayList<String> Names;
    ArrayList<String>works;
    ArrayList<String>dates;
    ArrayList<String>prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        mRecyclerView=findViewById(R.id.rv);


        /*
       mRecyclerView.findViewById(R.id.recvi);*/
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        materialSearchBar=(MaterialSearchBar)findViewById(R.id.searchby);
        dm= FirebaseDatabase.getInstance().getReference();
        serviceList=new ArrayList<>();

        Names=new ArrayList<>();
        works=new ArrayList<>();
        dates=new ArrayList<>();
        prices=new ArrayList<>();

        db= FirebaseDatabase.getInstance().getReference("Service");

        loadprogramme();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                serviceList.clear();

                if(dataSnapshot.exists())
                {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                    {
                        Service service=postSnapshot.getValue(Service.class);
                        service.setkey(postSnapshot.getKey());
                        serviceList.add(service);
                    }

                    sa=new ServiceAdapter(details.this,serviceList);
                    mRecyclerView.setAdapter(sa);
                    sa.setOnItemClickListner(details.this);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


                Toast.makeText(details.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void loadprogramme() {

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!editable.toString().isEmpty()) {
                    setAdapter(editable.toString());
                } else {

                   /* Names.clear();
                    works.clear();
                    dates.clear();
                    prices.clear();
                    mRecyclerView.removeAllViews();*/

                    sa=new ServiceAdapter(details.this,serviceList);
                    mRecyclerView.setAdapter(sa);
                }

            }
        });

    }


    private void setAdapter(final String searchedString) {

        Names.clear();
        works.clear();
        dates.clear();
        prices.clear();
        mRecyclerView.removeAllViews();
        dm.child("Service").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count=0;
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String uid=snapshot.getKey();
                    String nme=snapshot.child("vehicle_Name").getValue(String.class);
                    String jobs=snapshot.child("job").getValue(String.class);
                    String datess=snapshot.child("date").getValue(String.class);
                    String amout=snapshot.child("price").getValue(String.class);

                    if(nme.contains(searchedString))
                    {
                        Names.add(nme);
                        works.add(jobs);
                        dates.add(datess);
                        prices.add(amout);
                        count++;
                    }

                    if(count==15)
                        break;
                }
                ms=new MaintanancesearchAdapter(details.this,Names,works,dates,prices);
                mRecyclerView.setAdapter(ms);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onWhateverClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {


        Service selectedItem=serviceList.get(position);
        String selectedKey=selectedItem.getkey();
        db=FirebaseDatabase.getInstance().getReference("Service");
        db.child(selectedKey).removeValue();
        //Toast.makeText(details.this,"Record deleted",Toast.LENGTH_SHORT).show();

    }



/*
    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onWhateverClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

        Service selectedItem=serviceList.get(position);
        String selectedKey=selectedItem.getMkey();
        db=FirebaseDatabase.getInstance().getReference("Service");
        db.child(selectedKey).removeValue();

    }
    */

}

