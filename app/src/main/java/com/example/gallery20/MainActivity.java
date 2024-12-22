package com.example.gallery20;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.gallery20.permission.PermissionHelper;
import com.example.gallery20.viewmodel.VideoViewModel;

public class MainActivity extends AppCompatActivity {
    private VideoViewModel videoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkAndRequestPermissions();
    }

    public void checkAndRequestPermissions() {
        if (PermissionHelper.hasReadPermission(this)) {
            videoViewModel.getVideos();
        } else if (!PermissionHelper.shouldShowRequestPermissionRationale(this)){
            ActivityResultLauncher<String> requestReadPermission = registerForActivityResult(
                    new ActivityResultContracts.RequestPermission(),
                    isGranted -> {
                        if (isGranted) {
                            videoViewModel.getVideos();
                        }
                    }
            );
            PermissionHelper.requestReadPermission(requestReadPermission);
        } else {
            PermissionHelper.showPermissionRationaleDialog(this);
        }
    }
}