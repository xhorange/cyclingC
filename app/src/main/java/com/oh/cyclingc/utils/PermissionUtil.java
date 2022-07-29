package com.oh.cyclingc.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class PermissionUtil {
    private static final String TAG = "AudioCather";

    public static void requestPermission(Activity activity, String permission) {
        Log.i(TAG, "requestRecord");
        ActivityCompat.requestPermissions(activity, new String[]{permission}, 1);
    }

    public static boolean checkRecordPermission(Activity activity, String permission, boolean request) {
        if (activity == null) {
            return false;
        }
        boolean isAllow = PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        Log.i(TAG, "checkRecordPermission: " + isAllow);
        if (request) {
            requestPermission(activity, permission);
        }
        return isAllow;
    }
}
