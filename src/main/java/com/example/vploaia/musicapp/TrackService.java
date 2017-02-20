package com.example.vploaia.musicapp;

/**
 * Created by vploaia on 2/20/2017.
 */

public interface TrackService {
     public void retrieveTracks(String searchTerm, SearchTrackResultCallback callback);
}
