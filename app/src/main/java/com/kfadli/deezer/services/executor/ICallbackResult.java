package com.kfadli.deezer.services.executor;

/**
 * Created by Karim Fadli on 08/07/2017.
 */

public interface ICallbackResult<R> {
    void success(R result);
    void failed(Exception e);
}
