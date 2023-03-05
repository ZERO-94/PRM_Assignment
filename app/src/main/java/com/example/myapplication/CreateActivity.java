package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import Dao.InstrumentDao;
import Models.Instrument;

public class CreateActivity extends AppCompatActivity {

    private EditText editTextName, editTextCode, editTextPrice, editTextAmount, editTextImage;
    private TextView actionTextView;
    InstrumentDao dao;
    private String instrumentCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Intent intent = getIntent();
        if(intent != null) {
            instrumentCode = intent.getStringExtra("instrumentCode");
        }

        dao = new InstrumentDao(CreateActivity.this);

        editTextCode = findViewById(R.id.editTextCode);
        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextImage = findViewById(R.id.editTextImageUrl);
        actionTextView = findViewById(R.id.actionTextView);

        if(instrumentCode != null) {
            List<Instrument> data = dao.getAll();
            Instrument instrument = null;
            for(int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i).getCode());
                if(data.get(i).getCode().equals(instrumentCode)) {
                    instrument = data.get(i);
                    break;
                }
            }
            if(instrument != null) {
                actionTextView.setText("Update");
                editTextCode.setText(instrumentCode);
                editTextCode.setEnabled(false);
                editTextName.setText(instrument.getName());
                editTextPrice.setText(String.valueOf(instrument.getPrice()));
                editTextAmount.setText(String.valueOf(instrument.getAmount()));
                editTextImage.setText(instrument.getImageUrl());
            }
        }
    }

    public void onCreateClick(View view) {
        String code = editTextCode.getText().toString();
        String name = editTextName.getText().toString();
        int price = Integer.valueOf(editTextPrice.getText().toString());
        int amount = Integer.valueOf(editTextAmount.getText().toString());
        String imageUrl = editTextImage.getText().toString();

        Instrument instrument = new Instrument(code, name, imageUrl, price, amount);
        if(instrumentCode == null)
            dao.create(instrument);
        else
            dao.update(instrument);
        Intent intent = new Intent(CreateActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}