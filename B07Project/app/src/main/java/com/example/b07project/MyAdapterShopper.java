package com.example.b07project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class MyAdapterShopper extends RecyclerView.Adapter<MyAdapterShopper.MyViewHolder> {
    Context context;
    ArrayList<ShopperItem> list;
    static private DatabaseReference db;


    public MyAdapterShopper(Context context, ArrayList<ShopperItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.shopper_cart_preview, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShopperItem shopperItem = list.get(position);

        int quantity = shopperItem.getQuantity();
        holder.item_quantity.setText(String.valueOf(quantity));

        String ownerUsername = shopperItem.getOwnerUsername();
        String id = shopperItem.getId();
        holder.item_id.setText(id);
        holder.username = shopperItem.getUsername();

        db = FirebaseDatabase.getInstance("https://b07-project-3237a-default-rtdb.firebaseio.com/").getReference("Owners").child(ownerUsername).child("products").child(id);


        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                holder.item_name.setText(name);
                double price = dataSnapshot.child("price").getValue(Double.class);
                double total = price * (double) quantity;
                String total_str = Double.toString(total);
                holder.item_total.setText(total_str);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        holder.rmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopperItem.removeFromCart(id, quantity+1, holder.username);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_name, item_total, item_quantity, item_id;
        String username;
        Button rmBtn, editBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.shopper_item_name_text);
            item_total = itemView.findViewById(R.id.shopper_item_total_text);
            item_quantity = itemView.findViewById(R.id.shopper_item_quantity_text);
            item_id = itemView.findViewById(R.id.shopper_item_id_text);


            rmBtn = itemView.findViewById(R.id.shopper_remove_item);
        }

    }
}