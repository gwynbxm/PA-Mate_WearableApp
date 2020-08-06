/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nyp.edu.sg.pamatewear.constants.IntentName;

import static android.content.Context.SENSOR_SERVICE;

public class HeartRateActivity extends Fragment implements SensorEventListener {

    private static final String TAG = HeartRateActivity.class.getSimpleName();

    TextView tvRecordedHeartRate;
    Sensor heartRateSensor;
    SensorManager sensorManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_heart_rate, container, false);

        tvRecordedHeartRate = (TextView) v.findViewById(R.id.tvHeartRate);

        sensorManager = ((SensorManager) getActivity().getSystemService(SENSOR_SERVICE));
        heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        if (heartRateSensor == null) {
            Log.d(TAG, "heart rate sensor is null");
        }

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        sensorManager.registerListener(this, this.heartRateSensor, 3);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.values[0] > 0) {
            tvRecordedHeartRate.setText(String.valueOf((int) event.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "accuracy changed: " + accuracy);
    }

    @Override
    public void onStop() {
        super.onStop();

        sensorManager.unregisterListener(this, heartRateSensor);
    }
}
