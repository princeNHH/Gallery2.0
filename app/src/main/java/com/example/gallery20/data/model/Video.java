package com.example.gallery20.data.model;

public class Video {
    private String id;
    private String uri;
    private long date;
    private boolean isSelected;

    public Video(String id, String uri, long date) {
        this.id = id;
        this.uri = uri;
        this.date = date;
        this.isSelected = false;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public long getDate() {
        return date;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
