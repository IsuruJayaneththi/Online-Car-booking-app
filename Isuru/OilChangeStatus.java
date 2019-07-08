package com.example.issa.pdm_project_2018.Isuru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Model.Oil_Change_Records;
import com.example.issa.pdm_project_2018.R;
import com.example.issa.pdm_project_2018.ViewHolder.OilChangeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OilChangeStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference oilchange;

    FirebaseRecyclerAdapter<Oil_Change_Records,OilChangeViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_oil_change_status);


        //Firebase
        database = FirebaseDatabase.getInstance();
        oilchange = database.getReference("Oil Change Records");

        recyclerView = (RecyclerView) findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent().getExtras() == null) {
            loadOilchange(Common.currentUser.getPhone());
        } else {
            loadOilchange(getIntent().getStringExtra("userPhone"));
        }
    }


    private void loadOilchange(String phone) {
        adapter = new FirebaseRecyclerAdapter<Oil_Change_Records, OilChangeViewHolder>(
                Oil_Change_Records.class,
                R.layout.oilchange_layout,
                OilChangeViewHolder.class,
                oilchange.orderByChild("phone")
                        .equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(OilChangeViewHolder viewHolder, Oil_Change_Records model, int position) {
                viewHolder.oil_id.setText(adapter.getRef(position).getKey());
                viewHolder.oil_status.setText(Common.convertCode1ToStatus(model.getStatus()));
                viewHolder.oiltype.setText(model.getOiltype());
                viewHolder.oilamount.setText(model.getAmount());
                viewHolder.oillocation.setText(model.getLocation());
                viewHolder.oiltime.setText(model.getTime());
                viewHolder.oildate.setText(model.getDate());
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
