package com.example.b07project;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityLoginPageModel {

    FirebaseDatabase db;

    public ActivityLoginPageModel() {
        db = FirebaseDatabase.getInstance("https://b07-project-3237a-default-rtdb.firebaseio.com/");
    }

    public void queryDBShopper(String username, String password, ActivityLoginPagePresenter presenter) {
        DatabaseReference ref = db.getReference();
        // get a snapshot of the database that checks if the given username exists (and extend to password for ease)
        DatabaseReference query = ref.child("Shoppers").child(username).child("password");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean valid = snapshot.exists() && password.equals(snapshot.getValue());
                presenter.loginShopper(valid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void queryDBOwner(String username, String password, ActivityLoginPagePresenter presenter) {
        DatabaseReference ref = db.getReference();
        // get a snapshot of the database that checks if the given username exists (and extend to password for ease)
        DatabaseReference query = ref.child("Owners").child(username).child("password");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean valid = snapshot.exists() && password.equals(snapshot.getValue());
                presenter.loginOwner(valid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
