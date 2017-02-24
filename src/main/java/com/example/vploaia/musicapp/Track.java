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

/**
 * Created by vploaia on 2/17/2017.
 */

public class Track implements Parcelable{

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
    public String isOffline = "false";

    public Integer getTrackId() { return trackId; }

    public void setTrackId(int trackId) { this.trackId = trackId; }

    public String getTrackName () { return trackName; }

    public void setTrackName (String trackName) { this.trackName = trackName; }

    public String getArtistName () { return artistName; }

    public void setArtistName (String artistName) { this.artistName = artistName; }

    public String getTrackTimeMillis () {

        String time = new SimpleDateFormat("mm:ss").format(trackTimeMillis);
        return time;
    }

    public void setTrackTimeMillis (long trackTimeMillis) { this.trackTimeMillis = trackTimeMillis; }

    public String getArtworkUrl60() { return artworkUrl60; }

    public void setArtworkUrl60(String artworkUrl60) { this.artworkUrl60 = artworkUrl60; }

    public String getArtworkUrl100() { return artworkUrl100; }

    public void setArtworkUrl100(String artworkUrl100) { this.artworkUrl100 = artworkUrl100; }

    public String getIsOffline() { return isOffline; }

    public void setIsOffline(String isoffline) { this.isOffline = isoffline; }

    public Track() { }

    public Track(int trackId, String trackTitle, String artistName, long trackTimeMillis, String artworkUrl60, String artworkUrl100, String isOffline ) {
        this.trackId = trackId;
        this.trackName = trackTitle;
        this.artistName = artistName;
        this.trackTimeMillis = trackTimeMillis;
        this.artworkUrl60 = artworkUrl60;
        this.artworkUrl100 = artworkUrl100;
        this.isOffline = isOffline;
    }

   @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(trackId);
        dest.writeString(trackName);
        dest.writeString(artistName);
        dest.writeLong(trackTimeMillis);
        dest.writeString(artworkUrl60);
        dest.writeString(artworkUrl100);
        dest.writeString(isOffline);
    }

    protected Track(Parcel in) {
        this.trackId = in.readInt();
        this.trackName = in.readString();
        this.artistName = in.readString();
        this.trackTimeMillis = in.readLong();
        this.artworkUrl60 = in.readString();
        this.artworkUrl100 = in.readString();
    }

    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {

        @Override
        public Track createFromParcel(Parcel in) { return new Track(in); }

        @Override
        public Track[] newArray(int size) { return new Track[size]; }

    };

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+trackId+", trackName = "+trackName+", artistName = "+artistName+", trackTimeMillis = "+trackTimeMillis+", artworkUrl60 = "+artworkUrl60+", artworkUrl100 = "+artworkUrl100+", isOffline = " +isOffline +"]";
    }






}
