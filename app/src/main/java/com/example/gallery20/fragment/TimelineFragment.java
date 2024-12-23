package com.example.gallery20.fragment;

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
import com.example.gallery20.data.model.TimelineItem;
import com.example.gallery20.viewmodel.VideoViewModel;

public class TimelineFragment extends Fragment {
    private VideoViewModel videoViewModel;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.timeline_layout, container, false);
        videoViewModel = new ViewModelProvider(this.getActivity()).get(VideoViewModel.class);
        videoViewModel.getTimelineItems().observe(getViewLifecycleOwner(), timelineItems -> {
            RecyclerView recyclerView = view.findViewById(R.id.timeline_recycler_view);
            recyclerView.setAdapter(new TimelineAdapter(timelineItems));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 3);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (timelineItems.get(position).getType() == TimelineItem.TYPE_HEADER) {
                        return 3;
                    } else {
                        return 1;
                    }
                }
            });
            recyclerView.setLayoutManager(gridLayoutManager);
        });
        return view;
    }
}