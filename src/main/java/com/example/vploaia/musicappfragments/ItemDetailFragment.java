package com.example.vploaia.musicappfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vploaia.musicapp.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by vploaia on 3/1/2017.
 */

public class ItemDetailFragment extends Fragment {

    public Track track;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Attach the view for detail
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        TextView trackNameTextView = (TextView) view.findViewById(R.id.trackName);
        trackNameTextView.setText(track.getTrackName());
        TextView artistNameTextView = (TextView) view.findViewById(R.id.artistName);
        artistNameTextView.setText(track.getArtistName());
        TextView trackTimeMillisTextView = (TextView) view.findViewById(R.id.trackTimeMillis);
        trackTimeMillisTextView.setText(String.valueOf(track.getTrackTimeMillisConverted()));
        ImageView fullImageView = (ImageView) view.findViewById(R.id.fullImage);
        Picasso.with(getActivity()).load(track.getArtworkUrl100()).error(R.mipmap.ic_error_image).into(fullImageView);


        return view;
    }

    public static ItemDetailFragment newInstance(Track track) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        itemDetailFragment.track = track;
        return itemDetailFragment;

    }


}
