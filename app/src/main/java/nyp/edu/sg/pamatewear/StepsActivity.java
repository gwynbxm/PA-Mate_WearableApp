/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.WatchViewStub;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StepsActivity extends Fragment implements SensorEventListener {

    TextView tvRecordSteps;
    SensorManager sensorManager;
    Sensor stepCountSensor;
    Sensor stepDetectorSensor;
    int stepCount = 0;

    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_steps, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();

        tvRecordSteps = (TextView) v.findViewById(R.id.tvRecordSteps);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        return v;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];

        }
        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (stepCount == 0) {
                stepCount = value;
                tvRecordSteps.setText(String.valueOf(stepCount));
            }
        } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            stepCount += 1;
            tvRecordSteps.setText(String.valueOf(stepCount));
            editor.putString("lastStepCount", tvRecordSteps.getText().toString());
            editor.apply();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onResume() {
        super.onResume();

        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, stepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this, stepCountSensor);
        sensorManager.unregisterListener(this, stepDetectorSensor);
    }

    @Override
    public void onStart() {
        super.onStart();
        String lastStepCount = preferences.getString("lastStepCount", null);
        tvRecordSteps.setText(lastStepCount);
    }
}
