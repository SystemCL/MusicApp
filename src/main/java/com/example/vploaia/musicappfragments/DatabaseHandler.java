package com.example.vploaia.musicappfragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vploaia on 3/1/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "tracksdb";

    private static final String TABLE_TRACKS = "tracks";

    private static final String KEY_ID = "id";
    private static final String KEY_TRACK_NAME = "trackname";
    private static final String KEY_ARTIST_NAME = "artistname";
    private static final String KEY_TRACK_TIME_MILLIS = "tracktimemillis";
    private static final String KEY_ARTWORK_URL_60 = "artworkurl60";
    private static final String KEY_ARTWORK_URL_100 = "artworkurl100";
    private static final String KEY_ISOFFLINE = "isoffline";
    private static final String KEY_RATE = "rate";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRACKS_TABLE = "CREATE TABLE " + TABLE_TRACKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TRACK_NAME + " TEXT,"
                + KEY_ARTIST_NAME + " TEXT," + KEY_TRACK_TIME_MILLIS + " INTEGER,"
                + KEY_ARTWORK_URL_60 + " TEXT," + KEY_ARTWORK_URL_100 + " TEXT,"
                + KEY_ISOFFLINE + " BIT," + KEY_RATE +" REAL" + ")";

        db.execSQL(CREATE_TRACKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DROP older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRACKS);

        //create tables again
        onCreate(db);
    }

    public void addTrack(Track track) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, track.getTrackId());
        values.put(KEY_TRACK_NAME, track.getTrackName());
        values.put(KEY_ARTIST_NAME, track.getArtistName());
        values.put(KEY_TRACK_TIME_MILLIS, track.getTrackTimeMillis());
        values.put(KEY_ARTWORK_URL_60, track.getArtworkUrl60());
        values.put(KEY_ARTWORK_URL_100, track.getArtworkUrl100());
        values.put(KEY_ISOFFLINE, track.getIsOffline());
        values.put(KEY_RATE, track.getRateTrack());

        db.insert(TABLE_TRACKS, null, values);
        db.close();
    }

//    public Track getTrack(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_TRACKS, new String[]{KEY_ID,
//                        KEY_TRACK_NAME, KEY_ARTIST_NAME, KEY_TRACK_TIME_MILLIS,
//                        KEY_ARTWORK_URL_60, KEY_ARTWORK_URL_100}, KEY_ID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Track track = new Track(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
//                cursor.getString(2), Long.parseLong(cursor.getString(3)),
//                cursor.getString(4), cursor.getString(5), cursor.getString(6));
//        return track;
//    }

    public List<Track> getAllTracks() {
        List<Track> trackList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TRACKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        Track track = null;
        if (cursor.moveToFirst()) {
            do {
                track = new Track();
                track.setTrackId(Integer.parseInt(cursor.getString(0)));
                track.setTrackName(cursor.getString(1));
                track.setArtistName(cursor.getString(2));
                track.setTrackTimeMillis(cursor.getLong(3));
                track.setArtworkUrl60(cursor.getString(4));
                track.setArtworkUrl100(cursor.getString(5));
                trackList.add(track);
            } while (cursor.moveToNext());
        }

        return trackList;
    }

    public List<Track> searchTracks(String searchTerm) {
        List<Track> trackList = new ArrayList<>();

        String whereClause1 = "trackname LIKE ";

        String selectQuery = "SELECT * FROM " + TABLE_TRACKS + " WHERE " + whereClause1 + "'%" + searchTerm + "%'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Track track = new Track();
                track.setTrackName(cursor.getString(cursor.getColumnIndex(KEY_TRACK_NAME)));
                track.setArtistName(cursor.getString(cursor.getColumnIndex(KEY_ARTIST_NAME)));
                track.setTrackTimeMillis(cursor.getLong(cursor.getColumnIndex(KEY_TRACK_TIME_MILLIS)));
                track.setArtworkUrl60(cursor.getString(cursor.getColumnIndex(KEY_ARTWORK_URL_60)));
                track.setArtworkUrl100(cursor.getString(cursor.getColumnIndex(KEY_ARTWORK_URL_100)));
                track.setOffline(cursor.getInt(cursor.getColumnIndex(KEY_ISOFFLINE)) != 0);
                track.setRateTrack(cursor.getFloat(cursor.getColumnIndex(KEY_RATE)));
                trackList.add(track);
            } while (cursor.moveToNext());
        }

        return trackList;
    }

    public void updateTrack(Track track) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TRACK_NAME, track.getRateTrack());
        values.put(KEY_ARTIST_NAME, track.getArtistName());
        values.put(KEY_TRACK_TIME_MILLIS, track.getTrackTimeMillis());
        values.put(KEY_ARTWORK_URL_60, track.getArtworkUrl60());
        values.put(KEY_ARTWORK_URL_100, track.getArtworkUrl100());
        values.put(KEY_ISOFFLINE, track.getIsOffline());
        values.put(KEY_RATE, track.getRateTrack());

        db.update(TABLE_TRACKS, values, KEY_TRACK_NAME + "= ? AND " + KEY_ARTIST_NAME + " = ?"
                , new String[] { String.valueOf(track.trackName), String.valueOf(track.artistName) });
        db.close();
        Log.d("updated", track.trackName + "was updated");
    }


    public void deleteTrack(String trackName, String artistName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRACKS, KEY_TRACK_NAME + " = ? AND " + KEY_ARTIST_NAME + " = ?",
                new String[]{String.valueOf(trackName), String.valueOf(artistName)});
        db.close();
        Log.d("deleted", trackName + "was deleted");

    }

}
