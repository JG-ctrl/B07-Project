package com.example.b07project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OwnerOrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OwnerOrdersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OwnerOrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OwnerOrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OwnerOrdersFragment newInstance(String param1, String param2) {
        OwnerOrdersFragment fragment = new OwnerOrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    String username;
    RecyclerView recyclerView;
    ArrayList<ArrayList<ShopperItem>> orders;
    ArrayList<String> names;
    DatabaseReference db;
    MyAdapterOwnerOrders adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        username = this.getArguments().getString("username");
        return inflater.inflate(R.layout.fragment_owner_orders, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        recyclerView = v.findViewById(R.id.owner_orders_recycler);
        db = FirebaseDatabase.getInstance().getReference().child("Owners").child(username).child("orders");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);

        orders = new ArrayList<>();
        names = new ArrayList<>();

        adapter = new MyAdapterOwnerOrders(getContext(), orders, names);
        recyclerView.setAdapter(adapter);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                orders.clear();
                names.clear();
                Log.d("OwnerOrderTest", "onDataChange: orders");
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {

                    boolean hasShop = false;
                    for(DataSnapshot dataC: dataSnapshot.getChildren()) {
                        ShopperItem order = dataC.getValue(ShopperItem.class);

                        if (!hasShop) {
                            String name = order.getUsername();
                            names.add(name);
                            hasShop = true;
                            orders.add(new ArrayList<>());
                        }
                        Log.d("OwnerOrderTest", "onDataChange: added " + order.getUsername() + order.getId());
                        orders.get(i).add(order);
                    }
                    i++;
                }
                for(String name: names) {
                    Log.d("OwnerOrderTest", "onDataChange: " + name);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}