package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import Dao.InstrumentDao;
import Models.Instrument;

public class CreateActivity extends AppCompatActivity {

    private EditText editTextName, editTextCode, editTextPrice, editTextAmount, editTextImage;
    InstrumentDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        dao = new InstrumentDao(CreateActivity.this);

        editTextCode = findViewById(R.id.editTextCode);
        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextImage = findViewById(R.id.editTextImageUrl);
    }

    public void onCreateClick(View view) {
        String code = editTextCode.getText().toString();
        String name = editTextName.getText().toString();
        int price = Integer.valueOf(editTextPrice.getText().toString());
        int amount = Integer.valueOf(editTextAmount.getText().toString());
        String imageUrl = editTextImage.getText().toString();

        Instrument instrument = new Instrument(code, name, imageUrl, price, amount);
        dao.create(instrument);
        Intent intent = new Intent(CreateActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}