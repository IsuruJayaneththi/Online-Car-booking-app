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
import com.example.issa.pdm_project_2018.Isuru.DamageStatus;
import com.example.issa.pdm_project_2018.Isuru.OilChangeStatus;
import com.example.issa.pdm_project_2018.Model.Damage_Records;
import com.example.issa.pdm_project_2018.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListenDmg extends Service implements ChildEventListener {

    FirebaseDatabase db;
    DatabaseReference damage;

    public ListenDmg() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseDatabase.getInstance();
        damage = db.getReference("Damage Records");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        damage.addChildEventListener(this);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        //Trigger here
        Damage_Records dmg = dataSnapshot.getValue(Damage_Records.class);
        showNotification(dataSnapshot.getKey(),dmg);
    }

    private void showNotification(String key, Damage_Records dmg) {
        Intent intent = new Intent(getBaseContext(), DamageStatus.class);
        intent.putExtra("userPhone",dmg.getPhone());//We need put user Phone , why? I'll talk late
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());


        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("Vehicle")
                .setContentInfo("Your Event was updated")
                .setContentText("Event  #"+key+" was update status to "+ Common.convertCode1ToStatus(dmg.getStatus()))
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
