package com.example.gallery20.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gallery20.data.model.Album;
import com.example.gallery20.data.model.TimelineItem;
import com.example.gallery20.data.model.Video;
import com.example.gallery20.data.repository.VideoRepository;

import java.util.List;

public class VideoViewModel extends AndroidViewModel {
    private VideoRepository videoRepository;
    private MutableLiveData<List<Video>> videos = new MutableLiveData<>();
    private MutableLiveData<List<Album>> albums = new MutableLiveData<>();
    private MutableLiveData<List<TimelineItem>> timelineItems = new MutableLiveData<>();
    private MutableLiveData<List<Video>> selectedVideos = new MutableLiveData<>();

    private MutableLiveData<Boolean> isSelecting = new MutableLiveData<>(false);

    public VideoViewModel(@NonNull Application application) {
        super(application);
        videoRepository = new VideoRepository(application);
    }

    public LiveData<List<Video>> getVideos() {
        if (videos.getValue() == null) {
            loadVideos();
        }
        return videos;
    }

    public LiveData<List<Album>> getAlbums() {
        if (albums.getValue() == null) {
            loadAlbums();
        }
        return albums;
    }

    public LiveData<List<TimelineItem>> getTimelineItems() {
        if (timelineItems.getValue() == null) {
            loadTimelineItems();
        }
        return timelineItems;
    }

    private void loadVideos() {
        new Thread(() -> videos.postValue(videoRepository.getVideos())).start();
    }

    private void loadAlbums() {
        new Thread(() -> albums.postValue(videoRepository.getAlbums(videos.getValue()))).start();
    }

    private void loadTimelineItems() {
        new Thread(() -> timelineItems.postValue(videoRepository.getTimelineItems(videos.getValue()))).start();
    }

    public LiveData<Boolean> getIsSelecting() {
        return isSelecting;
    }

    public void setIsSelecting(boolean isSelecting) {
        this.isSelecting.setValue(isSelecting);
    }

    public LiveData<List<Video>> getSelectedVideos() {
        if (selectedVideos.getValue() == null) {
            selectedVideos = new MutableLiveData<>();
            selectedVideos.setValue(new java.util.ArrayList<>());
        }
        return selectedVideos;
    }
}
