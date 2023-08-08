package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentShopperCart extends Fragment {

    String username;
    RecyclerView recyclerView;
    ArrayList<ShopperItem> list;
    DatabaseReference db;
    MyAdapterShopper myAdapterShopper;
    Button btnOrder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        username = this.getArguments().getString("username");
        return inflater.inflate(R.layout.fragment_shopper_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_shopper_cart);
        db = FirebaseDatabase.getInstance("https://b07-project-3237a-default-rtdb.firebaseio.com/").getReference("Shoppers").child(username).child("cart");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);

        btnOrder = view.findViewById(R.id.btnPlaceOrder);

        list = new ArrayList<>();
        myAdapterShopper = new MyAdapterShopper(getContext(), list);
        recyclerView.setAdapter(myAdapterShopper);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {

                    ShopperItem shopperItem = dataSnapshot.getValue(ShopperItem.class);
                    list.add(shopperItem);
                }

                myAdapterShopper.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();

                // Create an Intent to open the new activity
                Intent intent = new Intent(getContext(), ActivityShopperView1.class); 
                startActivity(intent);
            }
        });

    }

    private void placeOrder() { //THIS ONE WAS NOT FUN TO IMPLEMENT :(
        DatabaseReference ownerDB = FirebaseDatabase.getInstance("https://b07-project-3237a-default-rtdb.firebaseio.com/").getReference("Owners");
        DatabaseReference shopperDB = FirebaseDatabase.getInstance("https://b07-project-3237a-default-rtdb.firebaseio.com/").getReference("Shoppers"); //create two seperate references for ease

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) { //NEED TO ITERATE THROUGH EVERY ITEM IN CURRENT CART
                    ShopperItem shopperItem = dataSnapshot.getValue(ShopperItem.class);


                    ownerDB.child(shopperItem.getOwnerUsername()).child("orders").child(shopperItem.getUsername()).child(shopperItem.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){ //HAVE TO CHECK IF ITEM IS ALR IN CURRENT CART
                                int curVal = snapshot.child("quantity").getValue(Integer.class);
                                ownerDB.child(shopperItem.getOwnerUsername()).child("orders").child(shopperItem.getUsername()).child(shopperItem.getId()).child("quantity").setValue(curVal + shopperItem.getQuantity());
                            }
                            else { //IF NOT ADD IT
                                ownerDB.child(shopperItem.getOwnerUsername()).child("orders").child(shopperItem.getUsername()).child(shopperItem.getId()).setValue(shopperItem);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });


                    shopperDB.child(shopperItem.getUsername()).child("orders").child(shopperItem.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                int curVal = snapshot.child("quantity").getValue(Integer.class);
                                shopperDB.child(shopperItem.getUsername()).child("orders").child(shopperItem.getId()).child("quantity").setValue(curVal + shopperItem.getQuantity());
                            }
                            else{
                                shopperDB.child(shopperItem.getUsername()).child("orders").child(shopperItem.getId()).setValue(shopperItem);
                            }
                         }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });


                    db.child(shopperItem.getId()).removeValue();
                    Toast.makeText(getContext(), "Order placed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Order failed.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
