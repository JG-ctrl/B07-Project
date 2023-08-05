package com.example.b07project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Map;

public class FragmentShopperHome extends Fragment implements ShopperHomeInterface{
    String username;
    RecyclerView recyclerView;
    ArrayList<Owner> list;
    DatabaseReference db;
    AdapterShopperHome adapterShopperHome;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        username = this.getArguments().getString("username");
        return inflater.inflate(R.layout.fragment_shopper_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_shopper_home);
        db = FirebaseDatabase.getInstance("https://b07-project-3237a-default-rtdb.firebaseio.com/").getReference("Owners");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);

        list = new ArrayList<>();
        adapterShopperHome = new AdapterShopperHome(getContext(), list, this);
        recyclerView.setAdapter(adapterShopperHome);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Owner owner = dataSnapshot.getValue(Owner.class);
                    list.add(owner);
                }

                adapterShopperHome.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void OnItemClick(int position) {
        FragmentItemsPreview fragmentItemsPreview = new FragmentItemsPreview();

        Bundle bundle = new Bundle();
        bundle.putString("storeName", list.get(position).getStoreName());
        bundle.putString("username", username);
        fragmentItemsPreview.setArguments(bundle);

        replaceFragment(fragmentItemsPreview);
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}