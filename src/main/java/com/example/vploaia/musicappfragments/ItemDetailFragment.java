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
import com.squareup.picasso.Picasso;

/**
 * Created by vploaia on 3/1/2017.
 */

public class ItemDetailFragment extends Fragment {

    private Track track;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        track = (Track) getArguments().getSerializable("track");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        //Attach the view for detail
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        TextView trackNameTextView = (TextView) view.findViewById(R.id.trackName);
        trackNameTextView.setText(track.getTrackName());
        TextView artistNameTextView = (TextView) view.findViewById(R.id.artistName);
        artistNameTextView.setText(track.getArtistName());
        TextView trackTimeMillisTextView = (TextView) view.findViewById(R.id.trackTimeMillis);
        trackTimeMillisTextView.setText(track.getTrackTimeMillis().toString());
        ImageView fullImageView = (ImageView) view.findViewById(R.id.fullImage);
        // if(bigData.getIsOffline() != "true") {
        Picasso.with(getActivity()).load(track.getArtworkUrl100()).error(R.mipmap.ic_error_image).into(fullImageView);
        //  } else {
        //       Picasso.with(TrackDetailsActivity.this).load(bigData.getArtworkUrl100()).networkPolicy(NetworkPolicy.OFFLINE).into(fullImageView);
        //   }

        return view;
    }

    public static ItemDetailFragment newInstance(Track track) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("track", track);
        itemDetailFragment.setArguments(args);
        return itemDetailFragment;

    }





}
