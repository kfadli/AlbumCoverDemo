package com.kfadli.deezer.services.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

public class FileCache implements ICache {

    private static final String TAG = "FileCache";

    private Context mContext;

    public FileCache(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Bitmap getBitmap(String key) {
        File file = new File(mContext.getCacheDir(), key);
        try {
            if (file.exists()) {
                return BitmapFactory.decodeStream(new FileInputStream(file));
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Failed to get bitmap for key:" + key);
        }
        return null;
    }

    @Override
    public void saveBitmap(String key, Bitmap bitmap) {
        File cacheDir = mContext.getCacheDir();
        File file = new File(cacheDir, key);

        OutputStream os;
        try {
            os = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, os);
            os.flush();
            os.close();
        } catch (IOException e) {
            Log.e(TAG, "Failed to save bitmap for key:" + key, e);
        }
    }

    @Override
    public void deleteBitmap(String key) {
        mContext.deleteFile(key);
    }

    @Override
    public void clear() {

        File[] files = mContext.getCacheDir().listFiles();

        for (File file : files) {
            file.delete();
        }

    }
}
