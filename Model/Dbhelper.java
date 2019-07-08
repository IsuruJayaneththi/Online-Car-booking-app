package com.example.issa.pdm_project_2018.Model;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Dbhelper {

    DatabaseReference db;
    Boolean saved=null;
    ArrayList<Service> services=new ArrayList<>();

    public Dbhelper(DatabaseReference db) {
        this.db = db;
    }

    public  Boolean save(Service service)
    {
        if(service==null)
        {
            saved=false;
        }
        else
        {
            try {
                //db.child("Fuel").push().setValue(fuel);
                db= FirebaseDatabase.getInstance().getReference("Service");
                db.push().setValue(service);
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



}
