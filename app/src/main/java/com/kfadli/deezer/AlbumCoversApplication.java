package com.kfadli.deezer;

import android.app.Application;

import com.kfadli.deezer.helper.ImageHelper;
import com.kfadli.deezer.services.cache.CacheManager;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

public class AlbumCoversApplication extends Application {

    private CacheManager mCache;
    private ImageHelper mImageHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        this.mCache = new CacheManager(this);
        this.mImageHelper = new ImageHelper(this);
    }

    public CacheManager getCache() {
        return this.mCache;
    }

    public ImageHelper getImageHelper() {
        return this.mImageHelper;
    }
}
