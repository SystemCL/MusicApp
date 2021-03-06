package com.example.vploaia.musicapp;

import android.database.DatabaseUtils;
import android.icu.util.TimeUnit;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.text.format.DateFormat.getDateFormat;

/**
 * Created by vploaia on 2/17/2017.
 */

public class Track {
    public int trackId;
    @SerializedName("trackName")
    public String trackName;
    @SerializedName("artistName")
    public String artistName;
    @SerializedName("trackTimeMillis")
    public long trackTimeMillis;
    @SerializedName("artworkUrl60")
    public String artworkUrl60;
    @SerializedName("artworkUrl100")
    public String artworkUrl100;
    public boolean isOffline;

    public double rateTrack;

    public Integer getTrackId() { return trackId; }

    public void setTrackId(int trackId) { this.trackId = trackId; }

    public String getTrackName () { return trackName; }

    public void setTrackName (String trackName) { this.trackName = trackName; }

    public String getArtistName () { return artistName; }

    public void setArtistName (String artistName) { this.artistName = artistName; }

    public long getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public String getTrackTimeMillisConverted() {
        String hms = String.format("%02d:%02d", java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(trackTimeMillis) - java.util.concurrent.TimeUnit.HOURS.toMinutes(java.util.concurrent.TimeUnit.MILLISECONDS.toHours(trackTimeMillis)),
                java.util.concurrent.TimeUnit.MILLISECONDS.toSeconds(trackTimeMillis) - java.util.concurrent.TimeUnit.MINUTES.toSeconds(java.util.concurrent.TimeUnit.MILLISECONDS.toMinutes(trackTimeMillis)));
        return hms;
    }

    public void setTrackTimeMillis (long trackTimeMillis) { this.trackTimeMillis = trackTimeMillis; }

    public String getArtworkUrl60() { return artworkUrl60; }

    public void setArtworkUrl60(String artworkUrl60) { this.artworkUrl60 = artworkUrl60; }

    public String getArtworkUrl100() { return artworkUrl100; }

    public void setArtworkUrl100(String artworkUrl100) { this.artworkUrl100 = artworkUrl100; }

    public boolean getIsOffline() {
        return isOffline;
    }

    public void setOffline(boolean isoffline) { this.isOffline = isoffline; }

    public double getRateTrack() { return rateTrack; }

    public void setRateTrack(double rateTrack) { this.rateTrack = rateTrack; }

    public Track() { }

    public Track(int trackId, String trackTitle, String artistName, Long trackTimeMillis, String artworkUrl60, String artworkUrl100, boolean isOffline ) {
        this.trackId = trackId;
        this.trackName = trackTitle;
        this.artistName = artistName;
        this.trackTimeMillis = trackTimeMillis;
        this.artworkUrl60 = artworkUrl60;
        this.artworkUrl100 = artworkUrl100;
        this.isOffline = isOffline;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+trackId+", trackName = "+trackName+", artistName = "+artistName+", trackTimeMillis = "+trackTimeMillis+", artworkUrl60 = "+artworkUrl60+", artworkUrl100 = "+artworkUrl100+", isOffline = " +isOffline +"]";
    }


}
