package com.oh.cyclingc.View;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.oh.cyclingc.R;
import com.oh.cyclingc.gps.GPSHelper;
import com.oh.cyclingc.sensor.AccelerometerSensorHelper;
import com.oh.cyclingc.sensor.ISensor;

public class DashBoardView extends ConstraintLayout {

    private TextView currentSpeedTv;
    private TextView averageSPeedTv;
    private TextView accelerateTv;
    private GPSHelper gpsHelper;

    private AccelerometerSensorHelper accelerometerSensorHelper;

    private TextView startBtn;

    private TextView stopBtn;


    public DashBoardView(Context context) {
        super(context);
        init(context);
    }

    public DashBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DashBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DashBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes, TextView startBtn) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.cycling_dash_board_view, this, true);
        gpsHelper = new GPSHelper((Activity) context);
        accelerometerSensorHelper = new AccelerometerSensorHelper((Activity) context);
        currentSpeedTv = findViewById(R.id.current_tv);
        averageSPeedTv = findViewById(R.id.average_tv);
        accelerateTv = findViewById(R.id.accelerate_tv);
        startBtn = findViewById(R.id.start_btn);
        stopBtn = findViewById(R.id.stop_btn);
        startBtn.setOnClickListener(v -> {
            Toast.makeText(context, "start", Toast.LENGTH_LONG).show();
            stopBtn.setBackgroundColor(getResources().getColor(R.color.red));
            startBtn.setBackgroundColor(getResources().getColor(R.color.grey));
            gpsHelper.startRecord(new ISensor.SpeedListener() {
                @Override
                public void onCurrentSpeedChanged(float speed) {
                    currentSpeedTv.setText("当前速度：" + speed + "m/s");
                }

                @Override
                public void onAverageSpeedChanged(float speed) {
                    averageSPeedTv.setText("平均速度：" + speed + "m/s");
                }
            });

            accelerometerSensorHelper.startRecord(new ISensor.SpeedListener() {
                @Override
                public void onCurrentSpeedChanged(float speed) {
                    accelerateTv.setText("当前加速度：" + speed + "m/s^2");
                }

                @Override
                public void onAverageSpeedChanged(float speed) {

                }
            });
        });

        stopBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                gpsHelper.stopRecord();
                accelerometerSensorHelper.stopRecord();
                stopBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                startBtn.setBackgroundColor(getResources().getColor(R.color.teal_200));
                currentSpeedTv.setText("当前速度：0m/s");
                averageSPeedTv.setText("平均速度：0m/s");
                accelerateTv.setText("当前加速度：0m/s^2");
            }
        });

    }

}
