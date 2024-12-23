package com.example.gallery20.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gallery20.ITouchTimelineListener;
import com.example.gallery20.R;
import com.example.gallery20.data.model.Header;
import com.example.gallery20.data.model.TimelineItem;
import com.example.gallery20.data.model.Video;
import com.example.gallery20.viewholder.HeaderViewHolder;
import com.example.gallery20.viewholder.VideoViewHolder;
import com.example.gallery20.viewmodel.VideoViewModel;

import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TimelineItem> timelineItems;
    private ITouchTimelineListener listener;
    private VideoViewModel videoViewModel;

    public TimelineAdapter(List<TimelineItem> timelineItems, VideoViewModel videoViewModel) {
        this.timelineItems = timelineItems;
        this.videoViewModel = videoViewModel;
    }

    @Override
    public int getItemViewType(int position) {
        return timelineItems.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TimelineItem.TYPE_HEADER) {
            return new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false), listener, videoViewModel);
        } else {
            return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false), listener, videoViewModel);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).onBind(((Header) timelineItems.get(position)).getDate());
        } else {
            ((VideoViewHolder) holder).onBind((
                    (Video) timelineItems.get(position)).getUri(), ((Video) timelineItems.get(position)).getDurationFormated()
            );
        }
    }

    @Override
    public int getItemCount() {
        return timelineItems.size();
    }

    public void setListener(ITouchTimelineListener listener) {
        this.listener = listener;
    }
}
