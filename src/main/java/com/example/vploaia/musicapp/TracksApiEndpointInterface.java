package com.example.vploaia.musicapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vploaia on 2/17/2017.
 */

public interface TracksApiEndpointInterface {

    @GET("search")
    Call<TrackResult> getTracksList(@Query(value = "term", encoded = true) String artistName); //TrackResult

     Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();





}
