package com.example.issa.pdm_project_2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.issa.pdm_project_2018.Chamara.Booking;
import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Isuru.DamageStatus;
import com.example.issa.pdm_project_2018.Isuru.OilChangeStatus;
import com.example.issa.pdm_project_2018.Isuru.OtherServicesStatus;
import com.example.issa.pdm_project_2018.Manoj.License;
import com.example.issa.pdm_project_2018.Service.ListenFill;
import com.example.issa.pdm_project_2018.Service.ListenOil;
import com.example.issa.pdm_project_2018.Service.ListenOrder;
import com.example.issa.pdm_project_2018.Shashi.FillupStatus;
import com.example.issa.pdm_project_2018.Shashi.HireStatus;

import io.paperdb.Paper;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    TextView txtFullName;

    private CardView  booking, oilchange,damagerecords,about,fillup,hireinformation,otherservice,maintenance,fueleconomy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // defining cards
        booking = (CardView) findViewById(R.id.booking);
        oilchange = (CardView) findViewById(R.id.oilchange);
        damagerecords = (CardView) findViewById(R.id.damagerecords);
        about = (CardView) findViewById(R.id.about);
        fillup = (CardView) findViewById(R.id.fillup);
        hireinformation = (CardView) findViewById(R.id.hireinformation);
        otherservice = (CardView) findViewById(R.id.otherservice);
        maintenance = (CardView) findViewById(R.id.maintenance);
        fueleconomy = (CardView) findViewById(R.id.fueleconomy);


        // Add Click listener to the cards
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent booking= new Intent(Home.this, com.example.issa.pdm_project_2018.Chamara.Booking.class);
                startActivity(booking);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        fueleconomy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fueleconomy = new Intent(Home.this, com.example.issa.pdm_project_2018.Manoj.MainActivity.class);
                startActivity(fueleconomy);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        oilchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent oilchange= new Intent(Home.this, com.example.issa.pdm_project_2018.Isuru.OilChange.class);
                startActivity(oilchange);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        damagerecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent damage= new Intent(Home.this, com.example.issa.pdm_project_2018.Isuru.Damage.class);
                startActivity(damage);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent damage= new Intent(Home.this, com.example.issa.pdm_project_2018.About.class);
                startActivity(damage);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        fillup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent damage= new Intent(Home.this, com.example.issa.pdm_project_2018.Shashi.Fillup.class);
                startActivity(damage);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        hireinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent damage= new Intent(Home.this, com.example.issa.pdm_project_2018.Shashi.Hire_Info.class);
                startActivity(damage);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        otherservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherservice= new Intent(Home.this, com.example.issa.pdm_project_2018.Isuru.OtherServices.class);
                startActivity(otherservice);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maintenance= new Intent(Home.this, com.example.issa.pdm_project_2018.Manoj.vehicle_maintanance.class);
                startActivity(maintenance);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        Paper.init(this);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Set Name for user
        View headerView = navigationView.getHeaderView(0);
        txtFullName = (TextView)headerView.findViewById(R.id.txtFullName);
        txtFullName.setText(Common.currentUser.getName());


        //Register Service
        Intent service_order = new Intent(Home.this, ListenOrder.class);
        startService(service_order);

        Intent service_oil = new Intent(Home.this, ListenOil.class);
        startService(service_oil);

        Intent service_fill = new Intent(Home.this, ListenFill.class);
        startService(service_fill);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_orders) {
            Intent orderIntent = new Intent(Home.this,OrderStatus.class);
            startActivity(orderIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else if (id == R.id.nav_oilchange) {
            Intent oilIntent = new Intent(Home.this,OilChangeStatus.class);
            startActivity(oilIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else if (id == R.id.nav_damage) {
            Intent damageIntent = new Intent(Home.this,DamageStatus.class);
            startActivity(damageIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else if (id == R.id.nav_hireinformation) {
            Intent hireinfo = new Intent(Home.this,HireStatus.class);
            startActivity(hireinfo);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else if (id == R.id.nav_otherservice) {
            Intent otherser = new Intent(Home.this,OtherServicesStatus.class);
            startActivity(otherser);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else if (id == R.id.nav_renewaldatereminder) {
            Intent licenseIntent = new Intent(Home.this,License.class);
            startActivity(licenseIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else if (id == R.id.nav_filluprecords) {
            Intent fillIntent = new Intent(Home.this,FillupStatus.class);
            startActivity(fillIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else if (id == R.id.nav_log_out) {

            //Delete Remember user & password
            Paper.book().destroy();

            //Logout
            Intent signIn = new Intent(Home.this,Main_login.class);
            signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(signIn);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
