package com.example.marvelapimvp.Models;

import java.io.Serializable;

public class Root implements Serializable {
    private  Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
