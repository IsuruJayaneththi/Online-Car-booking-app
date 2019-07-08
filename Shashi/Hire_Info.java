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
import com.example.issa.pdm_project_2018.Model.Hireinfo;
import com.example.issa.pdm_project_2018.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Date;

import es.dmoral.toasty.Toasty;

public class Hire_Info extends AppCompatActivity {


    MaterialEditText edtcusname,edtamount,edttime,edtdate,edtdistance,edtduration;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hire__info);


        edtcusname = (MaterialEditText)findViewById(R.id.edtcusname);
        edtamount = (MaterialEditText)findViewById(R.id.edtamount);
        edttime = (MaterialEditText)findViewById(R.id.edttime);
        edtdate= (MaterialEditText)findViewById(R.id.edtdate);
        edtdistance = (MaterialEditText)findViewById(R.id.edtdistance);
        edtduration = (MaterialEditText)findViewById(R.id.edtduration);

        btnSave = (Button)findViewById(R.id.btnSave);

        //Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_hireinfo_records = database.getReference("Hire Information Records");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Hire_Info.this);
                mDialog.setMessage("Please waiting");
                mDialog.show();

                table_hireinfo_records.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDialog.dismiss();
                        Hireinfo hireInfo_records = new Hireinfo(edtcusname.getText().toString(),edtamount.getText().toString(),edttime.getText().toString(),edtdate.getText().toString(),edtdistance.getText().toString(),edtduration.getText().toString(), Common.currentUser.getPhone().toString(),Common.currentUser.getName().toString());
                        table_hireinfo_records.child(String.valueOf(new Date())).setValue(hireInfo_records);
                        Toasty.success(Hire_Info.this,"Records Successfully Saved.....!!", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(Hire_Info.this,Home.class);
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
