package com.example.issa.pdm_project_2018.Shashi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Model.Fill_Up_Records;
import com.example.issa.pdm_project_2018.R;
import com.example.issa.pdm_project_2018.ViewHolder.FillupViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FillupStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference fillup;

    FirebaseRecyclerAdapter<Fill_Up_Records,FillupViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fillup_status);


        //Firebase
        database = FirebaseDatabase.getInstance();
        fillup = database.getReference("Fill Up Records");

        recyclerView = (RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent().getExtras() == null) {
            loadFillUp(Common.currentUser.getPhone());
        }else
        {
            loadFillUp(getIntent().getStringExtra("userPhone"));
        }
    }

    private void loadFillUp(String phone) {
        adapter = new FirebaseRecyclerAdapter<Fill_Up_Records, FillupViewHolder>(
                Fill_Up_Records.class,
                R.layout.fillup_layout,
                FillupViewHolder.class,
                fillup.orderByChild("phone")
                        .equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(FillupViewHolder viewHolder, Fill_Up_Records model, int position) {
                viewHolder.fill_id.setText(adapter.getRef(position).getKey());
                viewHolder.fill_status.setText(Common.convertCode1ToStatus(model.getStatus()));
                viewHolder.vehiclename.setText(model.getVehiclename());
                viewHolder.location.setText(model.getFilledlocation());
                viewHolder.startmeter.setText(model.getStartmeter());
                viewHolder.endmeter.setText(model.getEndmeter());
                viewHolder.cost.setText(model.getCost());
                viewHolder.noofliter.setText(model.getNoofliter());
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
