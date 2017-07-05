package com.kfadli.deezer.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kfadli.deezer.R;
import com.kfadli.deezer.AlbumCoversApplication;
import com.kfadli.deezer.helper.ImageHelper;
import com.kfadli.deezer.services.cache.CacheManager;
import com.kfadli.deezer.services.executor.task.GetJsonCallable;
import com.kfadli.deezer.enums.PriorityEnum;
import com.kfadli.deezer.services.executor.TaskPoolManager;

import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CacheManager mCacheManager;
    private ImageHelper mImageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mCacheManager = ((AlbumCoversApplication) getApplication()).getCache();
        mImageHelper = ((AlbumCoversApplication) getApplication()).getImageHelper();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCacheManager.clear();

                Snackbar.make(view, "Cache cleared", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        this.mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    protected void onResume() {
        super.onResume();

        FutureTask data = new FutureTask(new GetJsonCallable(this.mCacheManager, this.mImageHelper, mRecyclerView, getString(R.string.api)));
        TaskPoolManager.THREAD_POOL_EXECUTOR.execute(data);

    }
}
