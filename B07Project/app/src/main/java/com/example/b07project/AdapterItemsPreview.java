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

import java.util.ArrayList;

public class AdapterItemsPreview extends RecyclerView.Adapter<AdapterItemsPreview.MyViewHolder> {

    Context context;
    ArrayList<Item> list;

    String username;

    public AdapterItemsPreview(Context context, ArrayList<Item> list, String username) {
        this.context = context;
        this.list = list;
        this.username = username;
    }

    @NonNull
    @Override
    public AdapterItemsPreview.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.shopper_items_preview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItemsPreview.MyViewHolder holder, int position) {
        Item item = list.get(position);

        holder.name.setText(item.getName());
        holder.id.setText(item.getId());
        holder.price.setText(String.valueOf(item.getPrice()));
        holder.desc.setText(item.getDescription());

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShopperItem shopperItem = new ShopperItem();
                shopperItem.addToCart(item.getId(), 1, username, item.getOwnerUsername());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, id, price, desc;
        Button addBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_preview_name_text);
            id = itemView.findViewById(R.id.item_preview_id_text);
            price = itemView.findViewById(R.id.item_preview_price_text);
            desc = itemView.findViewById(R.id.item_preview_desc_text);

            addBtn = itemView.findViewById(R.id.shopper_add_to_cart);

        }
    }
}
