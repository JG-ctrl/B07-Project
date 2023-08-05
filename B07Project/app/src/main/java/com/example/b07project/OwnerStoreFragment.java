package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OwnerStoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OwnerStoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OwnerStoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OwnerStoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OwnerStoreFragment newInstance(String param1, String param2) {
        OwnerStoreFragment fragment = new OwnerStoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    String username;
    Button btnAddItem;
    EditText name, price, description;


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
        View view = inflater.inflate(R.layout.fragment_owner_store, container, false);

        username = this.getArguments().getString("username");

        btnAddItem = view.findViewById(R.id.buttonAddItem);
        name = view.findViewById(R.id.itemName);
        price = view.findViewById(R.id.itemPrice);
        description = view.findViewById(R.id.itemDescription);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToStore();
            }
        });
        // for testing, could access username
        // TextView msgWelcome = (TextView) view.findViewById(R.id.test);
        // msgWelcome.setText("Welcome " + username + "!");

        return view;
    }

    private void addToStore() {
        String itemName = name.getText().toString();
        Double itemPrice;
        // if user doesn't put a valid double, price is 0
        try {
            itemPrice = Double.parseDouble(price.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Price must be a number", Toast.LENGTH_SHORT).show();
            return;
        }
        String itemDescription = description.getText().toString();

        Item item = new Item(itemName, itemPrice, itemDescription, username);

        if (item.addItem(username)) { // item wasnt added successfully
            Toast.makeText(getActivity(), "Item created successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Item not created", Toast.LENGTH_SHORT).show();
        }
    }
}