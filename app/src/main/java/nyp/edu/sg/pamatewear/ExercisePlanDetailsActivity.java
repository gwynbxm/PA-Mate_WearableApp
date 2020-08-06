/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nyp.edu.sg.pamatewear.constants.IntentName;
import nyp.edu.sg.pamatewear.model.ExerciseDetails;
import nyp.edu.sg.pamatewear.sqlite.DatabaseHandler;

import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_PLAN_ID;

public class ExercisePlanDetailsActivity extends WearableActivity {

    TextView tvDuration,
            tvHeartRate,
            tvCalories,
            tvDistance,
            tvNoOfSteps;

    Button btnStartExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_plan_details);

        ExerciseDetails epd = new ExerciseDetails();
        DatabaseHandler db = new DatabaseHandler(this);

        tvDuration = (TextView) findViewById(R.id.tvPlanDuration);
        tvHeartRate = (TextView) findViewById(R.id.tvPlanHeart);
        tvCalories = (TextView) findViewById(R.id.tvPlanCalories);
        tvDistance = (TextView) findViewById(R.id.tvPlanDistance);
        tvNoOfSteps = (TextView) findViewById(R.id.tvPlanSteps);
        btnStartExercise = (Button) findViewById(R.id.btnStartExercise);

        final int id = getIntent().getIntExtra(IntentName.INTENT_EXERCISE_PLAN_ID, 0);
        epd = db.getPlanDetail(id);

        tvDuration.setText(epd.getDurationNeeded());
        tvHeartRate.setText(epd.getBeatsPerMin());
        tvCalories.setText(epd.getCalories());
        tvDistance.setText(epd.getDistance());
        tvNoOfSteps.setText(epd.getStepsCount());

        btnStartExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), RecordExerciseActivity.class);
                intent.putExtra(INTENT_EXERCISE_PLAN_ID, id);
                startActivity(intent);
                finish();
            }
        });
    }
}
