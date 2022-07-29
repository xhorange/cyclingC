package com.oh.cyclingc;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;

import com.oh.cyclingc.gps.GPSHelper;
import com.oh.cyclingc.utils.PermissionUtil;

public class MainActivity extends AppCompatActivity {

    private GPSHelper gpsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}