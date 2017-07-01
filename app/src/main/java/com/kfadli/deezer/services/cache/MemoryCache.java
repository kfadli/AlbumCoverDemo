package com.kfadli.deezer.services.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

public class MemoryCache implements ICache {

    private LruCache<String, Bitmap> mMemoryCache;


    public MemoryCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    @Override
    public Bitmap getBitmap(String key) {
        return mMemoryCache.get(key);
    }

    @Override
    public void saveBitmap(String key, Bitmap bitmap) {
        if (getBitmap(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    @Override
    public void deleteBitmap(String key) {
        mMemoryCache.remove(key);
    }

    @Override
    public void clear() {
        mMemoryCache.evictAll();
    }
}
