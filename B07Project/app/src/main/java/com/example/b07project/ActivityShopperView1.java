package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.b07project.databinding.ActivityShopperView1Binding;

public class ActivityShopperView1 extends AppCompatActivity {

    static String username; //will be passed from previous activity
    ActivityShopperView1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent receiverIntent = getIntent(); //These lines receive the string from previous activity
        username = receiverIntent.getStringExtra("KEY_USERNAME"); //AND update username to match

        // to send username to all shopper fragments
        Bundle bundle = new Bundle();
        bundle.putString("username", username); // key value I want to share w/ frags
        Fragment shopperHomeFrag = new FragmentShopperHome();
        Fragment shopperCartFrag = new FragmentShopperCart();
        Fragment shopperOrderFrag = new FragmentShopperOrders();
        // giving all frags the bundle of info I want to share
        shopperHomeFrag.setArguments(bundle);
        shopperCartFrag.setArguments(bundle);
        shopperOrderFrag.setArguments(bundle);

        binding = ActivityShopperView1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(shopperHomeFrag);
        binding.bottomNavigationView2.setBackground(null);

        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home)
                replaceFragment(shopperHomeFrag);
            else if (item.getItemId() == R.id.orders)
                replaceFragment(shopperOrderFrag);
            else
                replaceFragment(shopperCartFrag);

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

    }


}