package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AdapterShopperHome extends RecyclerView.Adapter<AdapterShopperHome.MyViewHolder> {

    Context context;
    ArrayList<Owner> list;
    ShopperHomeInterface shopperHomeInterface;

    public AdapterShopperHome(Context context, ArrayList<Owner> list, ShopperHomeInterface shopperHomeInterface) {
        this.context = context;
        this.list = list;
        this.shopperHomeInterface = shopperHomeInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.shopper_home_preview, parent, false);
        return new MyViewHolder(v, shopperHomeInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterShopperHome.MyViewHolder holder, int position) {
        Owner owner = list.get(position);
        holder.store_name.setText(owner.getStoreName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView store_name, store_owner;

        public MyViewHolder(@NonNull View itemView, ShopperHomeInterface shopperHomeInterface) {
            super(itemView);
            store_name = itemView.findViewById(R.id.store_name);
//            store_owner = itemView.findViewById(R.id.store_owner);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    shopperHomeInterface.OnItemClick(position);
                    }
            });
        }
    }
}
