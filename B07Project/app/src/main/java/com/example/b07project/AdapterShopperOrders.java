package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterShopperOrders extends RecyclerView.Adapter<AdapterShopperOrders.MyViewHolder> {
    Context context;
    ArrayList<ShopperItem> list;
    static private DatabaseReference db;

    public AdapterShopperOrders(Context context, ArrayList<ShopperItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.shopper_orders_preview,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShopperItem item = list.get(position);

        int status = item.getStatus(); //set status
        if(status == 0) {
            holder.item_status.setText("Not Started");
        }
        else if (status == 1) {
            holder.item_status.setText("In Progress");
        }
        else {
            holder.item_status.setText("Ready For Pickup!");
        }

        holder.item_id.setText(item.getId()); //set ID

        int quantity = item.getQuantity();  //set quantity
        holder.item_quantity.setText(String.valueOf(quantity));

        DatabaseReference ownerDB = FirebaseDatabase.getInstance("https://b07-project-3237a-default-rtdb.firebaseio.com/").getReference("Owners").child(item.getOwnerUsername()).child("products").child(item.getId());
        ownerDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                holder.item_name.setText(snapshot.child("name").getValue(String.class)); //SET ITEM NAME

                double price = snapshot.child("price").getValue(Double.class); //SET TOTAL
                double total = price * (double) quantity;
                holder.item_total.setText(Double.toString(total));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView item_name, item_total, item_quantity, item_id, item_status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.shopper_order_name_text);
            item_total = itemView.findViewById(R.id.shopper_order_total_text);
            item_quantity = itemView.findViewById(R.id.shopper_order_quantity_text);
            item_id = itemView.findViewById(R.id.shopper_order_id_text);
            item_status = itemView.findViewById(R.id.shopper_order_status_text);
        }
    }
}
