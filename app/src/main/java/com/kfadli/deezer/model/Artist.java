package com.kfadli.deezer.model;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public class Artist {

    private Integer id;
    private String name;
    private String picture;
    private String pictureSmall;
    private String pictureMedium;
    private String pictureBig;
    private String pictureXl;
    private String tracklist;

    public Artist(Integer id, String name, String picture, String pictureSmall, String pictureMedium, String pictureBig, String pictureXl, String tracklist) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.pictureSmall = pictureSmall;
        this.pictureMedium = pictureMedium;
        this.pictureBig = pictureBig;
        this.pictureXl = pictureXl;
        this.tracklist = tracklist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPictureSmall() {
        return pictureSmall;
    }

    public void setPictureSmall(String pictureSmall) {
        this.pictureSmall = pictureSmall;
    }

    public String getPictureMedium() {
        return pictureMedium;
    }

    public void setPictureMedium(String pictureMedium) {
        this.pictureMedium = pictureMedium;
    }

    public String getPictureBig() {
        return pictureBig;
    }

    public void setPictureBig(String pictureBig) {
        this.pictureBig = pictureBig;
    }

    public String getPictureXl() {
        return pictureXl;
    }

    public void setPictureXl(String pictureXl) {
        this.pictureXl = pictureXl;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

}
