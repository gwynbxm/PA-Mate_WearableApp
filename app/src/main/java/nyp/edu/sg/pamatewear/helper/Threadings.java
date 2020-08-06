/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear.helper;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by GBXM on 1/2/2018.
 */

public class Threadings {
    public static void delay(final long ms, final Runnable runnable) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Handler(Looper.getMainLooper()).post(runnable);
            }
        }).start();
    }

    public static void postRunnable(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runInBackground(final Runnable runnable) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }).start();
    }
}
