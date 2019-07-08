package com.example.issa.pdm_project_2018.Manoj;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.issa.pdm_project_2018.Model.FirebaseHelper;
import com.example.issa.pdm_project_2018.Model.Fuel;
import com.example.issa.pdm_project_2018.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class tab1 extends Fragment {


    CustomAdapter adapter;
    DatabaseReference db;
    DatabaseReference dd;
    DatabaseReference dm;
    FirebaseHelper helper;
    FirebaseHelper helpe;
    FirebaseHelper hel;
    public tab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.fragment_tab1,container,false);
        Button calbtn=(Button)view.findViewById(R.id.CalculateBtn);

        final TextView tv=(TextView)view.findViewById(R.id.Ans);
        final EditText odometer1 =(EditText) view.findViewById(R.id.odo1);
        final EditText odometer2 =(EditText) view.findViewById(R.id.odo2);
        final EditText  fuelamount =  (EditText)view.findViewById(R.id.fuel);
        //final EditText  vehi =  (EditText)view.findViewById(R.id.vehiname);
        // final MaterialBetterSpinner ms=(MaterialBetterSpinner) view.findViewById(R.id.spi);
        final Spinner ms=(Spinner) view.findViewById(R.id.spi);
        //  ArrayAdapter<String> arradapt=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_dropdown_item_1line,SPINNELIST);


        final List<String> vehiname=new ArrayList<String>();

        //  ms.setAdapter(arradapt);
        db= FirebaseDatabase.getInstance().getReference();
        dd= FirebaseDatabase.getInstance().getReference("Cars");
        dm=FirebaseDatabase.getInstance().getReference("Fuel");
        helper=new FirebaseHelper(db);
        //  hel=new FirebaseHelper(dd);

        helpe=new FirebaseHelper(dm);

        dd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    vehiname.add(postSnapshot.child("name").getValue().toString());
                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,vehiname);
                ms.setPrompt("Select the Vehicle");
                ms.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // ms.setAdapter(new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, hel.retreive()));

     /*  ms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             String  psubject = adapterView.getItemAtPosition(i).toString();
             fuelamount.setText(psubject);

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
*/

        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(odometer1.getText().toString().isEmpty() || odometer2.getText().toString().isEmpty() || fuelamount.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(),"Some fields are Empty",Toast.LENGTH_SHORT).show();
                }


                else {
                    double odoval1 = Double.parseDouble(odometer1.getText().toString());
                    double odoval2 = Double.parseDouble(odometer2.getText().toString());
                    double fuelamnt = Double.parseDouble(fuelamount.getText().toString());

                    int od1 = Integer.parseInt(odometer1.getText().toString());
                    int od2 = Integer.parseInt(odometer2.getText().toString());

                    String carname = ms.getSelectedItem().toString();
                    // String carname=vehi.getSeltedItem.toString();
                    String fm = Double.toString(fuelamnt);
                    int sum = od2 - od1;


                    double resut = (odoval2 - odoval1) / fuelamnt;

                    if(resut>0)
                    {
                        String distance = Integer.toString(sum);
                        String stringresult = String.format("%.2f",resut);

                        tv.setText(stringresult);

                        Fuel f = new Fuel();
                        f.setDistance(distance);
                        f.setEconomy(stringresult);
                        f.setName(carname);
                        f.setFuel_Amount(fm);
                        if (odometer1.getText().toString().isEmpty() || odometer2.getText().toString().isEmpty() || fuelamount.getText().toString().isEmpty())
                        {
                            Toast.makeText(getContext(), "Some fields are Empty", Toast.LENGTH_SHORT).show();
                    /*if (helpe.save(f)) {
                        fuelamount.setText("succes");

                    }*/
                        }

                        else
                        {
                            if (helpe.save(f))
                            {
                                Toast.makeText(getContext(), "Record Saved", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    else
                    {
                        Toast.makeText(getContext(), "End value should be greater than strating Value", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

