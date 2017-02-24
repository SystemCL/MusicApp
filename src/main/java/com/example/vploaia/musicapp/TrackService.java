package com.example.vploaia.musicapp;

import java.util.List;

/**
 * Created by vploaia on 2/20/2017.
 */

public interface TrackService {
     void searchTracks(String searchTerm, SearchTrackResultCallback callback);
}
