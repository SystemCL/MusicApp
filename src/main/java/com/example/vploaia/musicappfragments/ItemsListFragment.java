package com.example.vploaia.musicappfragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vploaia.musicapp.R;

import java.util.List;

import static android.support.v7.appcompat.R.styleable.CompoundButton;

/**
 * Created by vploaia on 3/1/2017.
 */

public class ItemsListFragment extends Fragment {
    private ArrayAdapter<Track> tracksAdapter;
    private OnListItemSelectedListener listener;
    private ListView listViewTracks;
    private TrackService trackService = WebTrackService.getInstance();


    public interface OnListItemSelectedListener {
        void onItemSelected(Track track);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a = null;

        if (context instanceof Activity) {
            a = (Activity) context;
        }
        if (a instanceof OnListItemSelectedListener) {
            listener = (OnListItemSelectedListener) a;
        } else {
            throw new ClassCastException(
                    a.toString()
                            + " must implement ItemsListFragment.OnListItemSelectedListener");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tracksAdapter = new TrackAdapter(getActivity());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_local:
                trackService = new LocalTrackService(getContext());
                break;

            case R.id.item_web:
                showSnackbarInternetStatus();
                trackService = WebTrackService.getInstance();
                break;
        }
        return true;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items_list, container, false);
        listViewTracks = (ListView) view.findViewById(R.id.list_items);
        registerForContextMenu(listViewTracks);
        listViewTracks.setAdapter(tracksAdapter);

        setupListViewListener();

        final SearchView searchView = (SearchView) view.findViewById(R.id.search_view);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search Here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {

                TextView view = (TextView) getView().findViewById(R.id.empty_list_item);
                view.setVisibility(View.GONE);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                trackService.searchTracks(query, new SearchTrackResultCallback() {
                    @Override
                    public void onSearchTrackResult(List<Track> tracks) {
                        TextView emptyListTextView = (TextView) getView().findViewById(R.id.empty_list_item);
                        if (tracks.isEmpty()) {
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
        return view;
    }


    private void setupListViewListener() {

        listViewTracks.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                            View item, int position, long id) {
                        Track track = tracksAdapter.getItem(position);
                        listener.onItemSelected(track);

                    }

                });


        listViewTracks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, final int position, long id) {
                final Track track = tracksAdapter.getItem(position);

                if (tracksAdapter.getItem(position).isOffline != false) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog))
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

                    new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.myDialog))
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


    public void insertTrackToDb(int position) {
        DatabaseHandler dbHandler = new DatabaseHandler(getActivity());
        Track track = tracksAdapter.getItem(position);
        tracksAdapter.getItem(position).setOffline(true);
        dbHandler.addTrack(track);
        String trackFullName = track.getArtistName() + " - " + track.getTrackName() + " saved!";
        Toast.makeText(getActivity(), trackFullName, Toast.LENGTH_SHORT).show();


    }

    public void deleteTrackFromLocal(String trackName, String artistName) {
        DatabaseHandler dbHandler = new DatabaseHandler(getActivity());
        dbHandler.deleteTrack(trackName, artistName);
    }


    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        listViewTracks.setChoiceMode(
                activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
                        : ListView.CHOICE_MODE_NONE);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void showSnackbarInternetStatus(){
        if(isNetworkAvailable() == false) {

            ListView view = (ListView) getView().findViewById(R.id.list_items);
            view.setVisibility(View.GONE);
            final View viewPos = getActivity().findViewById(R.id.myCoordinatorLayout);
            Snackbar snackbar = Snackbar.make(viewPos, "No internet connection", Snackbar.LENGTH_LONG).setAction("SWITCH_WIFI", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //enableWifi(true);
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));


                }
            });
            snackbar.show();
        }
    }

   /* private void enableWifi(boolean status) {
        WifiManager wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        if(status == true && !wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        } else if (status == false && wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        }

    }*/





}
