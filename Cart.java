package com.example.issa.pdm_project_2018;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.issa.pdm_project_2018.Chamara.Booking;
import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Common.Config;
import com.example.issa.pdm_project_2018.Database.Database;
import com.example.issa.pdm_project_2018.Model.Order;
import com.example.issa.pdm_project_2018.Model.Request;
import com.example.issa.pdm_project_2018.Service.ListenOrder;
import com.example.issa.pdm_project_2018.ViewHolder.CartAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import info.hoang8f.widget.FButton;

public class Cart extends AppCompatActivity {

    private static final int PAYPAL_REQUEST_CODE = 9999;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    TextView txtTotalPrice;
    FButton btnPlace;

    List<Order> cart = new ArrayList<>();

    CartAdapter adapter;

    //Paypal payment
    static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX) // use Sandbox because we test , change it late if you going to production
            .clientId(Config.PAYPAL_CLIENT_ID);
    String address,date,time; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Init paypal
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);


        //Firebase
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        //Init
        recyclerView = (RecyclerView)findViewById(R.id.listCart);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        txtTotalPrice = (TextView)findViewById(R.id.total);
        btnPlace = (FButton)findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cart.size()>0)
                    showAlertDialog();
                else
                    Toasty.error(Cart.this,"Your cart is empty !!!",Toast.LENGTH_SHORT).show();
            }
        });

        loadListCar();
        //Register Service
        Intent service = new Intent(Cart.this, ListenOrder.class);
        startService(service);
    }
    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Cart.this);
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter your Address / Date / Time");

        LayoutInflater inflater = this.getLayoutInflater();
        View order_address_time_date = inflater.inflate(R.layout.order_address_time_date,null);

        final MaterialEditText edtAddress = (MaterialEditText)order_address_time_date.findViewById(R.id.edtAddress);
        final MaterialEditText edtDate = (MaterialEditText)order_address_time_date.findViewById(R.id.edtDate);
        final MaterialEditText edtTime = (MaterialEditText)order_address_time_date.findViewById(R.id.edtTime);

        alertDialog.setView(order_address_time_date);
        alertDialog.setIcon(R.drawable.ic_shopping_cart_black_24dp);

        alertDialog.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Show Paypal to payment
                //first , get Address,date and time from Alert Dialog
                address = edtAddress.getText().toString();
                date = edtDate.getText().toString();
                time = edtTime.getText().toString();

                String formatAmount = txtTotalPrice.getText().toString()
                                             .replace("$","")
                                             .replace(",","");
                PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(formatAmount),
                        "USD",
                        "Vehicle Order",
                        PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
                startActivityForResult(intent,PAYPAL_REQUEST_CODE);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

    //Press Ctrl+o


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PAYPAL_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation !=null)
                {
                    try{
                        String paymentDetail = confirmation.toJSONObject().toString(4);
                        JSONObject jsonObject = new JSONObject(paymentDetail);

                //Create new Request
                Request request = new Request(
                        Common.currentUser.getPhone(),
                        Common.currentUser.getName(),
                        address,
                        txtTotalPrice.getText().toString(),
                        "0",//status
                        date,
                        time,
                        jsonObject.getJSONObject("response").getString("state"), // state from JSON
                        cart
                );

                //submit to firebase
                //We will using new Date to key
                requests.child(String.valueOf(new Date()))
                        .setValue(request);


                //Delete cart
                new Database(getBaseContext()).cleanCart();
                Toasty.success(Cart.this,"Order Place Thank You!!",Toast.LENGTH_SHORT).show();
                finish();

                    }
                    catch (JSONException e){
                        e.printStackTrace();

                    }
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED)
                Toasty.error(this,"Payment Cancel",Toast.LENGTH_SHORT).show();
            else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
                Toasty.error(this,"Invalid Payment",Toast.LENGTH_SHORT).show();
        }
    }

    private void loadListCar() {
        cart = new Database(this).getCarts();
        adapter = new CartAdapter(cart,this);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


        //Calculate total price
        int total = 0;
        for (Order order:cart)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuantity()));

        Locale locale = new Locale("en","US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

        txtTotalPrice.setText(fmt.format(total));
    }

    //press ctrl+o

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals(Common.DELETE))
            deleteCart(item.getOrder());
        return true;
    }
    private void deleteCart(int order) {
        //We will remove item at List<Order> by position
        cart.remove(order);
        //After that , we will delete all old data from SQLite
        new Database(this).cleanCart();
        //And final , we will update new data from List<Order> to SQLite
        for (Order item:cart)
            new Database(this).addToCart(item);
        //Refresh
        loadListCar();
    }
}
