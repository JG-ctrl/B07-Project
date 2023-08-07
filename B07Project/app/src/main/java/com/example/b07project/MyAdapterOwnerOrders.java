package com.example.b07project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterOwnerOrders extends RecyclerView.Adapter<MyAdapterOwnerOrders.MyItemsHolder> {

    Context context;
    ArrayList<ArrayList<ShopperItem>> orders;
    ArrayList<String> shopperNames;

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    public MyAdapterOwnerOrders(Context context, ArrayList<ArrayList<ShopperItem>> orders, ArrayList<String> shopperNames) {
        this.context = context;
        this.orders = orders;
        this.shopperNames = shopperNames;
    }

    @NonNull
    @Override
    public MyItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.display_owner_orders, parent, false);
        return new MyAdapterOwnerOrders.MyItemsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemsHolder holder, int position) {
        Log.d("OwnerOrderTest", "onBindViewHolder: " + orders.size() +","+ shopperNames.size());
        ArrayList<ShopperItem> items = orders.get(position);
        String name = shopperNames.get(position);

        holder.name.setText(name);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(holder.itemsRecycler.getContext(), LinearLayoutManager.VERTICAL, false);

        layoutManager.setInitialPrefetchItemCount(items.size());

        // Create an instance of the child
        // item view adapter and set its
        // adapter, layout manager and RecyclerViewPool
        MyAdapterOwnerOrdersItems itemsAdapter = new MyAdapterOwnerOrdersItems(this.context, items);
        holder.itemsRecycler.setLayoutManager(layoutManager);
        holder.itemsRecycler.setAdapter(itemsAdapter);
        holder.itemsRecycler.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class MyItemsHolder extends RecyclerView.ViewHolder {
        TextView name;
        RecyclerView itemsRecycler;

        public MyItemsHolder(@NonNull View itemView) {
            super(itemView);
            itemsRecycler = itemView.findViewById(R.id.owner_order_items_cart);
            name = itemView.findViewById(R.id.owner_order_shopper_name);

        }
    }
}