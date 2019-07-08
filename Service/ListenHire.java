package com.example.issa.pdm_project_2018.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;

import com.example.issa.pdm_project_2018.Common.Common;
import com.example.issa.pdm_project_2018.Isuru.OilChangeStatus;
import com.example.issa.pdm_project_2018.Model.Hireinfo;
import com.example.issa.pdm_project_2018.Model.Oil_Change_Records;
import com.example.issa.pdm_project_2018.R;
import com.example.issa.pdm_project_2018.Shashi.HireStatus;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListenHire extends Service implements ChildEventListener {

    FirebaseDatabase db;
    DatabaseReference hire;

    public ListenHire() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseDatabase.getInstance();
        hire = db.getReference("Hire Information Records");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        hire.addChildEventListener(this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        //Trigger here
        Hireinfo hire = dataSnapshot.getValue(Hireinfo.class);
        showNotification(dataSnapshot.getKey(),hire);
    }

    private void showNotification(String key, Hireinfo oil) {
        Intent intent = new Intent(getBaseContext(), HireStatus.class);
        intent.putExtra("userPhone",oil.getPhone());//We need put user Phone , why? I'll talk late
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());


        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("Vehicle")
                .setContentInfo("Your Event was updated")
                .setContentText("Event  #"+key+" was update status to "+ Common.convertCode1ToStatus(oil.getStatus()))
                .setContentIntent(contentIntent)
                .setContentInfo("Info")
                .setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager notificationManager = (NotificationManager)getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
