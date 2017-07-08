package com.kfadli.deezer.activity.main;

import com.kfadli.deezer.activity.base.IView;
import com.kfadli.deezer.model.Album;
import com.kfadli.deezer.services.cache.CacheManager;

import java.util.List;

/**
 * Created by Karim Fadli on 08/07/2017.
 */

public interface IMainView extends IView {
    void showToastMessage(String message);
    void showSnackMessage(String message);
    void updateAlbums(List<Album> albums);
}
