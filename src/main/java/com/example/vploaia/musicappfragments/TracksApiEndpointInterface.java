package com.example.vploaia.musicappfragments;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vploaia on 3/1/2017.
 */

public interface TracksApiEndpointInterface {
    @GET("search")
    Call<TrackResult> getTracksList(@Query(value = "term", encoded = true) String artistName);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
