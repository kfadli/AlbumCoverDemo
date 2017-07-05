package com.kfadli.deezer.services.executor;

import android.support.annotation.NonNull;

import com.kfadli.deezer.enums.PriorityEnum;

import java.util.concurrent.FutureTask;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public  class FutureTaskPriority extends FutureTask implements ITaskPriority {

    private PriorityEnum mPriority;

    public FutureTaskPriority(@NonNull SuperCallable callable, PriorityEnum priorityEnum) {
        super(callable);
        this.mPriority = priorityEnum;
    }

    public PriorityEnum getPriority() {
        return mPriority;
    }

}
