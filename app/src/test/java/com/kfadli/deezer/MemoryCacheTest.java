package com.kfadli.deezer;

import android.graphics.Bitmap;

import com.kfadli.deezer.services.cache.MemoryCache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MemoryCacheTest {

    @Test
    public void should_save_bitmap() throws Exception {
        Bitmap bitmap = Mockito.mock(Bitmap.class);

        MemoryCache mCache = new MemoryCache();
        mCache.saveBitmap("my_key", bitmap);

        Assert.assertEquals(bitmap, mCache.getBitmap("my_key"));
    }

    @Test
    public void should_remove_bitmap() throws Exception {
        Bitmap bitmap = Mockito.mock(Bitmap.class);

        MemoryCache mCache = new MemoryCache();
        mCache.saveBitmap("my_key", bitmap);
        mCache.deleteBitmap("my_key");

        Assert.assertNull(mCache.getBitmap("my_key"));
    }

    @Test
    public void should_clear() throws Exception {
        Bitmap bitmap = Mockito.mock(Bitmap.class);

        MemoryCache mCache = new MemoryCache();
        mCache.saveBitmap("my_key", bitmap);
        mCache.saveBitmap("my_key_2", bitmap);

        Assert.assertEquals(bitmap, mCache.getBitmap("my_key"));
        Assert.assertEquals(bitmap, mCache.getBitmap("my_key_2"));

        mCache.clear();
        Assert.assertNull(mCache.getBitmap("my_key"));
        Assert.assertNull(mCache.getBitmap("my_key_2"));
    }

}
