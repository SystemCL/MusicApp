package com.example.vploaia.musicapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Track> tracksAdapter;

    private ListView listViewTracks;

    private TrackService trackService = WebTrackService.getInstance();


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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_local:
                trackService = new LocalTrackService(this);
                break;

            case R.id.item_web:
                trackService = WebTrackService.getInstance();
                break;
        }
        return true;
    }

    private void setupListViewListener() {

        listViewTracks.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int position, long id) {
                        Track track = tracksAdapter.getItem(position);
                        Intent intent = new Intent(MainActivity.this, TrackDetailsActivity.class);
                        int sync = ResultIPC.get().setLargeData(track);
                        intent.putExtra("bigdata:synccode", sync);
                        startActivity(intent);

                    }

                });


        listViewTracks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, final int position, long id) {
                final Track track = tracksAdapter.getItem(position);

                if (tracksAdapter.getItem(position).isOffline != false) {
                    new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog))
                            .setTitle("Alert")
                            .setMessage("Do you want to delete the track info from device ?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ((TrackAdapter) listViewTracks.getAdapter()).remove(track);
                                    deleteTrackFromLocal(track.getTrackName(), track.getArtistName());

                                }

                            }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {

                    new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog))
                            .setTitle("Alert")
                            .setMessage("Do you like to save the track info on device ?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    insertTrackToDb(position);
                                    ((TrackAdapter) listViewTracks.getAdapter()).remove(track);
                                }


                            }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

                return true;
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
                trackService.searchTracks(query, new SearchTrackResultCallback() {
                    @Override
                    public void onSearchTrackResult(List<Track> tracks) {
                        TextView emptyListTextView = (TextView) findViewById(R.id.empty_list_item);
                        if(tracks.isEmpty()){
                            emptyListTextView.setVisibility(View.VISIBLE);
                            emptyListTextView.setTextSize(30);
                            emptyListTextView.setText("No such items found");
                        } else {
                            emptyListTextView.setVisibility(View.GONE);
                        }
                        tracksAdapter.clear();
                        tracksAdapter.addAll(tracks);
                        tracksAdapter.notifyDataSetChanged();
                    }
                });
                return true;
            }
        });
    }


    public void insertTrackToDb(int position) {
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        Track track = tracksAdapter.getItem(position);
        tracksAdapter.getItem(position).setOffline(true);
        dbHandler.addTrack(track);
        String trackFullName = track.getArtistName() + " - " + track.getTrackName() + " saved!";
        Toast.makeText(getApplicationContext(), trackFullName, Toast.LENGTH_SHORT).show();


    }

    public void deleteTrackFromLocal(String trackName, String artistName) {
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        dbHandler.deleteTrack(trackName, artistName);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}

