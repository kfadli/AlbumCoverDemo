package com.kfadli.deezer.services.executor.task;

import android.util.JsonReader;
import android.util.Log;

import com.kfadli.deezer.exception.ParseResponseException;
import com.kfadli.deezer.model.Album;
import com.kfadli.deezer.services.executor.ICallbackResult;
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

    public GetJsonCallable(String params, ICallbackResult failedCallback) {
        super(params, failedCallback);
    }

    @Override
    public JsonReader execute(String params) throws Exception {

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

                mDelegate.success(albumList);
            } catch (ParseResponseException e) {
                Log.e(TAG, "[postExecute] Failed to parse json", e);
                mDelegate.failed(e);
            }
        }
    }

    private InputStream downloadContent(String url) throws Exception {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            return connection.getInputStream();
        } catch (IOException e) {
            Log.e(TAG, "[downloadContent] Failed to read json, url: " + url, e);
            throw e;
        }
    }
}

