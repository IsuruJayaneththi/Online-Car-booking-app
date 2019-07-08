package com.example.issa.pdm_project_2018.Manoj;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.issa.pdm_project_2018.Model.Fuel;
import com.example.issa.pdm_project_2018.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab3 extends Fragment implements CustomAdapter.OnItemClickListner{

    RecyclerView re;
    Fuel fl;
    CustomAdapter adapter;
    DatabaseReference db;
    DatabaseReference dm;

    SearchView searchView;
    SearchAdapter sa;
    View v;
    List<Fuel> flist;
    ArrayList<String>Names;
    ArrayList<String>Distances;
    ArrayList<String>Amounts;
    ArrayList<String>Economies;

    private OnFragmentInteractionListener mListener;
    FirebaseRecyclerAdapter<Fuel,CustomAdapter.CustomerViewHolder> searchadapter;
    List<String> suggestlist=new ArrayList<>();
    MaterialSearchBar materialSearchBar;

    public Tab3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.fragment_tab3,container,false);

        re=(RecyclerView)view.findViewById(R.id.recyclerView);
        re.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        re.setHasFixedSize(true);
        re.addItemDecoration(new DividerItemDecoration(getActivity().getBaseContext(),LinearLayoutManager.VERTICAL));
        materialSearchBar=(MaterialSearchBar)view.findViewById(R.id.searchbar);
        dm= FirebaseDatabase.getInstance().getReference();
        flist=new ArrayList<>();
        Names=new ArrayList<>();
        Distances=new ArrayList<>();
        Amounts=new ArrayList<>();
        Economies=new ArrayList<>();


        loadsuggest();


        db=FirebaseDatabase.getInstance().getReference("Fuel");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    flist.clear();
                    for(DataSnapshot fuelsnapshot: dataSnapshot.getChildren())
                    {
                        Fuel fl=fuelsnapshot.getValue(Fuel.class);
                        fl.setkey(fuelsnapshot.getKey());
                        flist.add(fl);

                    }

                    adapter=new CustomAdapter(getContext(),flist);
                    re.setAdapter(adapter);
                    adapter.setOnItemClickListner(Tab3.this);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    private void loadsuggest() {

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!editable.toString().isEmpty())
                {
                    setAdapter(editable.toString());
                }

                else
                {

                    adapter=new CustomAdapter(getContext(),flist);
                    re.setAdapter(adapter);
                }
            }
        });
    }

    private void setAdapter(final String searchedString) {

        Names.clear();
        Distances.clear();;
        Amounts.clear();
        Economies.clear();
        re.removeAllViews();
        dm.child("Fuel").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count=0;
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    String uid=snapshot.getKey();
                    String nme=snapshot.child("name").getValue(String.class);
                    String distance=snapshot.child("distance").getValue(String.class);
                    String Amount=snapshot.child("fuel_Amount").getValue(String.class);
                    String economy=snapshot.child("economy").getValue(String.class);

                    if(nme.contains(searchedString))
                    {
                        Names.add(nme);
                        Distances.add(distance);
                        Amounts.add(Amount);
                        Economies.add(economy);
                        count++;
                    }

                    if(count==15)
                        break;
                }
                sa=new SearchAdapter(getContext(),Names,Distances,Amounts,Economies);
                re.setAdapter(sa);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*
        this.v=view;
        init();
        loaddata();
        */


      /*  db= FirebaseDatabase.getInstance().getReference("Fuel");
        helper=new FirebaseHelper(db);

        adapter=new CustomAdapter(this.getActivity(),helper.retrieve());
        lve.setAdapter(adapter);*/
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        super.onCreateOptionsMenu(menu, inflater);
    }



    private  void deletevalue(Fuel fl)
    {

        int position=flist.indexOf(fl);
        flist.remove(position);

    }
    /*
        @Override
        public boolean onContextItemSelected(MenuItem item) {

            if(item.getTitle().equals(common.DELETE)) {
                int position=flist.indexOf(fl);
                Fuel selecteditem=fl.get
                db = FirebaseDatabase.getInstance().getReference("Fuel");
               // db.child(fl.getMkey()).removeValue();
                db.removeValue();

            }

            return super.onContextItemSelected(item);


        }
    */
    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onWhateverClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

        Fuel selectedItem=flist.get(position);
        String selectedKey=selectedItem.getkey();
        db=FirebaseDatabase.getInstance().getReference("Fuel");
        db.child(selectedKey).removeValue();



    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
