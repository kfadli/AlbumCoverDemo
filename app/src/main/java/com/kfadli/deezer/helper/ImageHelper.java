package com.kfadli.deezer.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.kfadli.deezer.BuildConfig;
import com.kfadli.deezer.R;
import com.kfadli.deezer.enums.PriorityEnum;
import com.kfadli.deezer.services.executor.FutureTaskPriority;
import com.kfadli.deezer.services.executor.SuperCallable;
import com.kfadli.deezer.services.executor.TaskPoolManager;
import com.kfadli.deezer.services.executor.task.AsyncDrawable;
import com.kfadli.deezer.services.executor.task.GetImageCallable;

import java.util.concurrent.FutureTask;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public class ImageHelper {

    private static final String TAG = "ImageHelper";

    private Bitmap mPlaceHolder;

    public ImageHelper(Context context) {
        this.mPlaceHolder = BitmapFactory.decodeResource(context.getResources(), R.drawable.placeholder_icon);
    }

    private static GetImageCallable getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    /**
     * Returns true if the current work has been canceled or if there was no work in
     * progress on this image view.
     * Returns false if the work in progress deals with the same data. The work is not
     * stopped in that case.
     */
    public static boolean cancelPotentialWork(int imageId, ImageView imageView) {
        final GetImageCallable bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final int taskImageId = bitmapWorkerTask.getId();
            if (taskImageId != imageId) {
                bitmapWorkerTask.cancel(true);
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "cancelPotentialWork - cancelled work");
                }
            } else {
                // The same work is already in progress.
                return false;
            }
        }
        return true;
    }

    public static GetImageCallable getCurrentTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

    public void updateImage(ImageView imageView, GetImageCallable callable, PriorityEnum priority) {
        if (cancelPotentialWork(callable.getId(), imageView)) {
            final AsyncDrawable asyncDrawable = new AsyncDrawable(imageView.getResources(), mPlaceHolder, callable);
            imageView.setImageDrawable(asyncDrawable);
            FutureTask task = new FutureTaskPriority(callable, priority);
            TaskPoolManager.THREAD_POOL_EXECUTOR.execute(task);
        }
    }
}
