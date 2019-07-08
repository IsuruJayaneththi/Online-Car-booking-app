package com.example.issa.pdm_project_2018.Manoj;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.R;

public class MainActivity  extends AppCompatActivity implements tab1.OnFragmentInteractionListener,Tab3.OnFragmentInteractionListener{

    private Button calbtn;
    private EditText odometer1;
    private EditText odometer2;
    private EditText fuelamount;
    private TextView ans;
    private TabLayout tblout;
    private Toolbar tlbar;
    int resut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // setContentView(R.layout.fragment_tab1);

        tlbar=findViewById(R.id.toolbar);
        setSupportActionBar(tlbar);

        calbtn =  findViewById(R.id.CalculateBtn);
        odometer1 =  findViewById(R.id.odo1);
        odometer2 = findViewById(R.id.odo2);
        fuelamount =  findViewById(R.id.fuel);
        tblout = findViewById(R.id.tablayout);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);


        tblout.addTab(tblout.newTab().setText("Calculate"));
        tblout.addTab(tblout.newTab().setText("Records"));


        tblout.setTabGravity(TabLayout.GRAVITY_FILL);


        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tblout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tblout));

/*

       calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                docoding();

            }
        });*/



  /*  calbtn.setOnClickListener(new View.OnClickListener() {

       final int odoval1= Integer.parseInt(odometer1.getText().toString());
      final   int odoval2=Integer.parseInt(odometer2.getText().toString());
        final int fuelamnt=Integer.parseInt(fuelamount.getText().toString());
          @Override
          public void onClick(View view) {
            //  docoding();




              ans=findViewById(R.id.Ans);

              resut=(odoval2-odoval1)/fuelamnt;
              // String stringresult=Integer.toString(resut);
              Toast.makeText(MainActivity.this,resut,Toast.LENGTH_LONG).show();
          }


      });

*/
        tblout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
