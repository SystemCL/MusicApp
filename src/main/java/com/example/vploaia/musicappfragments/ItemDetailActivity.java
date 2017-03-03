package com.example.vploaia.musicappfragments;

import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vploaia.musicapp.R;

public class ItemDetailActivity extends FragmentActivity {
    ItemDetailFragment fragmentItemDetail;
    public static Track track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        if (track.isOffline) {
            Button rateButton = (Button) findViewById(R.id.rateTrackButton);
            rateButton.setVisibility(View.VISIBLE);
        }

        if (savedInstanceState == null) {
            //insert detail fragment based on the item passed
            fragmentItemDetail = ItemDetailFragment.newInstance(track);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.activity_item_detail_container, fragmentItemDetail);
            fragmentTransaction.commit();

        }

    }


}
