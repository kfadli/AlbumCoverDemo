package com.kfadli.deezer.activity.base;

import android.app.Application;
import android.content.Context;

import com.kfadli.deezer.helper.ImageHelper;
import com.kfadli.deezer.services.cache.CacheManager;

/**
 * Created by Karim Fadli on 08/07/2017.
 */

public interface IView {
    Context getContext();
    CacheManager getCache();
    ImageHelper getImageHelper();
}
