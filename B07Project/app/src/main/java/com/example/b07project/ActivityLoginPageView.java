package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityLoginPageView extends AppCompatActivity {

    ActivityLoginPagePresenter presenter;
    EditText userUsername, userPassword;
    TextView msgError;
    static String username; // used for passing username between activities

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        presenter = new ActivityLoginPagePresenter(this, new ActivityLoginPageModel());
        msgError = findViewById(R.id.textViewErrorMsg);
    }

    public void onClickSignupPage(View view) {
        openActivity(ActivityAccountType.class);
    }

    public void onClickLoginShopper(View view) {
        userUsername = (EditText) findViewById(R.id.editTextUsername);
        userPassword = (EditText) findViewById(R.id.editTextPassword);
        username = userUsername.getText().toString(); // NOTE: username updates the static variable
        String password = userPassword.getText().toString(); // parse both username and password into strings

        presenter.checkDBShopper(username, password);
    }

    public void onClickLoginOwner(View view) {
        userUsername = (EditText) findViewById(R.id.editTextUsername);
        userPassword = (EditText) findViewById(R.id.editTextPassword);
        username = userUsername.getText().toString(); // NOTE: username updates the static variable
        String password = userPassword.getText().toString(); // parse both username and password into strings

        presenter.checkDBOwner(username, password);
    }

    public void openActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("KEY_USERNAME", username); // pass static username to next
        startActivity(intent);
    }

    public void writeInvalid() {
        msgError.setText("*Invalid Username or Password");
    }
}