package com.example.vploaia.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TrackDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);



        Bundle extras = getIntent().getExtras(); String val = extras.getString("fname");

        String trackName = extras.getString("trackName");
        String artistName = extras.getString("artistName");
        String trackTimeMillis = extras.getString("trackTimeMillis");

        TextView trackNameTextView = (TextView) findViewById(R.id.trackName);
        trackNameTextView.setText(trackName);
        TextView artistNameTextView = (TextView) findViewById(R.id.artistName);
        artistNameTextView.setText(artistName);

        TextView trackTimeMillisTextView = (TextView) findViewById(R.id.trackTimeMillis);
        trackTimeMillisTextView.setText(trackTimeMillis);


    }



}
