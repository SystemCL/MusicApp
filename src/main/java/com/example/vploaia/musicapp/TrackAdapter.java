package com.example.vploaia.musicapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

/**
 * Created by vploaia on 2/17/2017.
 */

public class TrackAdapter extends ArrayAdapter<Track> {

    public TrackAdapter(Context context) {
        super(context, 0);
    }
    

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate(R.layout.track_item_view, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.trackName = (TextView) view.findViewById(R.id.track_item_title);
            viewHolder.artistName = (TextView) view.findViewById(R.id.track_item_artist);
            viewHolder.trackTimeMillis = (TextView) view.findViewById(R.id.track_item_duration);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        Track track = getItem(position);
        holder.trackName.setText(track.getTrackName());
        holder.artistName.setText(track.getArtistName());
        holder.trackTimeMillis.setText(track.getTrackTimeMillis());
        return view;
    }


    @Override
    public long getItemId(int position) {
        return getItem(position).getTrackId();
    }

    static class ViewHolder {
        protected TextView trackName;
        protected TextView artistName;
        protected TextView trackTimeMillis;

    }

    @Override
    public void notifyDataSetChanged() // Create this function in your adapter class
    {
        super.notifyDataSetChanged();
    }
}
