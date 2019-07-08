package com.example.issa.pdm_project_2018.Model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseHelper {



    DatabaseReference db;
    Boolean saved =null;
    ArrayList<Fuel> fuels=new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    public  Boolean save(Fuel fuel)
    {
        if(fuel==null)
        {
            saved=false;
        }
        else
        {
            try {
                //db.child("Fuel").push().setValue(fuel);
                db= FirebaseDatabase.getInstance().getReference("Fuel");
                db.push().setValue(fuel);
                saved=true;
            }
            catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }


    public ArrayList<String> retreive() {
        final ArrayList<String> categories = new ArrayList<>();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot,categories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return categories;
    }

    private  void fetchData(DataSnapshot snapshot,ArrayList<String> ctegory)
    {
        ctegory.clear();
        for(DataSnapshot ds:snapshot.getChildren())
        {
            String nme;
            nme = ds.getValue(Cars.class).getName();
            ctegory.add(nme);
        }
    }

}
