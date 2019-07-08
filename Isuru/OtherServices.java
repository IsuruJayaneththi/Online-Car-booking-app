package com.example.issa.pdm_project_2018.Isuru;

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
import com.example.issa.pdm_project_2018.Model.Oil_Change_Records;
import com.example.issa.pdm_project_2018.Model.Other_Service_Records;
import com.example.issa.pdm_project_2018.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Date;

import es.dmoral.toasty.Toasty;

public class OtherServices extends AppCompatActivity {

    MaterialEditText edtDescrip,edtLocation,edtAmount,edtBillno,edtTime,edtDate;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_other_services);

        edtDescrip = (MaterialEditText)findViewById(R.id.edtDescrip);
        edtLocation = (MaterialEditText)findViewById(R.id.edtLocation);
        edtAmount = (MaterialEditText)findViewById(R.id.edtAmount);
        edtBillno = (MaterialEditText)findViewById(R.id.edtBillno);
        edtTime = (MaterialEditText)findViewById(R.id.edtTime);
        edtDate = (MaterialEditText)findViewById(R.id.edtDate);

        btnSave = (Button)findViewById(R.id.btnSave);

        //Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_otherservice_records = database.getReference("Other Service Records");


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(OtherServices.this);
                mDialog.setMessage("Please waiting");
                mDialog.show();

                table_otherservice_records.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDialog.dismiss();
                        Other_Service_Records otherServiceRecords = new Other_Service_Records(edtDescrip.getText().toString(),edtLocation.getText().toString(),edtAmount.getText().toString(),edtBillno.getText().toString(),edtTime.getText().toString(),edtDate.getText().toString(), Common.currentUser.getPhone().toString(),Common.currentUser.getName().toString());
                        table_otherservice_records.child(String.valueOf(new Date())).setValue(otherServiceRecords);
                        Toasty.success(OtherServices.this,"Records Successfully Saved.....!!", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(OtherServices.this,Home.class);
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
