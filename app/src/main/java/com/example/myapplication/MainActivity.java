package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Contexts.AuthContext;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void onLogin(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String role = "admin";
        if(username.equals("admin") && password.equals("123456")) {
            //Is admin
            role = "admin";
        } else if (username.equals("customer") && password.equals("123456")) {
            //Is customer
            role = "customer";
        } else {
            //invalid credential
            Toast.makeText(this,"Invalid credential!", Toast.LENGTH_SHORT).show();
            return;
        }

        AuthContext.setUser(username, role);

        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        intent.putExtra("username", editTextUsername.getText().toString());
        startActivity(intent);
    }
}