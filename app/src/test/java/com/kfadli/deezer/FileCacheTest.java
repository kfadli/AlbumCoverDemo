package com.kfadli.deezer;

import android.content.Context;
import android.graphics.Bitmap;

import com.kfadli.deezer.services.cache.FileCache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Karim Fadli on 01/07/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class FileCacheTest {

    @Mock
    private Context mMockContext;

    private File mDirectory;

    @Before
    public void setUp() throws Exception {
        this.mDirectory = File.createTempFile("image", "fileCache");
        this.mDirectory.delete();
        this.mDirectory.mkdir();

        when(mMockContext.getCacheDir()).thenReturn(mDirectory);
    }

    @Test
    public void should_save_bitmap() throws Exception {
        Bitmap bitmap = Mockito.mock(Bitmap.class);

        FileCache fCache = new FileCache(mMockContext);
        fCache.saveBitmap("my_key", bitmap);

        Assert.assertEquals(1, this.mDirectory.list().length);
    }

    @Test
    public void should_delete_bitmap() throws Exception {
        FileCache fCache = new FileCache(mMockContext);
        fCache.deleteBitmap("my_key_2");

        verify(this.mMockContext).deleteFile("my_key_2");
    }

    @Test
    public void should_clear() throws Exception {
        Bitmap bitmap = Mockito.mock(Bitmap.class);

        FileCache fCache = new FileCache(mMockContext);
        fCache.saveBitmap("my_key_3", bitmap);
        fCache.saveBitmap("my_key_4", bitmap);

        Assert.assertEquals(2, this.mDirectory.list().length);

        fCache.clear();
        Assert.assertEquals(0, this.mDirectory.list().length);
    }

}
