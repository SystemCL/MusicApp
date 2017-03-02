package com.example.vploaia.musicappfragments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

/**
 * Created by vploaia on 3/1/2017.
 */

public class Track implements Serializable {

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
    @SerializedName("isoffline")
    public boolean isOffline;
    @SerializedName("ratetrack")
    public double rateTrack;

    public Integer getTrackId() { return trackId; }

    public void setTrackId(int trackId) { this.trackId = trackId; }

    public String getTrackName () { return trackName; }

    public void setTrackName (String trackName) { this.trackName = trackName; }

    public String getArtistName () { return artistName; }

    public void setArtistName (String artistName) { this.artistName = artistName; }

/*    public String getTrackTimeMillis () {

        //String value = trackTimeMillis;
        //String time = new SimpleDateFormat("mm:ss").format(Long.parseLong(value));
        String time = new SimpleDateFormat("mm:ss").format(trackTimeMillis);
        return time;


    }*/

    public long getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public String getTrackTimeMillisConverted() {
        String hms = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(trackTimeMillis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(trackTimeMillis)),
                TimeUnit.MILLISECONDS.toSeconds(trackTimeMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(trackTimeMillis)));
        return hms;
    }

    public void setTrackTimeMillis (long trackTimeMillis) { this.trackTimeMillis = trackTimeMillis; }

    public String getArtworkUrl60() { return artworkUrl60; }

    public void setArtworkUrl60(String artworkUrl60) { this.artworkUrl60 = artworkUrl60; }

    public String getArtworkUrl100() { return artworkUrl100; }

    public void setArtworkUrl100(String artworkUrl100) { this.artworkUrl100 = artworkUrl100; }

    public boolean getIsOffline() { return isOffline; }

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
