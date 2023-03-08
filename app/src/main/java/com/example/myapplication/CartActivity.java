package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import Adapter.CartAdapter;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cartList;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartList = findViewById(R.id.cartList);

        cartAdapter = new CartAdapter(this);
        cartList.setAdapter(cartAdapter);
        cartList.setLayoutManager(new GridLayoutManager(this, 1));
    }

    public void onGoBack(View view) {
        finish();
    }
}