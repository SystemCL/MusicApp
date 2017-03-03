package com.example.vploaia.musicappfragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vploaia.musicapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by vploaia on 3/1/2017.
 */

public class TrackAdapter extends ArrayAdapter<Track> {
    public TrackAdapter(Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            view = inflater.inflate(R.layout.track_item_view, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.trackName = (TextView) view.findViewById(R.id.track_item_title);
            viewHolder.artistName = (TextView) view.findViewById(R.id.track_item_artist);
            viewHolder.trackTimeMillis = (TextView) view.findViewById(R.id.track_item_duration);
            viewHolder.artworkUrl60 = (ImageView) view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        } else {
            view = convertView;
        }
        final ViewHolder holder = (ViewHolder) view.getTag();
        final Track track = getItem(position);
        holder.trackName.setText(track.getTrackName());
        holder.artistName.setText(track.getArtistName());
        holder.trackTimeMillis.setText(String.valueOf(track.getTrackTimeMillisConverted()));
        Picasso.with(getContext()).load(track.getArtworkUrl60()).error(R.mipmap.ic_error_image).into(holder.artworkUrl60);
        return view;
    }


    @Override
    public long getItemId(int position) {
        return getItem(position).getTrackId();
    }

    static class ViewHolder {
        TextView trackName;
        TextView artistName;
        TextView trackTimeMillis;
        ImageView artworkUrl60;


    }
}
