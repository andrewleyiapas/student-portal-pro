package com.prozer.studentportalpro.models;

import com.google.gson.annotations.SerializedName;

public class News {
    private int id;
    private String title;
    
    @SerializedName("body")
    private String description;

    public News() {}

    public News(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}
