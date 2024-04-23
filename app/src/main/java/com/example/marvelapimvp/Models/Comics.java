package com.example.marvelapimvp.Models;

import java.io.Serializable;
import java.util.List;

public class Comics implements Serializable {
    private long issueNumber;

    private String title;

    private long id;

    private long pageCount;
    private Thumbnail2 thumbnail;
    private List<Thumbnail> images;

    private long digitalId;
    private String upc;
    private String resourceURI;
    private String variantDescription;
    private String issn;

    public Thumbnail2 getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail2 thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(long value) {
        this.issueNumber = value;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }


    public long getid() {
        return id;
    }

    public void setid(long value) {
        this.id = value;
    }


    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long value) {
        this.pageCount = value;
    }


    public long getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(long value) {
        this.digitalId = value;
    }


    public String getUpc() {
        return upc;
    }

    public void setUpc(String value) {
        this.upc = value;
    }


    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String value) {
        this.resourceURI = value;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setVariantDescription(String value) {
        this.variantDescription = value;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String value) {
        this.issn = value;
    }
}