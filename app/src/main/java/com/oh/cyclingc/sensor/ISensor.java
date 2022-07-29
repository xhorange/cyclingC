package com.oh.cyclingc.sensor;

import android.app.Activity;

public interface ISensor {


    void startRecord(SpeedListener speedListener);

    void stopRecord();

    public interface SpeedListener {
        void onCurrentSpeedChanged(float speed);

        void onAverageSpeedChanged(float speed);
    }
}
