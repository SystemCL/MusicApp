package com.example.vploaia.musicapp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vploaia on 2/20/2017.
 */

public class RetrieveTrackWeb extends AppCompatActivity implements TrackService {

    private static TrackService instance = new RetrieveTrackWeb();

    public static TrackService getInstance() {
        return instance;
    }

    @Override
    public void retrieveTracks(String searchTerm, final SearchTrackResultCallback callback) {

        TracksApiEndpointInterface service = TracksApiEndpointInterface.retrofit.create(TracksApiEndpointInterface.class);

        Call<TrackResult> call = service.getTracksList(searchTerm);

        call.enqueue(new Callback<TrackResult>() {
            @Override
            public void onResponse(Call<TrackResult> call, Response<TrackResult> response) {

                if (callback != null) {
                    callback.onSearchTrackResult(response.body().getTracks());
                }
            }

            @Override
            public void onFailure(Call<TrackResult> call, Throwable t) {
                Log.e("E", t.getMessage(), t);
            }
        });

    }
}
