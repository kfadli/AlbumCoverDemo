package com.kfadli.deezer.services.executor.task;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.kfadli.deezer.R;
import com.kfadli.deezer.exception.DownloadImageException;
import com.kfadli.deezer.helper.ImageHelper;
import com.kfadli.deezer.services.cache.CacheManager;
import com.kfadli.deezer.services.executor.ICallbackResult;
import com.kfadli.deezer.services.executor.SuperCallable;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public class GetImageCallable extends SuperCallable<String, Bitmap> {

    private static final String TAG = "GetImageTask";

    //
    private CacheManager mCache;
    private int mId;

    public GetImageCallable(CacheManager cache, ImageView imageView, String url, int id) {
        super(url);

        this.mDelegate = new BitmapResult(imageView, this);
        this.mCache = cache;
        this.mId = id;
    }

    @Override
    public Bitmap execute(String url) throws Exception{

        Log.d(TAG, "[execute] url:" + url);

        if (isCancelled()) {
            return null;
        }

        Bitmap bitmap = this.mCache.getBitmap(mId + "");
        if (bitmap == null) {

            if (isCancelled()) {
                return null;
            }

            bitmap = downloadBitmap(url);

            if (bitmap != null) {
                Log.d(TAG, "[execute] saving bitmap, key:" + mId);
                this.mCache.saveBitmap(this.mId + "", bitmap);
            }
        }

        return bitmap;
    }

    @Override
    public void postExecute(Bitmap result) {
        Log.d(TAG, "[onPostExecute] id:" + mId);
        if (result != null) {
            mDelegate.success(result);
        }
    }


    public Bitmap downloadBitmap(String url) throws DownloadImageException {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            return BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            Log.e(TAG, "[downloadBitmap] Failed to download Bitmap, url: " + url, e);
            throw new DownloadImageException("Failed to download Bitmap, url: " + url, e);
        }
    }

    public int getId() {
        return mId;
    }

}
