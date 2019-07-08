package com.example.issa.pdm_project_2018.Isuru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Model.Oil_Change_Records;
import com.example.issa.pdm_project_2018.Model.Other_Service_Records;
import com.example.issa.pdm_project_2018.R;
import com.example.issa.pdm_project_2018.ViewHolder.OilChangeViewHolder;
import com.example.issa.pdm_project_2018.ViewHolder.OtherServiceViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OtherServicesStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference otherservice;

    FirebaseRecyclerAdapter<Other_Service_Records,OtherServiceViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_other_services_status);

        //Firebase
        database = FirebaseDatabase.getInstance();
        otherservice = database.getReference("Other Service Records");

        recyclerView = (RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOtherservice(Common.currentUser.getPhone());
    }

    private void loadOtherservice(String phone) {
        adapter = new FirebaseRecyclerAdapter<Other_Service_Records, OtherServiceViewHolder>(
                Other_Service_Records.class,
                R.layout.otherservices,
                OtherServiceViewHolder.class,
                otherservice.orderByChild("phone")
                        .equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(OtherServiceViewHolder viewHolder, Other_Service_Records model, int position) {
                viewHolder.des.setText(model.getDescription());
                viewHolder.amount.setText(model.getAmount());
                viewHolder.location.setText(model.getLocation());
                viewHolder.time.setText(model.getTime());
                viewHolder.date.setText(model.getDate());
            }
        };
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
}
