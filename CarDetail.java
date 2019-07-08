package com.example.issa.pdm_project_2018;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Database.Database;
import com.example.issa.pdm_project_2018.Model.Car;
import com.example.issa.pdm_project_2018.Model.Order;
import com.example.issa.pdm_project_2018.Model.Rating;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.Arrays;

import es.dmoral.toasty.Toasty;

public class CarDetail extends AppCompatActivity implements RatingDialogListener{

    TextView car_name,car_price,car_description;
    ImageView car_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart,btnRating;
    ElegantNumberButton numberButton;
    RatingBar ratingBar;

    String carId="";

    FirebaseDatabase database;
    DatabaseReference cars;
    DatabaseReference ratingTbl;

    Car currentCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        //Firebase
        database = FirebaseDatabase.getInstance();
        cars = database.getReference("Cars");
        ratingTbl = database.getReference("Rating");


        //Init View
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCart = (FloatingActionButton)findViewById(R.id.btnCart);
        btnRating =(FloatingActionButton) findViewById(R.id.btn_rating);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);


        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRatingDialog();
            }
        });


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        carId,
                        currentCar.getName(),
                        numberButton.getNumber(),
                        currentCar.getPrice(),
                        currentCar.getDiscount()
                ));

                Toasty.success(CarDetail.this,"Added to Cart",Toast.LENGTH_SHORT).show();
            }
        });

        car_description = (TextView)findViewById(R.id.car_description);
        car_name = (TextView)findViewById(R.id.car_name);
        car_price = (TextView)findViewById(R.id.car_price);
        car_image = (ImageView)findViewById(R.id.img_car);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //Get Food Id from Intent
        if (getIntent() !=null)
            carId = getIntent().getStringExtra("CarId");
        if (!carId.isEmpty())
        {
            getDetailCar(carId);
            getRatingCar(carId);
        }
    }

    private void getRatingCar(String carId) {

        com.google.firebase.database.Query carRating = ratingTbl.orderByChild("carId").equalTo(carId);

        carRating.addValueEventListener(new ValueEventListener() {
            int count=0,sum=0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    Rating item = postSnapshot.getValue(Rating.class);
                    sum+=Integer.parseInt(item.getRateValue());
                    count++;
                }
                if (count !=0)
                {
                    float average = sum/count;
                    ratingBar.setRating(average);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showRatingDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNoteDescriptions(Arrays.asList("Very Bad","Not Good","Quite Ok","Very Good","Excellent"))
                .setDefaultRating(1)
                .setTitle("Rate this Car")
                .setDescription("Please select some stars and give your feedback..!!")
                .setTitleTextColor(R.color.colorPrimary)
                .setDescriptionTextColor(R.color.colorPrimary)
                .setHint("Please write your comment here")
                .setHintTextColor(R.color.LightGrey)
                .setCommentTextColor(R.color.White)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.RatingDialogFadeAnim)
                .create(CarDetail.this)
                .show();
    }


    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    private void getDetailCar(String foodId) {
        cars.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentCar = dataSnapshot.getValue(Car.class);

                //set Image
                Picasso.with(getBaseContext()).load(currentCar.getImage())
                        .into(car_image);
                collapsingToolbarLayout.setTitle(currentCar.getName());
                car_price.setText(currentCar.getPrice());
                car_name.setText(currentCar.getName());
                car_description.setText(currentCar.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onPositiveButtonClicked(int value, String comments) {
        //Get Rating and upload to firebase
        final Rating rating = new Rating(Common.currentUser.getPhone(),
                carId,
                String.valueOf(value),
                comments);
        ratingTbl.child(Common.currentUser.getPhone()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(Common.currentUser.getPhone()).exists())
                {
                    //Remove old value(you can delete or let it be - useless function :D)
                    ratingTbl.child(Common.currentUser.getPhone()).removeValue();
                    //Update new value
                    ratingTbl.child(Common.currentUser.getPhone()).setValue(rating);
                }
                else
                {
                    //Update new value
                    ratingTbl.child(Common.currentUser.getPhone()).setValue(rating);
                }
                Toasty.success(CarDetail.this,"Thank you for submit rating !!!",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onNegativeButtonClicked() {

    }
}
