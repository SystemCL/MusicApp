package com.example.vploaia.musicappfragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.vploaia.musicapp.R;
import com.example.vploaia.musicappfragments.ItemsListFragment.OnListItemSelectedListener;

/**
 * Created by vploaia on 3/1/2017.
 */

public class ItemsListActivity extends AppCompatActivity implements OnListItemSelectedListener {
    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        determinePaneLayout();

    }


    private void determinePaneLayout() {
        FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.activity_item_detail_container);
        if (fragmentItemDetail != null) {
            isTwoPane = true;
            ItemsListFragment fragmentItemsList =
                    (ItemsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentItemsList);
            fragmentItemsList.setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(Track track) {
        if (isTwoPane) { // single activity with list and detail
            ItemDetailFragment fragmentItem = ItemDetailFragment.newInstance(track);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.activity_item_detail_container, fragmentItem);
            ft.commit();
        } else {
            ItemDetailActivity.track = track;
            startActivity(new Intent(ItemsListActivity.this, ItemDetailActivity.class));
        }
    }




}
