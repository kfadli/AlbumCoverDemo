package com.kfadli.deezer.services.executor;

import android.support.annotation.NonNull;

import com.kfadli.deezer.enums.PriorityEnum;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

public class TaskPoolManager {

    // Sets the amount of time an idle thread waits before terminating
    private static final int KEEP_ALIVE_TIME = 1;
    // Sets the Time Unit to seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int NUMBER_OF_CORES = CPU_COUNT + 1;
    private static final int MAX_POOL_SIZE = CPU_COUNT + 1;

    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "Task ID:" + mCount.getAndIncrement());
        }
    };

    private static final BlockingQueue<Runnable> BLOCKING_QUEUE =
            new PriorityBlockingQueue<>(128, new Comparator<Runnable>() {
                @Override
                public int compare(Runnable o1, Runnable o2) {

                    PriorityEnum p1 = ((FutureTaskPriority) o1).getPriority();
                    PriorityEnum p2 = ((FutureTaskPriority) o2).getPriority();

                    return p1.compareTo(p2);
                }
            });

    // Creates a thread pool manager
    public static final Executor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            NUMBER_OF_CORES,       // Initial pool size
            MAX_POOL_SIZE,       // Max pool size
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            BLOCKING_QUEUE);
}
