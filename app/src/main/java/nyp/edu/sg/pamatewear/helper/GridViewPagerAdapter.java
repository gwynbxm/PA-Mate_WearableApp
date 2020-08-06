/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear.helper;

import android.app.Fragment;
import android.content.Context;
import android.app.FragmentManager;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.view.ViewGroup;

import nyp.edu.sg.pamatewear.HeartRateActivity;
import nyp.edu.sg.pamatewear.RecordExerciseActivity;
import nyp.edu.sg.pamatewear.StepsActivity;
import nyp.edu.sg.pamatewear.SummaryActivity;
import nyp.edu.sg.pamatewear.TodayExercisePlanActivity;

/**
 * Created by GBXM on 30/12/2017.
 */
public class GridViewPagerAdapter extends FragmentGridPagerAdapter {
    TodayExercisePlanActivity todayExercisePlanActivity;
    HeartRateActivity heartRateActivity;
    StepsActivity stepsActivity;

    public TodayExercisePlanActivity getTodayExercisePlanActivity() {
        return todayExercisePlanActivity;
    }

    public HeartRateActivity getHeartRateActivity() {
        return heartRateActivity;
    }

    public StepsActivity getStepsActivity() {
        return stepsActivity;
    }

    public GridViewPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        todayExercisePlanActivity = new TodayExercisePlanActivity();
        todayExercisePlanActivity.setContext(context);
        heartRateActivity = new HeartRateActivity();
        stepsActivity = new StepsActivity();
    }

    @Override
    public Fragment getFragment(int row, int col) {
        if (col == 0) {
            return todayExercisePlanActivity;
        } else if (col == 1) {
            return heartRateActivity;
        } else {
            return stepsActivity;
        }
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount(int i) {
        return 3;
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int row, int column) {
        return super.instantiateItem(container, row, column);
    }

    @Override
    public int getCurrentColumnForRow(int row, int currentColumn) {
        return currentColumn;
    }
}
