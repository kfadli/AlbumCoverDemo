package com.kfadli.deezer.model;

import java.util.Date;

/**
 * Created by Karim Fadli on 02/07/2017.
 */

public class Album {

    private Integer id;
    private String title;
    private String link;
    private String cover;
    private String coverSmall;
    private String coverMedium;
    private String coverBig;
    private String coverXl;
    private Integer nbTracks;
    private Date releaseDate;
    private String recordType;
    private Boolean available;
    private String tracklist;
    private Boolean explicitLyrics;
    private Date timeAdd;
    private Artist artist;

    public Album(Integer id, String title, String link, String cover, String coverSmall, String coverMedium, String coverBig, String coverXl, Integer nbTracks, Date releaseDate, String recordType, Boolean available, String tracklist, Boolean explicitLyrics, Date timeAdd, Artist artist) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.cover = cover;
        this.coverSmall = coverSmall;
        this.coverMedium = coverMedium;
        this.coverBig = coverBig;
        this.coverXl = coverXl;
        this.nbTracks = nbTracks;
        this.releaseDate = releaseDate;
        this.recordType = recordType;
        this.available = available;
        this.tracklist = tracklist;
        this.explicitLyrics = explicitLyrics;
        this.timeAdd = timeAdd;
        this.artist = artist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public String getCoverMedium() {
        return coverMedium;
    }

    public void setCoverMedium(String coverMedium) {
        this.coverMedium = coverMedium;
    }

    public String getCoverBig() {
        return coverBig;
    }

    public void setCoverBig(String coverBig) {
        this.coverBig = coverBig;
    }

    public String getCoverXl() {
        return coverXl;
    }

    public void setCoverXl(String coverXl) {
        this.coverXl = coverXl;
    }

    public Integer getNbTracks() {
        return nbTracks;
    }

    public void setNbTracks(Integer nbTracks) {
        this.nbTracks = nbTracks;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }

    public Boolean getExplicitLyrics() {
        return explicitLyrics;
    }

    public void setExplicitLyrics(Boolean explicitLyrics) {
        this.explicitLyrics = explicitLyrics;
    }

    public Date getTimeAdd() {
        return timeAdd;
    }

    public void setTimeAdd(Date timeAdd) {
        this.timeAdd = timeAdd;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
