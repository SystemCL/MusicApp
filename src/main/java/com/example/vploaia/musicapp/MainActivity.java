package com.example.vploaia.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.LayoutParams;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    protected ArrayAdapter<Track> tracksAdapter;

    private ListView listViewTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupListView();
        setupSearchView();
    }

    private void setupListView() {
        listViewTracks = (ListView) findViewById(R.id.list_items);
        registerForContextMenu(listViewTracks);
        tracksAdapter = new TrackAdapter(this);
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
                        Intent i = new Intent(item.getContext(), TrackDetailsActivity.class);
                        i.putExtra("trackDetails", track);
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
                    public void onSearchTrackResult(List<Track> tracks) { //List<Track>
                        tracksAdapter.clear();
                        tracksAdapter.addAll(tracks);
                        tracksAdapter.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });
    }
}

