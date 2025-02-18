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

import com.example.issa.pdm_project_2018.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import es.dmoral.toasty.Toasty;

public class Cus_SignUp extends AppCompatActivity {

    MaterialEditText edtPhone,edtName,edtPassword,edtEmail,edtAge,edtSecureCode;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_cus__sign_up);

        edtName = (MaterialEditText)findViewById(R.id.edtName);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        edtEmail = (MaterialEditText)findViewById(R.id.edtEmail);
        edtAge = (MaterialEditText)findViewById(R.id.edtAge);
        edtSecureCode = (MaterialEditText)findViewById(R.id.edtSecureCode);

        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        //Init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Cus_SignUp.this);
                mDialog.setMessage("Please waiting");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Check if already user phone
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toasty.success(Cus_SignUp.this, "Please Log In", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            mDialog.dismiss();
                            User user = new User(edtPassword.getText().toString(),edtAge.getText().toString(),edtEmail.getText().toString(),edtName.getText().toString(),edtSecureCode.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toasty.success(Cus_SignUp.this,"Sign up successfully  !",Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(Cus_SignUp.this,Cus_main.class);
                            startActivity(homeIntent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        }
                        Intent homeIntent = new Intent(Cus_SignUp.this,Cus_main.class);
                        startActivity(homeIntent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
