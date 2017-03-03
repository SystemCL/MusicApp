package com.example.vploaia.musicappfragments;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vploaia on 3/1/2017.
 */

public class WebTrackService implements TrackService {
    private static TrackService instance = new WebTrackService();
    private TracksApiEndpointInterface service;

    private WebTrackService() {
        service = TracksApiEndpointInterface.retrofit.create(TracksApiEndpointInterface.class);
    }

    public static TrackService getInstance() {
        return instance;
    }

    @Override
    public void searchTracks(final String searchTerm, final SearchTrackResultCallback callback) {

        Call<TrackResult> call = service.getTracksList(searchTerm);
        call.enqueue(new Callback<TrackResult>() {
            @Override
            public void onResponse(Call<TrackResult> call, Response<TrackResult> response) {

                if (callback != null) {
                    callback.onSearchTrackResult(response.body().results);
                }
            }

            @Override
            public void onFailure(Call<TrackResult> call, Throwable t) {
                Log.e("E", t.getMessage(), t);
        }
        });




    }
}
