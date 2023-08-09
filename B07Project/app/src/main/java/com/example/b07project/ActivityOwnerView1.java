package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.b07project.databinding.ActivityOwnerView1Binding;
import com.example.b07project.databinding.ActivityShopperView1Binding;

public class ActivityOwnerView1 extends AppCompatActivity {

    static String username; //will be passed from previous activity
    ActivityOwnerView1Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_view1);

        Intent receiverIntent = getIntent(); //These lines receive the string from previous activity
        username = receiverIntent.getStringExtra("KEY_USERNAME"); //AND update username to match

        // TextView msgWelcome = (TextView) findViewById(R.id.textViewWelcomeTestOwner); //THIS LINE IS PURELY FOR TESTING
        // msgWelcome.setText("Welcome " + username + "!");                              //FEEL FREE TO REMOVE LATER ! :-)

        // to send username to all owner fragments
        Bundle bundle = new Bundle();
        bundle.putString("username", username); // key value I want to share w/ frags
        Fragment ownerStoreFrag = new OwnerStoreFragment();
        Fragment ownerHomeFrag = new OwnerHomeFragment();
        Fragment ownerOrdersFrag = new OwnerOrdersFragment();
        // giving all frags the bundle of info I want to share
        ownerStoreFrag.setArguments(bundle);
        ownerHomeFrag.setArguments(bundle);
        ownerOrdersFrag.setArguments(bundle);


        binding = ActivityOwnerView1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(ownerHomeFrag);
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.owner_home)
                replaceFragment(ownerHomeFrag);
            else if (item.getItemId() == R.id.owner_shop)
                replaceFragment(ownerStoreFrag);
            else if (item.getItemId() == R.id.owner_orders)
                replaceFragment(ownerOrdersFrag);
            else
                replaceFragment(ownerHomeFrag);

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ownerView, fragment);
        fragmentTransaction.commit();

    }
}