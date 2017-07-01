package com.kfadli.deezer.services.cache;

import android.graphics.Bitmap;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

public interface ICache {
    Bitmap getBitmap(String key);

    void saveBitmap(String key, Bitmap bitmap);

    void deleteBitmap(String key);

    void clear();

}
