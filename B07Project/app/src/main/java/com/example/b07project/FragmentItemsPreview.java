package com.example.b07project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class FragmentItemsPreview extends Fragment {

    String username;
    String storeName;
    RecyclerView recyclerView;
    ArrayList<Item> list;
    DatabaseReference db;
    AdapterItemsPreview adapterItemsPreview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        username = this.getArguments().getString("username");
        storeName = this.getArguments().getString("storeName");
        View view = inflater.inflate(R.layout.fragment_items_preview, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_items_preview);
        db = FirebaseDatabase.getInstance("https://b07-project-3237a-default-rtdb.firebaseio.com/").getReference("Owners");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);

        list = new ArrayList<>();
        adapterItemsPreview = new AdapterItemsPreview(getContext(), list);
        recyclerView.setAdapter(adapterItemsPreview);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {

                    if (Objects.equals(dataSnapshot.child("storeName").getValue(String.class), storeName)) {
                        for (DataSnapshot ds: dataSnapshot.child("products").getChildren()) {

                            Item item = ds.getValue(Item.class);
                            list.add(item);
                        }
                    }
                }

                adapterItemsPreview.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}