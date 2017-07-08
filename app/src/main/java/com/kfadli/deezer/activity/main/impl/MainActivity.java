package com.kfadli.deezer.activity.main.impl;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.kfadli.deezer.R;
import com.kfadli.deezer.activity.base.BaseActivity;
import com.kfadli.deezer.activity.main.AlbumsAdapter;
import com.kfadli.deezer.activity.main.IMainPresenter;
import com.kfadli.deezer.activity.main.IMainView;
import com.kfadli.deezer.model.Album;

import java.util.List;

public class MainActivity extends BaseActivity<IMainView, IMainPresenter> implements IMainView {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.mFloatingButton = (FloatingActionButton) findViewById(R.id.fab);
        this.mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().clearCache();
            }
        });


        this.mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().getDataContent(getString(R.string.api));
        }
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected MainActivity getView() {
        return this;
    }

    @Override
    protected int getContentLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void showToastMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showSnackMessage(String message) {
        Snackbar.make(this.mFloatingButton, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void updateAlbums(List<Album> albums) {
        mRecyclerView.setAdapter(new AlbumsAdapter(getCache(), getImageHelper(), albums));
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

}
