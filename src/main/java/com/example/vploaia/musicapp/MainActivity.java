package com.example.vploaia.musicapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TimeUtils;
import android.view.Menu;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Track> tracksAdapter;

    private ListView listViewTracks;

    TypedArray artworkUrl60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupListView();
        setupSearchView();
        setupListViewListener();

    }

    private void setupListView() {
        listViewTracks = (ListView) findViewById(R.id.list_items);
        registerForContextMenu(listViewTracks);
        tracksAdapter = new TrackAdapter(MainActivity.this);
        listViewTracks.setAdapter(tracksAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    private void setupListViewListener() {

        listViewTracks.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int pos, long id) {

                        Track track = tracksAdapter.getItem(pos);
                        String trackName = track.getTrackName();
                        String artistName = track.getArtistName();
                        String trackTimeMillis = track.getTrackTimeMillis();

                        Intent i = new Intent(item.getContext(), TrackDetailsActivity.class);
                        i.putExtra("trackName", trackName);
                        i.putExtra("artistName", artistName);
                        i.putExtra("trackTimeMillis", trackTimeMillis);
                        startActivity(i);


                    }

                });
    }


    private void setupSearchView() {
        final SearchView searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search Here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {

                RetrieveTrackWeb.getInstance().retrieveTracks(query, new SearchTrackResultCallback() {
                    @Override
                    public void onSearchTrackResult(List<Track> tracks) {

                            tracksAdapter.clear();
                            tracksAdapter.addAll(tracks);
                            tracksAdapter.notifyDataSetChanged();
                    }

                });
                return true;
            }
        });
    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

