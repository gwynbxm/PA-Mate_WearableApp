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
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.wearable.activity.WearableActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import nyp.edu.sg.pamatewear.constants.IntentName;
import nyp.edu.sg.pamatewear.model.ExercisePlan;
import nyp.edu.sg.pamatewear.sqlite.DatabaseHandler;

import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_PLAN_ID;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_PLAN_NAME;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_RECORDED_CALORIES;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_RECORDED_DISTANCE;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_RECORDED_DURATION;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_RECORDED_HEARTRATE;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_RECORDED_STEPS;

public class RecordExerciseActivity extends WearableActivity implements SensorEventListener {

    ImageButton btnPlay, btnPause, btnStop;
    TextView timerValue,
            tvPlanTitle,
            tvRecordingBpm,
            tvRecordingStep;

    Handler customHandler = new Handler();

    long startTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    ExercisePlan e;
    Context context;

    SensorManager sensorManager;
    Sensor heartRateSensor;
    Sensor stepCountSensor;
    Sensor stepDetectorSensor;
    int stepCount = 0;

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_record_exercise);
        e = new ExercisePlan();
        DatabaseHandler db = new DatabaseHandler(this);

        btnPlay = (ImageButton) findViewById(R.id.imgBtnPlayRecord);
        btnPause = (ImageButton) findViewById(R.id.imgBtnPauseRecord);
        btnStop = (ImageButton) findViewById(R.id.imgBtnStopRecord);
        timerValue = (TextView) findViewById(R.id.tvRecordValue);
        tvPlanTitle = (TextView) findViewById(R.id.tvPlanName);
        tvRecordingBpm = (TextView) findViewById(R.id.tvRecordingHeart);
        tvRecordingStep = (TextView) findViewById(R.id.tvREcordingSteps);

        final int id = getIntent().getIntExtra(IntentName.INTENT_EXERCISE_PLAN_ID, 0);
        e = db.getRecordPlan(id);
        tvPlanTitle.setText(e.getPlanName());

        sensorManager = ((SensorManager) getSystemService(SENSOR_SERVICE));
        heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);

                btnPause.setClickable(true);
                btnStop.setClickable(true);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);

                Intent i = new Intent(RecordExerciseActivity.this, RecordedDetailsActivity.class);

                i.putExtra(INTENT_EXERCISE_RECORDED_DURATION, timerValue.getText().toString());
                i.putExtra(INTENT_EXERCISE_PLAN_NAME, tvPlanTitle.getText().toString());
                i.putExtra(INTENT_EXERCISE_RECORDED_HEARTRATE, tvRecordingBpm.getText().toString());
                i.putExtra(INTENT_EXERCISE_RECORDED_STEPS, tvRecordingStep.getText().toString());
                i.putExtra(INTENT_EXERCISE_RECORDED_CALORIES, "423");
                i.putExtra(INTENT_EXERCISE_RECORDED_DISTANCE, "1.3");
                i.putExtra(INTENT_EXERCISE_PLAN_ID, id);
                startActivity(i);

                timerValue.setText(R.string.reset_timer);
                finish();
            }
        });
    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };

    @Override
    public void onStart() {
        super.onStart();

        sensorManager.registerListener(this, this.heartRateSensor, 3);
        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, stepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onStop() {
        super.onStop();

        sensorManager.unregisterListener(this, heartRateSensor);
        sensorManager.unregisterListener(this, stepCountSensor);
        sensorManager.unregisterListener(this, stepDetectorSensor);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;
        if (event.values[0] > 0 && sensor.getType() == Sensor.TYPE_HEART_RATE) {
            tvRecordingBpm.setText(String.valueOf((int) event.values[0]));
        }

        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }
        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (stepCount == 0) {
                stepCount = value;
                tvRecordingStep.setText(String.valueOf(stepCount));
            }
        } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            stepCount += 1;
            tvRecordingStep.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
