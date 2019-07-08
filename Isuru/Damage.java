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
import com.example.issa.pdm_project_2018.Model.Damage_Records;
import com.example.issa.pdm_project_2018.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Date;

import es.dmoral.toasty.Toasty;

public class Damage extends AppCompatActivity {

    MaterialEditText edtdmglocation,edtdesacc,edtdmgtime,edtdmgdate;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_damage);


        edtdmglocation = (MaterialEditText)findViewById(R.id.edtdmglocation);
        edtdesacc = (MaterialEditText)findViewById(R.id.edtdesacc);
        edtdmgtime = (MaterialEditText)findViewById(R.id.edtdmgtime);
        edtdmgdate = (MaterialEditText)findViewById(R.id.edtdmgdate);

        btnSave = (Button)findViewById(R.id.btnSave);

        //Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_damage_records = database.getReference("Damage Records");


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Damage.this);
                mDialog.setMessage("Please waiting");
                mDialog.show();

                table_damage_records.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDialog.dismiss();
                        Damage_Records damage_records = new Damage_Records(edtdmglocation.getText().toString(),edtdesacc.getText().toString(),edtdmgtime.getText().toString(),edtdmgdate.getText().toString(), Common.currentUser.getPhone().toString(),Common.currentUser.getName().toString());
                        table_damage_records.child(String.valueOf(new Date())).setValue(damage_records);
                        Toasty.success(Damage.this,"Records Successfully Saved.....!!", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(Damage.this,Home.class);
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
