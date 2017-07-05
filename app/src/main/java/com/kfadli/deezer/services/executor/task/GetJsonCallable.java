package com.kfadli.deezer.services.executor.task;

import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;

import com.kfadli.deezer.activity.AlbumsAdapter;
import com.kfadli.deezer.exception.ParseResponseException;
import com.kfadli.deezer.helper.ImageHelper;
import com.kfadli.deezer.model.Album;
import com.kfadli.deezer.services.cache.CacheManager;
import com.kfadli.deezer.services.executor.SuperCallable;
import com.kfadli.deezer.services.parser.ResponseParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public class GetJsonCallable extends SuperCallable<String, JsonReader> {

    private static final String TAG = "GetJsonTask";

    private RecyclerView mRecyclerView;
    private CacheManager mCache;
    private ImageHelper mImageHelper;

    public GetJsonCallable(CacheManager cache, ImageHelper imageHelper, RecyclerView recyclerView, String params) {
        super(params);
        this.mCache = cache;
        this.mImageHelper = imageHelper;
        this.mRecyclerView = recyclerView;
    }

    @Override
    public JsonReader execute(String params) {

        InputStream inStream = downloadContent(params);
        if (inStream != null) {
            return new JsonReader(new InputStreamReader(inStream));
        }

        return null;
    }

    @Override
    public void postExecute(JsonReader result) {
        if (result != null) {
            try {
                List<Album> albumList = ResponseParser.parseAlbumsResponse(result);
                Log.d(TAG, "[postExecute] album size:" + albumList.size());

                this.mRecyclerView.setAdapter(new AlbumsAdapter(this.mCache, this.mImageHelper, albumList));
                this.mRecyclerView.getAdapter().notifyDataSetChanged();
            } catch (ParseResponseException e) {
                e.printStackTrace();
            }
        }
    }

    private InputStream downloadContent(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            return connection.getInputStream();
        } catch (IOException e) {
            Log.e(TAG, "[downloadContent] Failed to read json, url: " + url, e);
        }
        return null;
    }
}

