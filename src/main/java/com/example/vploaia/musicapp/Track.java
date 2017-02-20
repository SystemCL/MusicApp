package com.example.vploaia.musicapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vploaia on 2/17/2017.
 */

public class Track implements Parcelable {

    public int trackId;
    @SerializedName("trackName")
    public String trackName;
    @SerializedName("artistName")
    protected String artistName;
    @SerializedName("trackTimeMillis")
    protected String trackTimeMillis;

    public int getTrackId() { return trackId; }

    public void setTrackId(int trackId) { this.trackId = trackId; }

    public String getTrackName () { return trackName; }

    public void setTrackName (String trackName) { this.trackName = trackName; }

    public String getArtistName () { return artistName; }

    public void setArtistName (String artistName) { this.artistName = artistName; }

    public String getTrackTimeMillis () { return trackTimeMillis; }

    public void setTrackTimeMillis (String trackTimeMillis) { this.trackTimeMillis = trackTimeMillis; }

    public Track() { }

    public Track(int trackId, String trackTitle, String artistName, String trackTimeMillis) {
        this.trackId = trackId;
        this.trackName = trackTitle;
        this.artistName = artistName;
        this.trackTimeMillis = trackTimeMillis;
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
        dest.writeString(trackTimeMillis);
    }

    protected Track(Parcel in) {
        this.trackId = in.readInt();
        this.trackName = in.readString();
        this.artistName = in.readString();
        this.trackTimeMillis = in.readString();
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
        return "ClassPojo [id = "+trackId+", trackName = "+trackName+", artistName = "+artistName+", trackTimeMillis = "+trackTimeMillis+"]";
    }



}
