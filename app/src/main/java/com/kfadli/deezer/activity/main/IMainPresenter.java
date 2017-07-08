package com.kfadli.deezer.activity.main;

import com.kfadli.deezer.activity.base.IPresenter;

/**
 * Created by Karim Fadli on 08/07/2017.
 */

public interface IMainPresenter extends IPresenter<IMainView> {
    void getDataContent(String url);
    void clearCache();
}
