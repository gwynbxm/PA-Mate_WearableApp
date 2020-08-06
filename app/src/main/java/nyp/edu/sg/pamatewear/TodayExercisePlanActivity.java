/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.wear.ambient.AmbientMode;
import android.support.wearable.view.WearableRecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import nyp.edu.sg.pamatewear.helper.RecyclerTouchListener;
import nyp.edu.sg.pamatewear.listview.WearableRecyclerViewAdapter;
import nyp.edu.sg.pamatewear.model.ExercisePlan;
import nyp.edu.sg.pamatewear.sqlite.DatabaseHandler;

import static nyp.edu.sg.pamatewear.constants.IntentName.INTENT_EXERCISE_PLAN_ID;

public class TodayExercisePlanActivity extends Fragment implements AmbientMode.AmbientCallbackProvider {

    LinearLayout parent;
    Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    //    ListView listview;
//    WearableAdapter listOfExercisePlans;
    private WearableRecyclerView wearableRecyclerView;
    private WearableRecyclerViewAdapter wearableRecyclerViewAdapter;
    ArrayList<ExercisePlan> exercisePlan;
    TextView tvDate;

    @Nullable
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_today_exercise_plan, container, false);

//        listview = v.findViewById(R.id.lvExercisePlans);
        wearableRecyclerView = (WearableRecyclerView) v.findViewById(R.id.rvExercisePlans);
        exercisePlan = new ArrayList<ExercisePlan>();

        tvDate = v.findViewById(R.id.tvTodayDate);
        tvDate.setText(getCurrentDate());

        retrieveExercisePlanOfTheDay();

        wearableRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), wearableRecyclerView, new RecyclerTouchListener.RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity().getApplicationContext(),
                        ExercisePlanDetailsActivity.class);

                ExercisePlan e = exercisePlan.get(position);
                intent.putExtra(INTENT_EXERCISE_PLAN_ID, e.getPlanId());
                startActivity(intent);
            }
        }));
        return v;
    }

    private void retrieveExercisePlanOfTheDay() {
        DatabaseHandler db = new DatabaseHandler(getContext());

        if (wearableRecyclerView != null) {
            exercisePlan = db.getExercisePlansOfToday(getCurrentDate());
            wearableRecyclerViewAdapter = new WearableRecyclerViewAdapter(
                    exercisePlan, getActivity());
            wearableRecyclerView.setAdapter(wearableRecyclerViewAdapter);
        }
    }

    private static String getCurrentDate() {
        SimpleDateFormat dateFormat =
                new SimpleDateFormat("dd-MM-yyyy",
                        Locale.US);

        return dateFormat.format(new Date());
    }

    @Override
    public AmbientMode.AmbientCallback getAmbientCallback() {
        return null;
    }
}
