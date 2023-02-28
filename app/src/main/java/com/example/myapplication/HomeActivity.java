package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import Adapter.InstrumentAdapter;
import Dao.InstrumentDao;
import Models.Instrument;

public class HomeActivity extends AppCompatActivity {

    ArrayList<Instrument> data = new ArrayList<>();
    InstrumentDao instrumentDao;
    InstrumentAdapter instrumentAdapter;
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(getIntent() != null) {
            Intent intent = getIntent();
            System.out.println(intent.getStringExtra("username"));
        }

        listView = findViewById(R.id.lv);
        instrumentDao = new InstrumentDao(HomeActivity.this);

        data = instrumentDao.getAll();
        System.out.println(data.size());

        instrumentAdapter = new InstrumentAdapter(HomeActivity.this, instrumentDao);

        listView.setAdapter(instrumentAdapter);
        listView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_logout:
                onLogout();
                return true;
            case R.id.option_create:
                onCreate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onLogout() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onCreate() {
        Intent intent = new Intent(HomeActivity.this, CreateActivity.class);
        startActivity(intent);
    }
}