package com.kfadli.deezer.helper;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.kfadli.deezer.services.executor.SuperCallable;
import com.kfadli.deezer.services.executor.task.CallableResult;

/**
 * Created by Karim Fadli on 03/07/2017.
 */

public class BinderHelper {

    public static void postData(SuperCallable callable, Object result) {
        Message message = getHandler().obtainMessage(0, new CallableResult<>(callable, result));
        message.sendToTarget();
        Binder.flushPendingCommands();
    }

    private static InternalHandler getHandler() {
        return new InternalHandler();
    }

    private static class InternalHandler extends Handler {
        public InternalHandler() {
            super(Looper.getMainLooper());
        }

        @SuppressWarnings({"unchecked", "RawUseOfParameterizedType"})
        @Override
        public void handleMessage(Message msg) {
            CallableResult callableResult = (CallableResult) msg.obj;
            callableResult.success();
        }
    }
}
