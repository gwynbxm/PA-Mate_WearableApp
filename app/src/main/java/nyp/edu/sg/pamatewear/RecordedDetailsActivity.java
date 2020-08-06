/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import nyp.edu.sg.pamatewear.constants.IntentName;
import nyp.edu.sg.pamatewear.helper.Threadings;
import nyp.edu.sg.pamatewear.model.ExerciseDetails;
import nyp.edu.sg.pamatewear.model.Patient;
import nyp.edu.sg.pamatewear.sqlite.DatabaseHandler;

import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_PLAN_NAME;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_RECORDED_CALORIES;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_RECORDED_DISTANCE;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_RECORDED_DURATION;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_RECORDED_HEARTRATE;
import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_RECORDED_STEPS;

public class RecordedDetailsActivity extends WearableActivity {

    ExerciseDetails ed;
    Patient p;
    ImageView imgExerciseIcon;
    TextView planName, recordedDuration, recordedBpm, recordedSteps, recordedCalories, recordedDist;
    ImageButton btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_details);

        ed = new ExerciseDetails();
        p = new Patient();
        DatabaseHandler db = new DatabaseHandler(this);

        imgExerciseIcon = findViewById(R.id.imgExerciseIcon);
        planName = findViewById(R.id.tvRecordedPlanName);
        recordedDuration = findViewById(R.id.tvRecordedDuration);
        recordedBpm = findViewById(R.id.tvDisplayHeart);
        recordedSteps = findViewById(R.id.tvDisplaySteps);
        recordedCalories = findViewById(R.id.tvDisplayCalories);
        recordedDist = findViewById(R.id.tvDisplayDistance);
        btnDone = (findViewById(R.id.btnDone));

        String duration = getIntent().getStringExtra(INTENT_EXERCISE_RECORDED_DURATION);
        String exerciseName = getIntent().getStringExtra(INTENT_EXERCISE_PLAN_NAME);
        String heartRate = getIntent().getStringExtra(INTENT_EXERCISE_RECORDED_HEARTRATE);
        String steps = getIntent().getStringExtra(INTENT_EXERCISE_RECORDED_STEPS);
        String calories = getIntent().getStringExtra(INTENT_EXERCISE_RECORDED_CALORIES);
        String distance = getIntent().getStringExtra(INTENT_EXERCISE_RECORDED_DISTANCE);

        final int id = getIntent().getIntExtra(IntentName.INTENT_EXERCISE_PLAN_ID, 0);

        planName.setText(exerciseName);
        recordedDuration.setText(duration);
        recordedBpm.setText(heartRate);
        recordedSteps.setText(steps);
        recordedCalories.setText(calories);
        recordedDist.setText(distance);

        ed = db.getPlanDetail(id);

        boolean isPerfCreate = db.createExercisePerformance(
                exerciseName, heartRate, calories, steps, distance,
                duration, getCurrentDate(), "gwyngwyn", id);

        if (isPerfCreate) {
            Log.d("performance", "Created Exercise Performance");

        } else {
            Log.d("error", "Failed to create Exercise Performance");
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), HomeActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("dd-MM-yyyy",
                        Locale.US);

        return dateFormat.format(new Date());
    }
}
