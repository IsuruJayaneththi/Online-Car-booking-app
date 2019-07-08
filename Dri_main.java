package com.example.issa.pdm_project_2018;

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
import com.example.issa.pdm_project_2018.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;
import io.paperdb.Paper;

public class Dri_main extends AppCompatActivity implements View.OnClickListener{

    Button btnSignIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dri_main);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        //Init Paper
        Paper.init(this);
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
    @Override
    public void onClick(View v) {
        if (v == btnSignIn){
            startActivity(new Intent(this,Dri_SignIn.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        if (v == btnSignUp){
            startActivity(new Intent(this,Dri_SignUp.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        String user = Paper.book().read(Common.USER_KEY);
        String pwd = Paper.book().read(Common.PWD_KEY);
        if (user  !=null && pwd  !=null)
        {
            if (!user.isEmpty() && !pwd.isEmpty())
                login(user,pwd);

        }
    }

    private void login(final String phone, final String pwd) {
        //Just copy login code from SignIn.class

        //Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        final ProgressDialog mDialog = new ProgressDialog(Dri_main.this);
        mDialog.setMessage("Please waiting");
        mDialog.show();

        table_user.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Check if user not exist in database
                if (dataSnapshot.child(phone).exists()) {

                    //Get User Information
                    mDialog.dismiss();
                    User user = dataSnapshot.child(phone).getValue(User.class);
                    user.setPhone(phone); //set phone
                    if (user.getPassword().equals(pwd))
                    {
                        Intent homeIntent = new Intent(Dri_main.this,Home.class);
                        Common.currentUser = user;
                        startActivity(homeIntent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                    else
                    {
                        Toasty.error(Dri_main.this, "Wrong Password..!!!!", Toast.LENGTH_SHORT).show();

                    }

                }
                else{
                    mDialog.dismiss();
                    Toasty.warning(Dri_main.this, "Not a Registered Customer..!!!!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        }


}
