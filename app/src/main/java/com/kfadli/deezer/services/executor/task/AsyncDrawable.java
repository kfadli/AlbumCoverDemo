package com.kfadli.deezer.services.executor.task;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.lang.ref.WeakReference;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public class AsyncDrawable extends BitmapDrawable {

    private final WeakReference<GetImageCallable> bitmapWorkerTaskReference;

    public AsyncDrawable(Resources res, Bitmap bitmap, GetImageCallable bitmapWorkerTask) {
        super(res, bitmap);
        bitmapWorkerTaskReference = new WeakReference(bitmapWorkerTask);
    }

    public GetImageCallable getBitmapWorkerTask() {
        return bitmapWorkerTaskReference.get();
    }

}