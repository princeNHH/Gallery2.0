package com.example.gallery20.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gallery20.ITouchTimelineListener;
import com.example.gallery20.R;
import com.example.gallery20.viewmodel.VideoViewModel;

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    private View itemView;
    private ITouchTimelineListener listener;
    private VideoViewModel videoViewModel;

    public HeaderViewHolder(@NonNull View itemView, ITouchTimelineListener listener, VideoViewModel videoViewModel) {
        super(itemView);
        this.itemView = itemView;
        this.listener = listener;
        this.videoViewModel = videoViewModel;
    }

    public void onBind(String date) {
        TextView textView = itemView.findViewById(R.id.title_header);
        CheckBox checkBox = itemView.findViewById(R.id.checkbox_header);

        textView.setText(date);

        if (videoViewModel.getIsSelecting().getValue()) {
            checkBox.setVisibility(View.VISIBLE);
        } else {
            checkBox.setVisibility(View.GONE);
        }
    }
}
