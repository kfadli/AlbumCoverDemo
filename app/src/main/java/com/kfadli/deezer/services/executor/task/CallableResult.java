package com.kfadli.deezer.services.executor.task;

import com.kfadli.deezer.services.executor.SuperCallable;

/**
 * Created by Karim Fadli on 03/07/2017.
 */

public class CallableResult<R> {

    private SuperCallable callable;
    private R result;

    public CallableResult(SuperCallable callable, R result) {
        this.callable = callable;
        this.result = result;
    }

    public void success(){
        this.callable.postExecute(result);
    }

    public void failed(){

    }
}
