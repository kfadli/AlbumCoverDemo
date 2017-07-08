package com.kfadli.deezer.activity.base;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kfadli.deezer.AlbumCoversApplication;
import com.kfadli.deezer.helper.ImageHelper;
import com.kfadli.deezer.services.cache.CacheManager;

/**
 * Created by Karim Fadli on 08/07/2017.
 */

public abstract class BaseActivity<V extends IView, P extends IPresenter> extends AppCompatActivity implements IView {

    protected P presenter;
    protected V view;

    private CacheManager mCacheManager;
    private ImageHelper mImageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutID());

        this.mCacheManager = ((AlbumCoversApplication) getApplication()).getCache();
        this.mImageHelper = ((AlbumCoversApplication) getApplication()).getImageHelper();

        presenter = createPresenter();
        view = getView();

        presenter.attachView(view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public CacheManager getCache() {
        return this.mCacheManager;
    }

    @Override
    public ImageHelper getImageHelper() {
        return this.mImageHelper;
    }

    @Override
    public Context getContext() {
        return this;
    }

    protected abstract P createPresenter();

    protected abstract V getView();

    protected abstract int getContentLayoutID();

    public P getPresenter() {
        return this.presenter;
    }

}
