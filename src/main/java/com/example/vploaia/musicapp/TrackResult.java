package com.example.vploaia.musicapp;

import java.util.List;

/**
 * Created by vploaia on 2/17/2017.
 */
public class TrackResult {

    public static List<Track> results;

    private String trackCount;

    public List<Track> getTracks ()
    {
        return results;
    }

    public void setTracks (List<Track> tracks)
    {
        this.results = tracks;
    }

    public String getTrackCount ()
    {
        return trackCount;
    }

    public void setTrackCount (String trackCount)
    {
        this.trackCount = trackCount;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tracks = "+results+", trackCount = "+trackCount+"]";
    }
}
