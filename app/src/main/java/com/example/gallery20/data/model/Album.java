package com.example.gallery20.data.model;

import java.util.List;

public class Album {
    private String name;
    private List<Video> videos;
    private int itemCount;

    public Album(String name, List<Video> videos, int itemCount) {
        this.name = name;
        this.videos = videos;
        this.itemCount = itemCount;
    }

    public String getName() {
        return name;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
