package com.example.gallery20.data.model;

public class Header extends TimelineItem{
    String date;

    public Header(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int getType() {
        return 0;
    }
}
