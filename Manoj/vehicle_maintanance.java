package com.example.issa.pdm_project_2018.Manoj;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.issa.pdm_project_2018.Model.Dbhelper;
import com.example.issa.pdm_project_2018.Model.Service;
import com.example.issa.pdm_project_2018.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class vehicle_maintanance extends AppCompatActivity {

    public TextView batry,paint,airfilter,tire,plug;
    public TextView brakes;
    public EditText jobname,dateselect,price;
    String [] SPINNELIST={"Toyota","Audi","Nissan"};
    DatabaseReference db;
    DatabaseReference dd;
    Dbhelper hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_maintanance);


        batry=(TextView)findViewById(R.id.Batery);
        brakes=(TextView)findViewById(R.id.Brake);
        jobname=(EditText)findViewById(R.id.jobtext);
        price=(EditText)findViewById(R.id.pricetxt);
        dateselect=(EditText)findViewById(R.id.datetxt);
        paint=(TextView) findViewById(R.id.Paint);
        airfilter=(TextView) findViewById(R.id.Airfil);
        tire=(TextView) findViewById(R.id.Tire);
        plug=(TextView)findViewById(R.id.plugs) ;
        TextView selectDate=(TextView)findViewById(R.id.datetxt);
        Button savebtn=(Button)findViewById(R.id.add);
        Button addbtn=(Button)findViewById(R.id.fab);
        final Button dateselector=(Button)findViewById(R.id.datebtn);
        final Spinner ms=(Spinner)findViewById(R.id.spi2);
        final List<String> vehiname=new ArrayList<String>();
        /*
        ArrayAdapter<String> arradapt=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,SPINNELIST);
        ms.setAdapter(arradapt);
                 */
        db= FirebaseDatabase.getInstance().getReference();
        dd= FirebaseDatabase.getInstance().getReference("Cars");
        hp=new Dbhelper(db);

        Intent incoming=getIntent();
        final String date=incoming.getStringExtra("date");
        selectDate.setText(date);



        dd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    vehiname.add(postSnapshot.child("name").getValue().toString());
                }
                ArrayAdapter<String> myadapter=new ArrayAdapter<String>(vehicle_maintanance.this,android.R.layout.simple_spinner_item,vehiname);
                ms.setPrompt("Select the Vehicle");
                ms.setAdapter(myadapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(vehicle_maintanance.this,details.class);
                startActivity(intent);
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(price.getText().toString().isEmpty() || dateselect.getText().toString().isEmpty() || jobname.getText().toString().isEmpty())
                {
                    Toast.makeText(vehicle_maintanance.this,"Some fields are Empty",Toast.LENGTH_SHORT).show();
                }

                else {
                    String vehiName = ms.getSelectedItem().toString();
                    String serviceprice = price.getText().toString();
                    String date = dateselect.getText().toString();
                    String job = jobname.getText().toString();


                    Service s = new Service();
                    s.setVehicle_Name(vehiName);
                    s.setPrice(serviceprice);
                    s.setDate(date);
                    s.setJob(job);
                    if (serviceprice != null && serviceprice.length() > 0) {
                        if (hp.save(s)) {

                            Toast.makeText(vehicle_maintanance.this, "Service Record Added", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
        });
        dateselector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(vehicle_maintanance.this,Calendermy.class);
                startActivity(intent);
            }
        });


        batry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pretext=jobname.getText().toString();
                jobname.setText(pretext+","+"New Batery");
            }
        });

        brakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pretext=jobname.getText().toString();
                jobname.setText(pretext+","+"Brakes");
            }
        });

        plug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pretext=jobname.getText().toString();
                jobname.setText(pretext+","+"BrakesNew spark plugs");
            }
        });

        airfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pretext=jobname.getText().toString();
                jobname.setText(pretext+","+"Change Air filter");
            }
        });

        tire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pretext=jobname.getText().toString();
                jobname.setText(pretext+","+"New tire");
            }
        });



        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pretext=jobname.getText().toString();
                jobname.setText(pretext+","+"paint");
            }
        });



    }


    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

}
