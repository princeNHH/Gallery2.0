package com.example.gallery20.data.model;

public abstract class TimelineItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_VIDEO = 1;

    public abstract int getType();
}
