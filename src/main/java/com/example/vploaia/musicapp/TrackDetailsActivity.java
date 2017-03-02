package com.example.vploaia.musicapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class TrackDetailsActivity extends AppCompatActivity {

    public static Track track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);


        int sync = getIntent().getIntExtra("bigdata:synccode", -1);
        final Track bigData = ResultIPC.get().getLargeData(sync);

        if (bigData.isOffline) {
            Button rateButton = (Button) findViewById(R.id.rateTrackButton);
            rateButton.setVisibility(View.VISIBLE);

        }


        TextView trackNameTextView = (TextView) findViewById(R.id.trackName);
        trackNameTextView.setText(bigData.getTrackName());
        TextView artistNameTextView = (TextView) findViewById(R.id.artistName);
        artistNameTextView.setText(bigData.getArtistName());
        TextView trackTimeMillisTextView = (TextView) findViewById(R.id.trackTimeMillis);
        trackTimeMillisTextView.setText(String.valueOf(bigData.getTrackTimeMillisConverted()));
        ImageView fullImageView = (ImageView) findViewById(R.id.fullImage);
        // if(bigData.getIsOffline() != "true") {
        Picasso.with(TrackDetailsActivity.this).load(bigData.getArtworkUrl100()).error(R.mipmap.ic_error_image).into(fullImageView);
        //  } else {
        //       Picasso.with(TrackDetailsActivity.this).load(bigData.getArtworkUrl100()).networkPolicy(NetworkPolicy.OFFLINE).into(fullImageView);
        //   }


    }

    public void sendTrackToRate(View view) {
        // Do something in response to button
        Intent intent = new Intent(TrackDetailsActivity.this, RateTrackActivity.class);
        startActivity(intent);

    }





}
