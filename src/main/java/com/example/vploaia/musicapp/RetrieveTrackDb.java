package com.example.vploaia.musicapp;

/**
 * Created by vploaia on 2/21/2017.
 */

public class RetrieveTrackDb implements TrackService {

    private static TrackService instance = new RetrieveTrackDb();

    public static TrackService getInstance() { return instance; }

    @Override
    public void retrieveTracks(String searchTerm, SearchTrackResultCallback callback) {

    }
}
