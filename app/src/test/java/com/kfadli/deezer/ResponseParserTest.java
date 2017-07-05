package com.kfadli.deezer;

import android.util.JsonReader;

import com.kfadli.deezer.exception.ParseResponseException;
import com.kfadli.deezer.model.Album;
import com.kfadli.deezer.services.parser.ResponseParser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ResponseParserTest {

    private InputStream inputStream;

    @Before
    public void setUp() throws Exception {
        inputStream = this.getClass().getClassLoader().getResourceAsStream("Sample.json");
    }

    @Test
    public void parseAlbumsTest() throws ParseResponseException, IOException {
        BufferedInputStream bufferedStream = new BufferedInputStream(
                inputStream);
        InputStreamReader streamReader = new InputStreamReader(
                bufferedStream);

        JsonReader reader = new JsonReader(streamReader);
        List<Album> albumList = ResponseParser.parseAlbumsResponse(new JsonReader(new InputStreamReader(inputStream)));

        Assert.assertEquals(2, albumList.size());
    }
}
