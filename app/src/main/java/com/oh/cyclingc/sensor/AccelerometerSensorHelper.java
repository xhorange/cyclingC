package com.oh.cyclingc.sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.annotation.NonNull;

public class AccelerometerSensorHelper implements SensorEventListener, ISensor {

    private SensorManager sensorManager;
    private Activity activity;
    private Sensor accelerometerSensor;

    private SpeedListener speedListener;

    public AccelerometerSensorHelper(Activity activity) {
        this.activity = activity;
        init(activity);
    }

    private void init(@NonNull Activity activity) {
        this.sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        this.activity = activity;
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

    }

    @Override
    public void startRecord(SpeedListener speedListener) {
        // TODO: 2022/7/24 需要验证精度是否够用
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_UI);
        this.speedListener = speedListener;
    }

    @Override
    public void stopRecord() {
        sensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        if (speedListener != null) {
            double acc = Math.sqrt(values[0] * values[0] + values[1] * values[1] + values[2] * values[2]);
            speedListener.onCurrentSpeedChanged((float) acc);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
