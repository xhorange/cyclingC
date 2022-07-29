package com.oh.cyclingc.gps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.oh.cyclingc.sensor.ISensor;
import com.oh.cyclingc.utils.PermissionUtil;

public class GPSHelper implements LocationListener {
    private LocationManager locationManager;
    private Activity activity;

    private long lastTime;

    private Location lastLocation;
    private ISensor.SpeedListener speedListener;

    public GPSHelper(@NonNull Activity activity) {
        initLocationManager(activity);
    }

    public void startRecord(ISensor.SpeedListener speedListener) {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            PermissionUtil.checkRecordPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION, true);
            this.speedListener = speedListener;
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        }
    }

    public void stopRecord() {
        locationManager.removeUpdates(this);
    }

    /**
     * 初始化位置信息
     *
     * @param activity
     */
    private void initLocationManager(@NonNull Activity activity) {
        this.locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        this.activity = activity;

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location.hasSpeed()) {
            if (speedListener != null) {
                speedListener.onCurrentSpeedChanged(location.getSpeed());
            }
        }
        if (lastTime > 0 && lastLocation != null) {
            float dis = location.distanceTo(lastLocation);
            float speed = dis / (location.getTime() - lastTime);
            if (speedListener != null) {
                speedListener.onAverageSpeedChanged(speed);
            }
        }
        lastTime = location.getTime();
        lastLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(activity, "状态改变:" + status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(activity, "Gps开启", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(activity, "Gps关闭", Toast.LENGTH_SHORT).show();
    }
}
