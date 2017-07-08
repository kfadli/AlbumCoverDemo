package com.kfadli.deezer.activity.base;

/**
 * Created by Karim Fadli on 08/07/2017.
 */

public interface IPresenter<V extends IView> {

    void onStart();
    void onResume();
    void onPause();

    void onStop();
    void attachView(V view);
    void detachView(boolean retainInstance);

}
