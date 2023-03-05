package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.stream.Collectors;

import Dao.InstrumentDao;
import Models.Instrument;
import Utils.DownloadImageTask;

public class InstrumentDetail extends AppCompatActivity {

    private TextView codeTextView, nameTextView, priceTextView, amountTextView;
    private ImageView imageView;
    InstrumentDao dao;
    String instrumentCode;
    ArrayList<Instrument> data = new ArrayList<>();

    public InstrumentDetail() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_detail);

        codeTextView = findViewById(R.id.codeTextView);
        nameTextView = findViewById(R.id.nameTextView);
        priceTextView = findViewById(R.id.priceTextView);
        amountTextView = findViewById(R.id.amountTextView);
        imageView = findViewById(R.id.instrumentImageView);

        dao = new InstrumentDao(InstrumentDetail.this);
        data = dao.getAll();

        Intent intent = getIntent();
        if(intent != null) {
            instrumentCode = intent.getStringExtra("instrumentCode");
            Instrument instrument = null;
            for(int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i).getCode());
                if(data.get(i).getCode().equals(instrumentCode)) {
                    instrument = data.get(i);
                    break;
                }
            }

            if(instrument != null) {
                codeTextView.setText(instrument.getCode());
                nameTextView.setText(instrument.getName());
                priceTextView.setText(String.valueOf(instrument.getPrice()));
                amountTextView.setText(String.valueOf(instrument.getAmount()));
                new DownloadImageTask(imageView)
                        .execute(instrument.getImageUrl());
            }
        }
    }

    public void onEdit(View view) {
        Intent intent = new Intent(InstrumentDetail.this, CreateActivity.class);
        intent.putExtra("instrumentCode", instrumentCode);
        startActivity(intent);
    }

    public void onDelete(View view) {
        dao.delete(instrumentCode);
        Intent intent = new Intent(InstrumentDetail.this, HomeActivity.class);
        startActivity(intent);
    }

    public void onGoBack(View view) {
        Intent intent = new Intent(InstrumentDetail.this, HomeActivity.class);
        startActivity(intent);
    }
}