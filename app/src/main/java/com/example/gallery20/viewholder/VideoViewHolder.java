package com.example.gallery20.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gallery20.ITouchTimelineListener;
import com.example.gallery20.R;
import com.example.gallery20.data.model.Video;
import com.example.gallery20.viewmodel.VideoViewModel;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    private View itemView;
    private ITouchTimelineListener listener;
    private VideoViewModel videoViewModel;

    public VideoViewHolder(@NonNull View itemView, ITouchTimelineListener listener, VideoViewModel videoViewModel) {
        super(itemView);
        this.itemView = itemView;
        this.listener = listener;
        this.videoViewModel = videoViewModel;
    }

    public void onBind(String uri, String duration) {
        TextView textView = itemView.findViewById(R.id.video_duration);
        CheckBox checkBox = itemView.findViewById(R.id.checkbox_video);
        ImageView thumbnail = itemView.findViewById(R.id.video_thumbnail);

        textView.setText(duration);
        Glide.with(itemView.getContext())
                .load(uri)
                .centerCrop()
                .frame(0)
                .into(thumbnail);

        if (videoViewModel.getIsSelecting().getValue()) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.GONE);
        }

        checkBox.setOnClickListener(v -> {
            if (itemView.isSelected()) {
                itemView.setSelected(false);
                checkBox.setChecked(false);
            } else {
                itemView.setSelected(true);
                checkBox.setChecked(true);
            }
        });

        setOnTouchListener();
    }

    public void setOnTouchListener() {
        itemView.setOnLongClickListener(v -> {
            listener.onLongClickItem(getAdapterPosition());
            return true;
        });

        itemView.setOnClickListener(v -> {
            listener.onClickItem(getAdapterPosition());
            if (videoViewModel.getIsSelecting().getValue()){
                if (itemView.isSelected()) {
                    itemView.setSelected(false);
                    videoViewModel.getSelectedVideos().getValue().remove((Video) videoViewModel.getTimelineItems().getValue().get(getAdapterPosition()));;
                } else {
                    itemView.setSelected(true);
                    videoViewModel.getSelectedVideos().getValue().add((Video) videoViewModel.getTimelineItems().getValue().get(getAdapterPosition()));
                }
            }

        });
    }


}
