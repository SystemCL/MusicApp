package com.example.vploaia.musicapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RateTrackActivity extends AppCompatActivity {


    RatingBar ratingBar1;
    Button button;
    TextView rateTrackTextView;
    Track track;
    private ArrayAdapter<Track> tracksAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_rate);
        tracksAdapter = new TrackAdapter(RateTrackActivity.this);

        addListenerOnButtonSubmitClick();

    }

    public void addListenerOnButtonSubmitClick(){
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar);
        button = (Button) findViewById(R.id.submitRateButton);
        rateTrackTextView = (TextView) findViewById(R.id.rateResultTextView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateTrack();
                String rating = String.valueOf(ratingBar1.getRating());
                rateTrackTextView.setText(rating);
            }
        });
    }


    //nu lucreaza
    public void updateTrack(float rateValue) {
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        track.setRateTrack(rateValue);
        dbHandler.updateTrack(track);
        String trackFullName = track.getArtistName() + " - " + track.getTrackName() + " updated!";
        Toast.makeText(getApplicationContext(), trackFullName, Toast.LENGTH_SHORT).show();
    }
}
