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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapterOwnerOrdersItems extends RecyclerView.Adapter<MyAdapterOwnerOrdersItems.OwnerOrdersViewHolder> {

    Context context;
    ArrayList<ShopperItem> items;

    public MyAdapterOwnerOrdersItems(Context context, ArrayList<ShopperItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public OwnerOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.display_owner_orders_item, parent, false);
        return new OwnerOrdersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerOrdersViewHolder holder, int position) {
        ShopperItem item = items.get(position);
        Log.d("OwnerOrderTest", "onBindViewHolder: " + item.getId());
        holder.id.setText(item.getId());
        holder.quantity.setText("  x" + item.getQuantity());

        String status = "Complete";
        if(item.getStatus() == 0) status = "Pending";

        DatabaseReference namedb = FirebaseDatabase.getInstance().getReference().child("Owners").child(item.getOwnerUsername())
                .child("products").child(item.getId()).child("name");

        namedb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.name.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        holder.status.setText(status);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("", "onClick: Completing this thing");
                item.setStatus(1, item.getUsername(), item.getId(), item.getOwnerUsername());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class OwnerOrdersViewHolder extends RecyclerView.ViewHolder {
        TextView status, quantity, name, id;
        Button btn;


        public OwnerOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity = itemView.findViewById(R.id.owner_order_item_amount);
            name = itemView.findViewById(R.id.owner_order_item_name);
            id= itemView.findViewById((R.id.owner_order_item_id));
            status = itemView.findViewById(R.id.owner_order_item_status);
            btn = itemView.findViewById(R.id.owner_order_button);
        }
    }
}