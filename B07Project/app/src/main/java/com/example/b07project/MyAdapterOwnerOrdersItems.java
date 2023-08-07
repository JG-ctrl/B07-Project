package com.example.b07project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.name.setText(item.getId());
        holder.quantity.setText("  x" + item.getQuantity());
        String status = "Complete";
        if(item.getStatus() == 0) status = "Pending";
        holder.status.setText(status);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class OwnerOrdersViewHolder extends RecyclerView.ViewHolder {
        TextView status, quantity, name;


        public OwnerOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity = itemView.findViewById(R.id.owner_order_item_amount);
            name = itemView.findViewById(R.id.owner_order_item_name);
            status = itemView.findViewById(R.id.owner_order_item_status);
        }
    }
}