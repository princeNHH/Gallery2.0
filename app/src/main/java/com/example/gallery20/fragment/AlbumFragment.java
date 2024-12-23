package com.example.gallery20.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gallery20.R;
import com.example.gallery20.adapter.TimelineAdapter;
import com.example.gallery20.viewmodel.VideoViewModel;

public class AlbumFragment extends Fragment {
    private VideoViewModel videoViewModel;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.album_layout, container, false);
        videoViewModel = new ViewModelProvider(getActivity()).get(VideoViewModel.class);
        videoViewModel.getTimelineItems().observe(getViewLifecycleOwner(), timelineItems -> {
            RecyclerView recyclerView = view.findViewById(R.id.album_recycler_view);
            recyclerView.setAdapter(new TimelineAdapter(timelineItems));
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
