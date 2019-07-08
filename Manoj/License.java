package com.example.issa.pdm_project_2018.Manoj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.issa.pdm_project_2018.Model.Cars;
import com.example.issa.pdm_project_2018.R;
import com.example.issa.pdm_project_2018.ViewHolder.VehicleAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class License extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DatabaseReference db;
    private VehicleAdapter va;
    private List<Cars> vehicleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);



        mRecyclerView=findViewById(R.id.recvi);


        /*
       mRecyclerView.findViewById(R.id.recvi);*/
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        vehicleList=new ArrayList<>();

        db= FirebaseDatabase.getInstance().getReference("Cars");


        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                vehicleList.clear();

                if(dataSnapshot.exists())
                {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                    {
                        Cars vehicle=postSnapshot.getValue(Cars.class);
                        vehicleList.add(vehicle);
                    }

                    va=new VehicleAdapter(License.this,vehicleList);
                    mRecyclerView.setAdapter(va);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


                Toast.makeText(License.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}

