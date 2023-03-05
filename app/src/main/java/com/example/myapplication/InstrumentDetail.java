package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
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
    MediaPlayer mediaPlayer;
    String sound = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";

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
                sound = instrument.getSound();
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
        finish();
    }

    public void onGoBack(View view) {
        finish();
    }

    public void onTestSound(View view) {
        if(mediaPlayer == null ){
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioAttributes(new AudioAttributes
                    .Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build());
            try {
                mediaPlayer.setDataSource(sound);
                mediaPlayer.prepare();
                mediaPlayer.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show();
        } else {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
                Toast.makeText(this,"Audio has been paused", Toast.LENGTH_SHORT).show();
            } else {
                mediaPlayer = null;
                Toast.makeText(this,"Audio has not played", Toast.LENGTH_SHORT).show();
            }
        }
    }
}