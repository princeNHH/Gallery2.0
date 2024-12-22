package com.example.gallery20.data.model;

public class TimelineItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_VIDEO = 1;

    private int type;
    private Video video;
    private String header;

    public TimelineItem(int type, Video video) {
        this.type = type;
        this.video = video;
    }
    public TimelineItem(int type, String header) {
        this.type = type;
        this.header = header;
    }
    public int getType() {
        return type;
    }
    public Video getVideo() {
        return video;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
