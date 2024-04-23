package com.example.marvelapimvp.Models;

import java.util.ArrayList;

public class Data {
    private ArrayList<MarvelCharacter> results;
    private ArrayList<Thumbnail> thumbnails;
    public ArrayList<MarvelCharacter> getResults() {
        return results;
    }

    public void setResults(ArrayList<MarvelCharacter> results) {
        this.results = results;
    }
}