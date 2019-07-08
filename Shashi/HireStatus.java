package com.example.issa.pdm_project_2018.Shashi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Model.Fill_Up_Records;
import com.example.issa.pdm_project_2018.Model.Hireinfo;
import com.example.issa.pdm_project_2018.R;
import com.example.issa.pdm_project_2018.ViewHolder.FillupViewHolder;
import com.example.issa.pdm_project_2018.ViewHolder.HireinfoViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HireStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference hireinfo;

    FirebaseRecyclerAdapter<Hireinfo,HireinfoViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hire_status);



        //Firebase
        database = FirebaseDatabase.getInstance();
        hireinfo = database.getReference("Hire Information Records");

        recyclerView = (RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadHireinfo(Common.currentUser.getPhone());
    }

    private void loadHireinfo(String phone) {
        adapter = new FirebaseRecyclerAdapter<Hireinfo, HireinfoViewHolder>(
                Hireinfo.class,
                R.layout.hireinfo_layout,
                HireinfoViewHolder.class,
                hireinfo.orderByChild("phone")
                        .equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(HireinfoViewHolder viewHolder, Hireinfo model, int position) {
                viewHolder.customername.setText(model.getCustomername());
                viewHolder.amount.setText(model.getHireamount());
                viewHolder.time.setText(model.getHiretime());
                viewHolder.date.setText(model.getHiredate());
                viewHolder.distance.setText(model.getHiredistance());
                viewHolder.duration.setText(model.getHireDuration());
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
