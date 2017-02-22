package com.example.vploaia.musicapp;

import java.util.List;

/**
 * Created by vploaia on 2/22/2017.
 */

public class ResultIPC {
    private static ResultIPC instance;

    public synchronized static ResultIPC get() {
        if (instance == null) {
            instance = new ResultIPC ();
        }
        return instance;
    }

    private int sync = 0;

    private Track track;
    public int setLargeData(Track track) {
        this.track = track;
        return ++sync;
    }

    public Track getLargeData(int request) {
        return (request == sync) ? track : null;
    }
}
