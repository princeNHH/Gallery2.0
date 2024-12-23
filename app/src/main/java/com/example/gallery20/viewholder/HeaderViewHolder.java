package com.example.gallery20.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gallery20.R;

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    public HeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void onBind(String date) {
        TextView textView = itemView.findViewById(com.example.gallery20.R.id.title_header);
        textView.setText(date);
    }
}
