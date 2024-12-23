package com.example.gallery20.data.model;

public class Video extends TimelineItem{
    private String id;
    private String uri;
    private long date;
    private long duration;
    private boolean isSelected;

    public Video(String id, String uri, long date, long duration) {
        this.id = id;
        this.uri = uri;
        this.date = date;
        this.duration = duration;
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

    public long getDuration() {
        return duration;
    }

    public String getDurationFormated() {
        long seconds = duration / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        seconds %= 60;
        minutes %= 60;
        String duration = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return duration;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public int getType() {
        return TYPE_VIDEO;
    }
}
