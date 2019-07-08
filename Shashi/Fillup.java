package com.example.issa.pdm_project_2018.Shashi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Home;
import com.example.issa.pdm_project_2018.Model.Fill_Up_Records;
import com.example.issa.pdm_project_2018.Model.Oil_Change_Records;
import com.example.issa.pdm_project_2018.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Date;

import es.dmoral.toasty.Toasty;

public class Fillup extends AppCompatActivity {

    MaterialEditText edtVehiclename,edtFilledlocation,edtStartmeter,edtEndmeter,edtCost,edtNoofliter;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fillup);

        edtVehiclename = (MaterialEditText)findViewById(R.id.edtVehiclename);
        edtFilledlocation = (MaterialEditText)findViewById(R.id.edtFilledlocation);
        edtStartmeter = (MaterialEditText)findViewById(R.id.edtStartmeter);
        edtEndmeter= (MaterialEditText)findViewById(R.id.edtEndmeter);
        edtCost = (MaterialEditText)findViewById(R.id.edtCost);
        edtNoofliter = (MaterialEditText)findViewById(R.id.edtNoofliter);

        btnSave = (Button)findViewById(R.id.btnSave);

        //Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_fill_up_records = database.getReference("Fill Up Records");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Fillup.this);
                mDialog.setMessage("Please waiting");
                mDialog.show();

                table_fill_up_records.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDialog.dismiss();
                        Fill_Up_Records fill_up_records = new Fill_Up_Records(edtVehiclename.getText().toString(),edtFilledlocation.getText().toString(),edtStartmeter.getText().toString(),edtEndmeter.getText().toString(),edtCost.getText().toString(),edtNoofliter.getText().toString(), Common.currentUser.getPhone().toString(),Common.currentUser.getName().toString(),"0");
                        table_fill_up_records.child(String.valueOf(new Date())).setValue(fill_up_records);
                        Toasty.success(Fillup.this,"Records Successfully Saved.....!!", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(Fillup.this,Home.class);
                        startActivity(homeIntent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
