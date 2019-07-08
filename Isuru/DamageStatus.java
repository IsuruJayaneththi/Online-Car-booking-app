package com.example.issa.pdm_project_2018.Isuru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Model.Damage_Records;
import com.example.issa.pdm_project_2018.Model.Oil_Change_Records;
import com.example.issa.pdm_project_2018.R;
import com.example.issa.pdm_project_2018.ViewHolder.DamageViewHolder;
import com.example.issa.pdm_project_2018.ViewHolder.OilChangeViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DamageStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference damage;

    FirebaseRecyclerAdapter<Damage_Records,DamageViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage_status);

        //Firebase
        database = FirebaseDatabase.getInstance();
        damage = database.getReference("Damage Records");

        recyclerView = (RecyclerView)findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loaddamage(Common.currentUser.getPhone());

    }

    private void loaddamage(String phone) {
        adapter = new FirebaseRecyclerAdapter<Damage_Records, DamageViewHolder>(
                Damage_Records.class,
                R.layout.damage_layout,
                DamageViewHolder.class,
                damage.orderByChild("phone")
                        .equalTo(phone)
        ) {
            @Override
            protected void populateViewHolder(DamageViewHolder viewHolder, Damage_Records model, int position) {
                viewHolder.dmglocation.setText(model.getDamageLocation());
                viewHolder.dmgdescription.setText(model.getDescription());
                viewHolder.dmgtime.setText(model.getTime());
                viewHolder.dmgdate.setText(model.getDate());
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
