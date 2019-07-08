package com.example.issa.pdm_project_2018.Isuru;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Home;
import com.example.issa.pdm_project_2018.Model.Oil_Change_Records;
import com.example.issa.pdm_project_2018.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class OilChange extends AppCompatActivity {


    MaterialEditText edtOiltype,edtLocation,edtAmount,edtBillno,edtTime,edtDate;
    Button btnSave;
    private static final String TAG="OilChange";
    private DatePickerDialog.OnDateSetListener mDateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_oil_change);

        edtOiltype = (MaterialEditText)findViewById(R.id.edtOiltype);
        edtLocation = (MaterialEditText)findViewById(R.id.edtLocation);
        edtAmount = (MaterialEditText)findViewById(R.id.edtAmount);
        edtBillno = (MaterialEditText)findViewById(R.id.edtBillno);
        edtTime = (MaterialEditText)findViewById(R.id.edtTime);
        edtDate = (MaterialEditText)findViewById(R.id.edtDate);

        btnSave = (Button)findViewById(R.id.btnSave);

        //Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_oil_change_records = database.getReference("Oil Change Records");


        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog=new DatePickerDialog(OilChange.this,android.R.style.Theme_Holo_Light_Dialog,mDateListner,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateListner=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month=month+1;
                Log.d(TAG,"onDateSet:mm/dd/yyy"+month+"-"+day+"-"+year);
                String date=year+"."+month+"."+day;
                edtDate.setText(date);

            }
        };



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(OilChange.this);
                mDialog.setMessage("Please waiting");
                mDialog.show();

                table_oil_change_records.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDialog.dismiss();
                        Oil_Change_Records oil_change_records = new Oil_Change_Records(edtOiltype.getText().toString(),edtLocation.getText().toString(),edtAmount.getText().toString(),edtBillno.getText().toString(),edtTime.getText().toString(),edtDate.getText().toString(),Common.currentUser.getPhone().toString(),Common.currentUser.getName().toString(),"0");
                        table_oil_change_records.child(String.valueOf(new Date())).setValue(oil_change_records);
                        Toasty.success(OilChange.this,"Records Successfully Saved.....!!",Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(OilChange.this,Home.class);
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
