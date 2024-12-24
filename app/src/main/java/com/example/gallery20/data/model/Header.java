package com.example.gallery20.data.model;

public class Header extends TimelineItem{
    String date;
    int itemCount;

    public Header(String date, int itemCount) {
        this.date = date;
        this.itemCount = itemCount;
    }

    public String getDate() {
        return date;
    }

    public int getItemCount() {
        return itemCount;
    }

    @Override
    public int getType() {
        return 0;
    }


}
