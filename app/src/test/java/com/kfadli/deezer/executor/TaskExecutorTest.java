package com.kfadli.deezer.executor;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.kfadli.deezer.services.cache.CacheManager;
import com.kfadli.deezer.services.executor.task.GetImageCallable;
import com.kfadli.deezer.enums.PriorityEnum;
import com.kfadli.deezer.services.executor.TaskPoolManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.FutureTask;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class TaskExecutorTest {


    @Test
    public void should_execute_task() throws InterruptedException {

        String url = "https://www.gstatic.com/android/market_images/web/play_prism_hlock_2x.png";
        ImageView imageView = Mockito.mock(ImageView.class);
        CacheManager cacheManager = Mockito.mock(CacheManager.class);

        FutureTask task = new FutureTask<>(new GetImageCallable(cacheManager, imageView, PriorityEnum.HIGH, url));
        TaskPoolManager.THREAD_POOL_EXECUTOR.execute(task);

        Mockito.verify(cacheManager, Mockito.times(1)).getBitmap(url);
        Mockito.verify(cacheManager, Mockito.times(1)).saveBitmap(url, Mockito.any(Bitmap.class));

        Mockito.verify(imageView, Mockito.times(1)).setImageBitmap(Mockito.any(Bitmap.class));

    }
}
