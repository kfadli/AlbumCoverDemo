package com.kfadli.deezer.services.executor.task;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.kfadli.deezer.helper.ImageHelper;
import com.kfadli.deezer.services.executor.ICallbackResult;

import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

/**
 * Created by Karim Fadli on 08/07/2017.
 */

public class BitmapResult implements ICallbackResult<Bitmap> {

    private static final String TAG = "BitmapResult";

    private WeakReference<ImageView> mImageView;
    private WeakReference<GetImageCallable> mTask;

    public BitmapResult(ImageView imageView, GetImageCallable task) {
        this.mImageView = new WeakReference<>(imageView);
        this.mTask = new WeakReference<>(task);
    }

    @Override
    public void success(Bitmap result) {
        final ImageView imageView = mImageView.get();
        final Callable bitmapWorkerTask = ImageHelper.getCurrentTask(imageView);
        if (mTask.get() != null && mTask.get() == bitmapWorkerTask) {
            imageView.setImageBitmap(result);
            ObjectAnimator fade = ObjectAnimator.ofFloat(imageView, "alpha", 0.0f, 1.0f);
            fade.setDuration(200);
            fade.start();
        }
    }

    @Override
    public void failed(Exception e) {
        Log.e(TAG, "[failed] Failed to getBitmap", e);
        //do nothing
    }
}