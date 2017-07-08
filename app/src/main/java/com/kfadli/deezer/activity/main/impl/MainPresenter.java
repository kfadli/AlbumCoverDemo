package com.kfadli.deezer.activity.main.impl;

import com.kfadli.deezer.activity.main.IMainPresenter;
import com.kfadli.deezer.activity.main.IMainView;
import com.kfadli.deezer.model.Album;
import com.kfadli.deezer.services.executor.ICallbackResult;
import com.kfadli.deezer.services.executor.TaskPoolManager;
import com.kfadli.deezer.services.executor.task.GetJsonCallable;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.FutureTask;

/**
 * Created by Karim Fadli on 08/07/2017.
 */

public class MainPresenter implements IMainPresenter, ICallbackResult<List<Album>> {

    private static final String TAG = "MainPresenter";

    private WeakReference<IMainView> mView = null;


    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void attachView(IMainView view) {
        this.mView = new WeakReference<>(view);
    }

    @Override
    public void detachView(boolean retainInstance) {
        this.mView.clear();
    }

    @Override
    public void getDataContent(String url) {
        if (this.mView.get() != null) {
            FutureTask data = new FutureTask(new GetJsonCallable(url, this));
            TaskPoolManager.THREAD_POOL_EXECUTOR.execute(data);
        }
    }

    @Override
    public void success(List<Album> result) {
        if (result != null) {
            if (mView.get() != null) {
                mView.get().updateAlbums(result);
            }
        }
    }

    @Override
    public void failed(Exception e) {
        if (mView.get() != null) {
            mView.get().showToastMessage(e.getMessage());
        }
    }

    @Override
    public void clearCache() {
        if (mView.get() != null) {
            this.mView.get().getCache().clear();
            mView.get().showSnackMessage("Cache cleared with success");
        }
    }

}
