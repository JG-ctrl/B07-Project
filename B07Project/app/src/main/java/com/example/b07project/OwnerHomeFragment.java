package com.example.b07project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OwnerHomeFragment extends Fragment {

    public OwnerHomeFragment() {
        // Required empty public constructor
    }

    String username;
    RecyclerView recyclerView;
    ArrayList<Item> itemsList;
    DatabaseReference db;
    MyAdapterOwner adapterOwner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        username = this.getArguments().getString("username");
        View v = inflater.inflate(R.layout.fragment_owner_home, container, false);


        return v;
    }


    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        recyclerView = v.findViewById(R.id.recycler_view_owner_home);
        db = FirebaseDatabase.getInstance().getReference().child("Owners").child(username).child("products");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);

        itemsList = new ArrayList<>();
        adapterOwner = new MyAdapterOwner(getContext(), itemsList);
        recyclerView.setAdapter(adapterOwner);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    String id = dataSnapshot.child("id").getValue(String.class);
                    String descrip = dataSnapshot.child("description").getValue(String.class);
                    String name = dataSnapshot.child("name").getValue(String.class);
                    Double price = dataSnapshot.child("price").getValue(Double.class);
                    Item item = new Item(name, price, descrip, id, username);
                    itemsList.add(item);
                }
                adapterOwner.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}