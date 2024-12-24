package com.example.gallery20.data.repository;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.gallery20.data.model.Album;
import com.example.gallery20.data.model.Header;
import com.example.gallery20.data.model.TimelineItem;
import com.example.gallery20.data.model.Video;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

public class VideoRepository {
    private Context context;

    public VideoRepository(Context context) {
        this.context = context;
    }

    public List<Video> getVideos() {
        List<Video> videos = new ArrayList<>();
        String[] projection = {
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DATE_TAKEN
        };
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Video.Media.DATE_TAKEN + " DESC"
        );
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                String uri = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                long date = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN));
                long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                videos.add(new Video(id, uri, date, duration));
            }
            cursor.close();
        }
        return videos;
    }

    public List<Album> getAlbums(List<Video> videos) {
        HashMap<String, Album> albumHashMap = new HashMap<>();
        for (Video video : videos) {
            File file = new File(video.getUri());
            String folderName = file.getParentFile().getName();
            if (!albumHashMap.containsKey(folderName)) {
                albumHashMap.put(folderName, new Album(folderName, new ArrayList<>(), 0));
            }
            Album album = albumHashMap.get(folderName);
            album.getVideos().add(video);
            album.setItemCount(album.getItemCount() + 1);
        }
        return new ArrayList<>(albumHashMap.values());
    }

    public List<TimelineItem> getTimelineItems(List<Video> videos) {
        LinkedHashMap<String, List<Video>> groupedVideos = new LinkedHashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        for (Video video : videos) {
            String date = dateFormat.format(video.getDate());
            if (!groupedVideos.containsKey(date)) {
                groupedVideos.put(date, new ArrayList<>());
            }
            groupedVideos.get(date).add(video);
        }

        List<TimelineItem> timelineItems = new ArrayList<>();
        for (String date : groupedVideos.keySet()) {
            timelineItems.add(new Header(date, videos.size()));
            for (Video video : videos) {
                timelineItems.add(new Video(video.getId(), video.getUri(), video.getDate(), video.getDuration()));
            }
        }
        return timelineItems;
    }

}
