package com.example.vploaia.musicapp;

import android.content.Context;

import java.util.List;

/**
 * Created by vploaia on 2/21/2017.
 */

public class LocalTrackService implements TrackService {

    private final Context context;

    public LocalTrackService(Context context) {
        this.context = context;
    }

    @Override
    public void searchTracks(String searchTerm, SearchTrackResultCallback callback) {
        DatabaseHandler dbHandler = new DatabaseHandler(context);
        List<Track> tracks = dbHandler.searchTracks(searchTerm);
        if (callback != null) {
            callback.onSearchTrackResult(tracks);
        }
    }
}
