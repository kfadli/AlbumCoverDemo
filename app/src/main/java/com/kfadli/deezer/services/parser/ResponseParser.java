package com.kfadli.deezer.services.parser;

import android.util.JsonReader;

import com.kfadli.deezer.exception.ParseResponseException;
import com.kfadli.deezer.model.Album;
import com.kfadli.deezer.model.Artist;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public class ResponseParser {

    public static List<Album> parseAlbumsResponse(JsonReader reader) throws ParseResponseException {
        List<Album> albums = null;
        String name;

        try {
            reader.beginObject();
            while (reader.hasNext()) {
                name = reader.nextName();
                switch (name) {
                    case "next":
                        reader.nextString();
                        break;
                    case "data": {
                        albums = parseAlbums(reader);
                        break;
                    }
                    default:
                        reader.skipValue();
                        break;
                }
            }

        } catch (IOException | ParseException e) {
            throw new ParseResponseException(e);
        }

        return albums;
    }

    private static List<Album> parseAlbums(JsonReader reader) throws IOException, ParseException {
        List<Album> albums = new ArrayList<>();

        reader.beginArray();
        while (reader.hasNext()) {
            albums.add(parseAlbum(reader));
        }
        reader.endArray();

        return albums;
    }

    private static Album parseAlbum(JsonReader reader) throws IOException, ParseException {

        String name;

        int id = -1;
        String title = "";
        String link = "";
        String cover = "";
        String coverSmall = "";
        String coverMedium = "";
        String coverBig = "";
        String coverXl = "";
        int nbTracks = 0;
        Date releaseDate = null;
        String recordType = "";
        boolean available = false;
        String trackList = "";
        boolean explicitLyrics = false;
        Date timeAdd = null;
        Artist artist = null;

        reader.beginObject();
        while (reader.hasNext()) {
            name = reader.nextName();

            switch (name) {
                case "id":
                    id = reader.nextInt();
                    break;
                case "title":
                    title = reader.nextString();
                    break;
                case "link":
                    link = reader.nextString();
                    break;
                case "cover":
                    cover = reader.nextString();
                    break;
                case "cover_small":
                    coverSmall = reader.nextString();
                    break;
                case "cover_medium":
                    coverMedium = reader.nextString();
                    break;
                case "cover_big":
                    coverBig = reader.nextString();
                    break;
                case "cover_xl":
                    coverXl = reader.nextString();
                    break;
                case "nb_tracks":
                    nbTracks = reader.nextInt();
                    break;
                case "release_date":
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    releaseDate = sdf.parse(reader.nextString());
                    break;
                case "record_type":
                    recordType = reader.nextString();
                    break;
                case "available":
                    available = reader.nextBoolean();
                    break;
                case "tracklist":
                    trackList = reader.nextString();
                    break;
                case "explicit_lyrics":
                    explicitLyrics = reader.nextBoolean();
                    break;
                case "time_add":
                    timeAdd = new Date(reader.nextLong());
                    break;
                case "artist":
                    artist = parseArtist(reader);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        return new Album(id, title, link, cover, coverSmall, coverMedium, coverBig, coverXl, nbTracks, releaseDate, recordType, available, trackList, explicitLyrics, timeAdd, artist);
    }

    private static Artist parseArtist(JsonReader reader) throws IOException {
        String name;

        int id = -1;
        String title = "";
        String picture = "";
        String pictureSmall = "";
        String pictureMedium = "";
        String pictureBig = "";
        String pictureXl = "";
        String trackList = "";

        reader.beginObject();
        while (reader.hasNext()) {
            name = reader.nextName();

            switch (name) {
                case "id":
                    id = reader.nextInt();
                    break;
                case "name":
                    title = reader.nextString();
                    break;
                case "picture":
                    picture = reader.nextString();
                    break;
                case "picture_small":
                    pictureSmall = reader.nextString();
                    break;
                case "picture_medium":
                    pictureMedium = reader.nextString();
                    break;
                case "picture_big":
                    pictureBig = reader.nextString();
                    break;
                case "picture_xl":
                    pictureXl = reader.nextString();
                    break;
                case "tracklist":
                    trackList = reader.nextString();
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }

        reader.endObject();

        return new Artist(id, title, picture, pictureSmall, pictureMedium, pictureBig, pictureXl, trackList);
    }

}