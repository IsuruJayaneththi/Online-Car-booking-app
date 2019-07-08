package com.example.issa.pdm_project_2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Main_login extends AppCompatActivity implements View.OnClickListener{

    Button cus,dri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_login);


        cus = (Button)findViewById(R.id.cus);
        dri = (Button)findViewById(R.id.dri);

        cus.setOnClickListener(this);
        dri.setOnClickListener(this);

    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    @Override
    public void onClick(View v) {
        if (v == cus){
            startActivity(new Intent(this,Cus_main.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        if (v == dri){
            startActivity(new Intent(this,Dri_main.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}
