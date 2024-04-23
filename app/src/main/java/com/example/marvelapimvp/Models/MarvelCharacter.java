package com.example.marvelapimvp.Models;

import java.io.Serializable;

public class MarvelCharacter implements Serializable {
    private Thumbnail thumbnail;
    private String name;
    private String description;
    private String modified;
    private long id;
    private String resourceURI;

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String value) {
        this.modified = value;
    }

    public long getid() {
        return id;
    }

    public void setid(long value) {
        this.id = value;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String value) {
        this.resourceURI = value;
    }


}
