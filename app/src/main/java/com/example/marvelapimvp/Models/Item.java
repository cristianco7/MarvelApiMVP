package com.example.marvelapimvp.Models;

public class Item {
    private String title;
    private String id;
    private String url;

    public Item(String title, String id, String url) {
        this.title = title;
        this.id = id;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
