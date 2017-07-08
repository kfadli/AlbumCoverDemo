package com.kfadli.deezer.exception;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public class DownloadImageException extends Exception {

    public DownloadImageException(String message, Exception e) {
        super(message, e);
    }
}
