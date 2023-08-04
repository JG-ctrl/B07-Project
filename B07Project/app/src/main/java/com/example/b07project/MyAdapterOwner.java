package com.example.b07project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MyAdapterOwner extends RecyclerView.Adapter<MyAdapterOwner.ShopperViewHolder> {
    Context context;
    ArrayList<Item> itemsList;

    static private DatabaseReference db;

    public MyAdapterOwner(Context context, ArrayList<Item> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public ShopperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.owner_home_preview, parent, false);
        return new ShopperViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopperViewHolder holder, int position) {
        Item item = itemsList.get(position);
        holder.name.setText(item.getName());
        holder.id.setText(item.getId());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.description.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ShopperViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, price, description;

        public ShopperViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.item_id);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            description = itemView.findViewById(R.id.item_description);
        }
    }
}
