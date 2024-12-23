package com.example.gallery20.viewholder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gallery20.R;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    View itemView;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
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

    }
}
