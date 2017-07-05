package com.kfadli.deezer.exception;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public class ParseResponseException extends Exception {

    public ParseResponseException(Exception e) {
        super("Failed to parse Response", e);
    }
}
