package com.example.gallery20.permission;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.activity.result.ActivityResultLauncher;
import androidx.core.content.ContextCompat;

public class PermissionHelper {

    public static boolean hasReadPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_MEDIA_VIDEO)
                    == PackageManager.PERMISSION_GRANTED;
        } else {
            return ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED;
        }
    }

    public static void requestReadPermission(ActivityResultLauncher<String> launcher) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            launcher.launch(android.Manifest.permission.READ_MEDIA_VIDEO);
        } else {
            launcher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return activity.shouldShowRequestPermissionRationale(android.Manifest.permission.READ_MEDIA_VIDEO);
        } else {
            return activity.shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    public static void showPermissionRationaleDialog(Activity activity) {
    }

    private static void openSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(intent);
    }
}
