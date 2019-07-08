package com.example.issa.pdm_project_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.issa.pdm_project_2018.Interface.ItemClickListener;
import com.example.issa.pdm_project_2018.Model.Car;
import com.example.issa.pdm_project_2018.ViewHolder.CarViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class CarList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference carList;

    String categoryId="";

    FirebaseRecyclerAdapter<Car,CarViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);


        //firebase
        database = FirebaseDatabase.getInstance();
        carList =  database.getReference("Cars");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_car);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Get Intent here
        if (getIntent() !=null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if(!categoryId.isEmpty()  && categoryId  !=null)
        {
            loadListCar(categoryId);
        }
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }


    private void loadListCar(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Car, CarViewHolder>(Car.class,
                R.layout.car_item,
                CarViewHolder.class,
                carList.orderByChild("menuId").equalTo(categoryId) // like : Select *from Foods where MenuId =
        ) {
            @Override
            protected void populateViewHolder(CarViewHolder viewHolder, Car model, int position) {
                viewHolder.car_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.car_image);

                final Car local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Start new Activity
                        Intent carDetail = new Intent(CarList.this,CarDetail.class);
                        carDetail.putExtra("CarId",adapter.getRef(position).getKey());//Send Food Id to new activity
                        startActivity(carDetail);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
