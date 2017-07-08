package com.kfadli.deezer.services.executor;

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
    protected ICallbackResult mDelegate = null;

    public SuperCallable(P mParams) {
        this.mParams = mParams;
    }

    public SuperCallable(P params, ICallbackResult delegate) {
        this.mParams = params;
        this.mDelegate = delegate;
    }

    @Override
    public R call() throws Exception {
        R result = null;
        try {
            Log.d(TAG, "[call] params:" + mParams);
            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
            result = execute(mParams);

            //Binder is used to call [postResult] method with UIThread
            BinderHelper.postData(this, result);
        }catch (Exception e){
            this.mDelegate.failed(e);
        }
        return result;
    }


    public boolean isCancelled() {
        return mCancelled;
    }

    public final void cancel(boolean cancelled) {
        mCancelled = cancelled;
    }

    public abstract R execute(P params) throws Exception;

    public abstract void postExecute(R result);

}
