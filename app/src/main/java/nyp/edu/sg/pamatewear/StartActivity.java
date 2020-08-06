/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import nyp.edu.sg.pamatewear.helper.MobileWearableService;
import nyp.edu.sg.pamatewear.model.Patient;
import nyp.edu.sg.pamatewear.sqlite.DatabaseHandler;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final DatabaseHandler db = new DatabaseHandler(this);

        Patient p = new Patient();

        boolean isCreated =
                db.createPatient("Gwyn", "Female",
                        "21-08-1995", "168", "55",
                        "gwyn@gmail.com", "gwyngwyn",
                        "111");

        if (isCreated) {
            Log.d("patient", "Created Patient");
            long newExercisePlanId = db.createExercisePlan("Walk for 30 mins", "12-02-2018", "gwyngwyn");

            if (newExercisePlanId != -1) {
                Log.d("plan", "Created Exercise Plan");

                boolean createDetails = db.createExercisePlanDetails(
                        "Walk", "95", "5.9", "0", "2", "1", "gwyngwyn",
                        (int) newExercisePlanId);
                if (createDetails) {
                    Log.d("details", "Created Exercise Plan Details");

                } else {
                    Log.d("error", "Failed to create Exercise Plan Details");
                }
            } else {
                Log.d("error", "Failed to create Exercise plan");
            }
        }
        ImageButton imgLogo = (ImageButton) findViewById(R.id.imgBtnLogo);

        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
