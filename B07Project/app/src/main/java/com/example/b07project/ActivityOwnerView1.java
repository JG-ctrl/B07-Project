package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.b07project.databinding.ActivityShopperView1Binding;

public class ActivityOwnerView1 extends AppCompatActivity {

    static String username; //will be passed from previous activity
    ActivityShopperView1Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_view1);

        Intent receiverIntent = getIntent(); //These lines receive the string from previous activity
        username = receiverIntent.getStringExtra("KEY_USERNAME"); //AND update username to match

        TextView msgWelcome = (TextView) findViewById(R.id.textViewWelcomeTestOwner); //THIS LINE IS PURELY FOR TESTING
        msgWelcome.setText("Welcome " + username + "!");                              //FEEL FREE TO REMOVE LATER ! :-)

        binding = ActivityShopperView1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new FragmentShopperHome());
        binding.bottomNavigationView2.setBackground(null);

        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.home)
                replaceFragment(new FragmentShopperHome());
            else if (item.getItemId() == R.id.search)
                replaceFragment(new FragmentShopperSearch());
            else
                replaceFragment(new FragmentShopperCart());

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