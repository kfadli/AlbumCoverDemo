package com.kfadli.deezer.cache;

import android.content.Context;
import android.graphics.Bitmap;

import com.kfadli.deezer.services.cache.CacheManager;
import com.kfadli.deezer.services.cache.FileCache;
import com.kfadli.deezer.services.cache.MemoryCache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CacheManagerTest {

    @Mock
    Context mMockContext;

    @Mock
    private FileCache mFileCache;

    @Mock
    private MemoryCache mMemoryCache;

    private CacheManager mCache;

    @Before
    public void setUp() throws Exception {
        File directory = File.createTempFile("image", "fileCache");
        directory.delete();
        directory.mkdir();

        when(mMockContext.getCacheDir()).thenReturn(directory);

        this.mCache = new CacheManager(mMockContext);
        this.mCache.setFileCache(mFileCache);
        this.mCache.setMemoryCache(mMemoryCache);
    }

    @Test
    public void should_save_bitmap_in_FileCache_and_MemoryCache() {
        Bitmap bitmap = Mockito.mock(Bitmap.class);

        this.mCache.saveBitmap("my_key_1", bitmap);

        verify(mFileCache, times(1)).saveBitmap("my_key_1", bitmap);
        verify(mMemoryCache, times(1)).saveBitmap("my_key_1", bitmap);
    }

    @Test
    public void should_get_image_in_FileCache_when_not_exist_in_memory() {
        this.mCache.getBitmap("my_key_1");

        verify(mMemoryCache, times(1)).getBitmap("my_key_1");
        verify(mFileCache, times(1)).getBitmap("my_key_1");
    }

    @Test
    public void should_not_get_image_in_FileCache_when_exist_in_memory() {
        Bitmap bitmap = Mockito.mock(Bitmap.class);

        when(mMemoryCache.getBitmap("my_key_1")).thenReturn(bitmap);
        this.mCache.getBitmap("my_key_1");

        verify(mMemoryCache, times(1)).getBitmap("my_key_1");
        verify(mFileCache, times(0)).getBitmap("my_key_1");
    }

    @Test
    public void should_delete_FileCache_and_MemoryCache() {
        this.mCache.deleteBitmap("my_key_1");

        verify(mMemoryCache, times(1)).deleteBitmap("my_key_1");
        verify(mFileCache, times(1)).deleteBitmap("my_key_1");
    }

    @Test
    public void should_clear_FileCache_and_MemoryCache() {
        this.mCache.clear();

        verify(mMemoryCache, times(1)).clear();
        verify(mFileCache, times(1)).clear();
    }

}
