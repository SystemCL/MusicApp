package com.example.vploaia.musicapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrackDetailsActivity extends AppCompatActivity {

    public static Track data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);

        /*TrackDetailsActivity.data =

        Bundle extras = getIntent().getExtras();

        String trackName = extras.getString("trackName");
        String artistName = extras.getString("artistName");
        String trackTimeMillis = extras.getString("trackTimeMillis");

        TextView trackNameTextView = (TextView) findViewById(R.id.trackName);
        trackNameTextView.setText(trackName);
        TextView artistNameTextView = (TextView) findViewById(R.id.artistName);
        artistNameTextView.setText(artistName);
        TextView trackTimeMillisTextView = (TextView) findViewById(R.id.trackTimeMillis);
        trackTimeMillisTextView.setText(trackTimeMillis);*/

/*        Intent intent = getIntent();
        String trackName = intent.getStringExtra("trackName");
        String artistName = intent.getStringExtra("artistName");
        String trackTimeMillis = intent.getStringExtra("trackTimeMillis");


        TextView trackNameTextView = (TextView) findViewById(R.id.trackName);
        trackNameTextView.setText(trackName);
        TextView artistNameTextView = (TextView) findViewById(R.id.artistName);
        artistNameTextView.setText(artistName);
        TextView trackTimeMillisTextView = (TextView) findViewById(R.id.trackTimeMillis);
        trackTimeMillisTextView.setText(trackTimeMillis);*/

        int sync = getIntent().getIntExtra("bigdata:synccode", -1);
        final Track bigData = ResultIPC.get().getLargeData(sync);
        TextView trackNameTextView = (TextView) findViewById(R.id.trackName);
        trackNameTextView.setText(bigData.getTrackName());
        TextView artistNameTextView = (TextView) findViewById(R.id.artistName);
        artistNameTextView.setText(bigData.getArtistName());
        TextView trackTimeMillisTextView = (TextView) findViewById(R.id.trackTimeMillis);
        trackTimeMillisTextView.setText(bigData.getTrackTimeMillis());
        ImageView fullImageView = (ImageView) findViewById(R.id.fullImage);
        Picasso.with(TrackDetailsActivity.this).load(bigData.getArtworkUrl100()).error(R.mipmap.ic_error_image).into(fullImageView);









    }





}
