package com.example.gallery20;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.gallery20.data.model.Video;
import com.example.gallery20.fragment.AlbumFragment;
import com.example.gallery20.fragment.TimelineFragment;
import com.example.gallery20.permission.PermissionHelper;
import com.example.gallery20.viewmodel.VideoViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private VideoViewModel videoViewModel;
    private TimelineFragment timelineFragment;
    private AlbumFragment albumFragment;
    ActivityResultLauncher<String> requestReadPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        requestReadPermission = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        videoViewModel.getVideos();
                    }
                }
        );
        videoViewModel.getVideos().observe(this, videos -> {
            if (!videos.isEmpty()) {
                timelineFragment = new TimelineFragment();
                albumFragment = new AlbumFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, timelineFragment)
                        .commit();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkAndRequestPermissions();
    }

    public void checkAndRequestPermissions() {
        if (PermissionHelper.hasReadPermission(this)) {
            videoViewModel.getVideos();
        } else if (PermissionHelper.shouldShowRequestPermissionRationale(this)){
            PermissionHelper.requestReadPermission(requestReadPermission);
        } else {
            PermissionHelper.showPermissionRationaleDialog(this);
        }
    }
}