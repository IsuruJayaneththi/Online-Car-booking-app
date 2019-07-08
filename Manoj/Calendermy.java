package com.example.issa.pdm_project_2018.Manoj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.issa.pdm_project_2018.R;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Calendermy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendermy);


        Date today=new Date();
        Calendar nextyear=Calendar.getInstance();
        nextyear.add(Calendar.YEAR,1);

        CalendarPickerView datepicker=findViewById(R.id.calendarView);
        datepicker.init(today,nextyear.getTime()).withSelectedDate(today);



        datepicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                //String selecteddate= DateFormat.getDateInstance().format(date);
                // Toast.makeText(Calendermy.this,selecteddate,Toast.LENGTH_SHORT).show();
                SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                String selecteddate=dateFormat.format(date);
                Intent intent=new Intent(Calendermy.this,vehicle_maintanance.class);
                intent.putExtra("date",selecteddate);
                startActivity(intent);
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });


    }
}
