package com.kfadli.deezer.services.executor;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import com.kfadli.deezer.helper.BinderHelper;

import java.util.concurrent.Callable;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

public abstract class SuperCallable<P, R> implements Callable<R> {

    private static final String TAG = "AsyncTaskPriority";
    private P mParams;
    private boolean mCancelled = false;

    public SuperCallable(P params) {
        this.mParams = params;
    }

    @Override
    public R call() throws Exception {

        Log.d(TAG, "[call] params:" + mParams);
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        R result = execute(mParams);
        BinderHelper.postData(this, result);

        return result;
    }


    public boolean isCancelled() {
        return mCancelled;
    }

    public final void cancel(boolean cancelled) {
        mCancelled = cancelled;
    }

    public abstract R execute(P params);

    public abstract void postExecute(R result);

}
