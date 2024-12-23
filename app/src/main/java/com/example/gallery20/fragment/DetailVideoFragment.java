package com.example.gallery20.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gallery20.R;

public class DetailVideoFragment extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detail_video_layout, container, false);
//        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
//        videoViewModel.getTimelineItems().observe(getViewLifecycleOwner(), timelineItems -> {
//            RecyclerView recyclerView = view.findViewById(R.id.timeline_recycler_view);
//            recyclerView.setAdapter(new TimelineAdapter(timelineItems));
//        });
        return view;
    }
}
