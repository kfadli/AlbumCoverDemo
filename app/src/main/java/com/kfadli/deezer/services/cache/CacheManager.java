package com.kfadli.deezer.services.cache;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

public class CacheManager implements ICache {

    private static final String TAG = "CacheManager";

    private FileCache mFileCache;
    private MemoryCache mMemoryCache;

    public CacheManager(Context context) {
        this.mFileCache = new FileCache(context);
        this.mMemoryCache = new MemoryCache();
    }

    public void setFileCache(FileCache mFileCache) {
        this.mFileCache = mFileCache;
    }

    public void setMemoryCache(MemoryCache mMemoryCache) {
        this.mMemoryCache = mMemoryCache;
    }

    @Override
    public Bitmap getBitmap(String key) {
        Bitmap bitmap = mMemoryCache.getBitmap(key);
        if (bitmap != null) {
            return bitmap;
        }

        bitmap = mFileCache.getBitmap(key);
        if (bitmap != null) {
            return bitmap;
        }

        return null;
    }

    @Override
    public void saveBitmap(String key, Bitmap bitmap) {
        mMemoryCache.saveBitmap(key, bitmap);
        mFileCache.saveBitmap(key, bitmap);
    }

    @Override
    public void deleteBitmap(String key) {
        mMemoryCache.deleteBitmap(key);
        mFileCache.deleteBitmap(key);
    }

    @Override
    public void clear() {
        mMemoryCache.clear();
        mFileCache.clear();
    }
}
