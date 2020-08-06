/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.GridViewPager;
import android.view.View;
import android.view.WindowInsets;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import nyp.edu.sg.pamatewear.helper.GridViewPagerAdapter;

public class HomeActivity extends Activity {

    private static GridViewPager pagerGlobal;

    public static GridViewPager getPagerGlobal() {
        return pagerGlobal;
    }
    GridViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Resources res = getResources();
        pager = (GridViewPager) findViewById(R.id.pager);
        pager.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                pager.onApplyWindowInsets(insets);
                return insets;
            }
        });
        pager.setAdapter(new GridViewPagerAdapter(this, getFragmentManager()));

        pagerGlobal = pager;
        DotsPageIndicator dotsPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);
        dotsPageIndicator.setPager(pager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pager.setCurrentItem(1, 0);
    }
}
