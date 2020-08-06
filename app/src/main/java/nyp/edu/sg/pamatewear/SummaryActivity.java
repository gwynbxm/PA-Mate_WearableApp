/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.wearable.view.WatchViewStub;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;

public class SummaryActivity extends Fragment {
    DecoView decoView;

    private int backIndex;
    private int series1Index;
    private int series2Index;
    private int series3Index;

    TextView tvNumber;

    private final float seriesMax = 50f;

    Context context;
    public void setContext(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_summary, container, false);
        decoView = (DecoView) v.findViewById(R.id.dvSummaryOfExercises);
        tvNumber = (TextView) v.findViewById(R.id.tvNumber);
        createBackSeries();
        createSeries1();
        return v;
    }

    private void createBackSeries() {
        SeriesItem seriesItem =
                new SeriesItem.Builder(Color.parseColor("#FF2929")).setRange(0, seriesMax, 0)
                        .setInitialVisibility(true).build();

        int backIndex = decoView.addSeries(seriesItem);
    }

    private void createSeries1() {
        final SeriesItem seriesItem1 =
                new SeriesItem.Builder(Color.parseColor("#FF7029")).setRange(0, seriesMax, 0)
                        .setInitialVisibility(false).build();

        seriesItem1.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((30 - seriesItem1.getMinValue() /
                        seriesItem1.getMaxValue() - seriesItem1.getMinValue()));

                tvNumber.setText(String.format("%.0f%", percentFilled * 100f));
            }

            @Override
            public void onSeriesItemDisplayProgress(float v) {

            }
        });
    }
}
