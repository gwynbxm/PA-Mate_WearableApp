/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import nyp.edu.sg.pamatewear.model.ExerciseDetails;
import nyp.edu.sg.pamatewear.model.ExercisePlan;
import nyp.edu.sg.pamatewear.model.Patient;

/**
 * Created by GBXM on 1/1/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "pamate";

    private static final String TABLE_PATIENT = "Patient";

    private static final String KEY_PATIENT_ID = "id";
    private static final String KEY_NAME = "patientName";
    private static final String KEY_GENDER = "patientGender";
    private static final String KEY_DOB = "patientDOB";
    private static final String KEY_HEIGHT = "patientHeight";
    private static final String KEY_WEIGHT = "patientWeight";
    private static final String KEY_EMAIL = "patientEmail";
    private static final String KEY_USERNAME = "patientUsername";
    private static final String KEY_PASSWORD = "patientPassword";
    private static final String KEY_DTLOGIN = "dateTimeLogin";
    private static final String KEY_DTLOGOUT = "dateTimeLogout";
    private static final String KEY_MEMBERSINCE = "memberSince";

    private static final String CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_PATIENT + "(" +
            KEY_PATIENT_ID + " INTEGER PRIMARY KEY," +
            KEY_NAME + " TEXT," +
            KEY_GENDER + " TEXT," +
            KEY_DOB + " DATE," +
            KEY_HEIGHT + " FLOAT," +
            KEY_WEIGHT + " FLOAT," +
            KEY_EMAIL + " TEXT," +
            KEY_USERNAME + " TEXT," +
            KEY_PASSWORD + " TEXT," +
            KEY_DTLOGIN + " DATE," +
            KEY_DTLOGOUT + " DATE," +
            KEY_MEMBERSINCE + " DATE" + ")";

    private static final String TABLE_EXERCISE_PLAN = "ExercisePlan";

    private static final String KEY_EXERCISE_PLAN_ID = "id";
    private static final String KEY_PLAN_NAME = "planName";
    private static final String KEY_PLAN_DATE = "planDate";
    private static final String FOREIGN_KEY_PLAN_PATIENT_USERNAME = "patientPlan";

    private static final String CREATE_EXERCISE_PLAN_TABLE = "CREATE TABLE " + TABLE_EXERCISE_PLAN + "(" +
            KEY_EXERCISE_PLAN_ID + " INTEGER PRIMARY KEY," +
            KEY_PLAN_NAME + " TEXT," +
            KEY_PLAN_DATE + " TEXT," +
            FOREIGN_KEY_PLAN_PATIENT_USERNAME + " TEXT," +
            " FOREIGN KEY (" + FOREIGN_KEY_PLAN_PATIENT_USERNAME + ") REFERENCES " +
            TABLE_PATIENT + "(" + KEY_USERNAME + "))";

    private static final String TABLE_EXERCISE_DETAILS = "ExerciseDetails";

    private static final String KEY_EXERCISE_DETAILS_ID = "id";
    private static final String KEY_EXERCISE_NAME = "exerciseName";
    private static final String KEY_HEART_RATE = "beatsPerMin";
    private static final String KEY_CALORIES = "calories";
    private static final String KEY_STEPS_COUNT = "stepsCount";
    private static final String KEY_DISTANCE = "distance";
    private static final String KEY_DETAILS_DURATION = "duration";
    private static final String FOREIGN_KEY_DETAILS_PATIENT_USERNAME = "patientUsername";
    private static final String FOREIGN_KEY_DETAILS_PLAN_ID = "planId";

    private static final String CREATE_EXERCISE_DETAILS_TABLE = "CREATE TABLE " +
            TABLE_EXERCISE_DETAILS + "(" +
            KEY_EXERCISE_DETAILS_ID + " INTEGER PRIMARY KEY," +
            KEY_EXERCISE_NAME + " TEXT," +
            KEY_HEART_RATE + " INTEGER," +
            KEY_CALORIES + " DOUBLE," +
            KEY_STEPS_COUNT + " LONG," +
            KEY_DISTANCE + " DOUBLE," +
            KEY_DETAILS_DURATION + " TEXT," +
            FOREIGN_KEY_DETAILS_PATIENT_USERNAME + " TEXT," +
            FOREIGN_KEY_DETAILS_PLAN_ID + " INTEGER," +
            " FOREIGN KEY (" + FOREIGN_KEY_DETAILS_PATIENT_USERNAME + ") REFERENCES " +
            TABLE_PATIENT + "(" + KEY_USERNAME + ")," +
            " FOREIGN KEY (" + FOREIGN_KEY_DETAILS_PLAN_ID + ") REFERENCES " +
            TABLE_EXERCISE_PLAN + "(" + KEY_EXERCISE_PLAN_ID + "))";

    private static final String TABLE_EXERCISE_PERFORMANCE = "ExercisePerformance";

    private static final String KEY_EXERCISE_PERFORMANCE_ID = "exercisePerfId";
    private static final String KEY_PERFORMANCE_EXERCISE_NAME = "exerciseName";
    private static final String KEY_PERFORMANCE_HEART_RATE = "recordedBeatsPerMin";
    private static final String KEY_PERFORMANCE_CALORIES = "recordCalories";
    private static final String KEY_PERFORMANCE_STEPS = "recordedSteps";
    private static final String KEY_PERFORMANCE_DISTANCE = "recordedDistance";
    private static final String KEY_PERFORMANCE_DURATION = "recordedDuration";
    private static final String KEY_ACTUAL_START_DATE = "actualStartDate";
    private static final String FOREIGN_KEY_PERFORMANCE_PATIENT_USERNAME = "patientUsername";
    private static final String FOREIGN_KEY_PERFORMANCE_PLAN_ID = "planId";

    private static final String CREATE_EXERCISE_PERFORMANCE_TABLE = "CREATE TABLE " +
            TABLE_EXERCISE_PERFORMANCE + "(" +
            KEY_EXERCISE_PERFORMANCE_ID + " INTEGER PRIMARY KEY," +
            KEY_PERFORMANCE_EXERCISE_NAME + " TEXT," +
            KEY_PERFORMANCE_HEART_RATE + " INTEGER," +
            KEY_PERFORMANCE_CALORIES + " DOUBLE," +
            KEY_PERFORMANCE_STEPS + " LONG," +
            KEY_PERFORMANCE_DISTANCE + " DOUBLE," +
            KEY_PERFORMANCE_DURATION + " TEXT," +
            KEY_ACTUAL_START_DATE + " DATE," +
            FOREIGN_KEY_PERFORMANCE_PATIENT_USERNAME + " TEXT," +
            FOREIGN_KEY_PERFORMANCE_PLAN_ID + " INTEGER," +
            " FOREIGN KEY (" + FOREIGN_KEY_PERFORMANCE_PATIENT_USERNAME + ") REFERENCES " +
            TABLE_PATIENT + "(" + KEY_USERNAME + ")," +
            " FOREIGN KEY (" + FOREIGN_KEY_PERFORMANCE_PLAN_ID + ") REFERENCES " +
            TABLE_EXERCISE_PLAN + "(" + KEY_EXERCISE_PLAN_ID + "))";

    SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PATIENT_TABLE);
        db.execSQL(CREATE_EXERCISE_PLAN_TABLE);
        db.execSQL(CREATE_EXERCISE_DETAILS_TABLE);
        db.execSQL(CREATE_EXERCISE_PERFORMANCE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE_PLAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE_PERFORMANCE);

        onCreate(db);
    }

    public void open() throws SQLiteException {
        try {
            db = this.getWritableDatabase();
        } catch (SQLiteException e) {
            db = this.getReadableDatabase();
        }
    }

    public void close() {
        db.close();
    }

    public boolean createPatient(String patient_name, String patient_gender,
                                 String patient_dob, String patient_height, String patient_weight,
                                 String patient_email, String patient_username,
                                 String patient_password) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, patient_name);
        values.put(KEY_GENDER, patient_gender);
        values.put(KEY_DOB, patient_dob);
        values.put(KEY_HEIGHT, patient_height);
        values.put(KEY_WEIGHT, patient_weight);
        values.put(KEY_EMAIL, patient_email);
        values.put(KEY_USERNAME, patient_username);
        values.put(KEY_PASSWORD, patient_password);
        //values.put(KEY_MEMBERSINCE, member_since);

        long create_patient = db.insert(TABLE_PATIENT, null, values);

        db.close();
        if (create_patient == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Patient getPatientDetails(String username) {

        Patient p = null;

        String selectQuery = "SELECT * FROM " + TABLE_PATIENT + " WHERE "
                + KEY_USERNAME + " = '" + username + "'";

        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                p = new Patient(
                        c.getString(c.getColumnIndex(KEY_NAME)),
                        c.getString(c.getColumnIndex(KEY_GENDER)),
                        c.getString(c.getColumnIndex(KEY_DOB)),
                        c.getString(c.getColumnIndex(KEY_HEIGHT)),
                        c.getString(c.getColumnIndex(KEY_WEIGHT)),
                        c.getString(c.getColumnIndex(KEY_EMAIL)),
                        c.getString(c.getColumnIndex(KEY_USERNAME)),
                        c.getString(c.getColumnIndex(KEY_PASSWORD)));
            } while (c.moveToNext());
        }
        db.close();
        return p;
    }
    public long createExercisePlan(String plan_name, String plan_date, String username) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PLAN_NAME, plan_name);
        values.put(KEY_PLAN_DATE, plan_date);
        values.put(FOREIGN_KEY_PLAN_PATIENT_USERNAME, username);

        long create_plan = db.insert(TABLE_EXERCISE_PLAN, null, values);

        db.close();
        return create_plan;
    }

    public ArrayList<ExercisePlan> getPatientAllPlans(String username) {

        ArrayList<ExercisePlan> planList = new ArrayList<ExercisePlan>();

        String selectQuery = "SELECT * FROM " + TABLE_EXERCISE_PLAN + " WHERE "
                + FOREIGN_KEY_PLAN_PATIENT_USERNAME + " = '" + username + "'";

        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                ExercisePlan e = new ExercisePlan(
                        c.getInt(c.getColumnIndex(KEY_EXERCISE_PLAN_ID)),
                        c.getString(c.getColumnIndex(KEY_PLAN_NAME)),
                        c.getString(c.getColumnIndex(KEY_PLAN_DATE)),
                        c.getString(c.getColumnIndex(FOREIGN_KEY_PLAN_PATIENT_USERNAME)));

                planList.add(e);
            } while (c.moveToNext());
        }
        db.close();
        return planList;
    }

    public ArrayList<ExercisePlan> getExercisePlansOfToday(String date) {
        ArrayList<ExercisePlan> planList = new ArrayList<ExercisePlan>();

        String selectQuery = "SELECT * FROM " + TABLE_EXERCISE_PLAN + " WHERE "
                + KEY_PLAN_DATE + " = '" + date + "'";

        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                ExercisePlan e = new ExercisePlan(
                        c.getInt(c.getColumnIndex(KEY_EXERCISE_PLAN_ID)),
                        c.getString(c.getColumnIndex(KEY_PLAN_NAME)),
                        c.getString(c.getColumnIndex(KEY_PLAN_DATE)),
                        c.getString(c.getColumnIndex(FOREIGN_KEY_PLAN_PATIENT_USERNAME)));

                planList.add(e);
            } while (c.moveToNext());
        }
        db.close();
        return planList;
    }

    public ExercisePlan getRecordPlan(long planId) {

        ExercisePlan ep = null;

        String selectQuery = "SELECT * FROM " + TABLE_EXERCISE_PLAN + " WHERE "
                + KEY_EXERCISE_PLAN_ID + " = '" + planId + "'";

        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                ep = new ExercisePlan(
                        c.getInt(c.getColumnIndex(KEY_EXERCISE_PLAN_ID)),
                        c.getString(c.getColumnIndex(KEY_PLAN_NAME)),
                        c.getString(c.getColumnIndex(KEY_PLAN_DATE)),
                        c.getString(c.getColumnIndex(FOREIGN_KEY_PLAN_PATIENT_USERNAME)));
            } while (c.moveToNext());
        }
        db.close();
        return ep;
    }

    public boolean createExercisePlanDetails(String exerciseName, String beatsPerMin, String calories,
                                             String stepCount, String distance, String duration,
                                             String username, int plan_id) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_EXERCISE_NAME, exerciseName);
        values.put(KEY_HEART_RATE, beatsPerMin);
        values.put(KEY_CALORIES, calories);
        values.put(KEY_STEPS_COUNT, stepCount);
        values.put(KEY_DISTANCE, distance);
        values.put(KEY_DETAILS_DURATION, duration);
        values.put(FOREIGN_KEY_DETAILS_PATIENT_USERNAME, username);
        values.put(FOREIGN_KEY_DETAILS_PLAN_ID, plan_id);

        long create_plan_details = db.insert(TABLE_EXERCISE_DETAILS, null, values);

        db.close();
        if (create_plan_details == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ExerciseDetails getPlanDetail(long planId) {

        ExerciseDetails e = null;

        String selectQuery = "SELECT * FROM " + TABLE_EXERCISE_DETAILS + " WHERE "
                + FOREIGN_KEY_DETAILS_PLAN_ID + " = '" + planId + "'";

        db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                e = new ExerciseDetails(
                        c.getInt(c.getColumnIndex(KEY_EXERCISE_DETAILS_ID)),
                        c.getString(c.getColumnIndex(KEY_EXERCISE_NAME)),
                        c.getString(c.getColumnIndex(KEY_HEART_RATE)),
                        c.getString(c.getColumnIndex(KEY_CALORIES)),
                        c.getString(c.getColumnIndex(KEY_STEPS_COUNT)),
                        c.getString(c.getColumnIndex(KEY_DISTANCE)),
                        c.getString(c.getColumnIndex(KEY_DETAILS_DURATION)),
                        c.getString(c.getColumnIndex(FOREIGN_KEY_DETAILS_PATIENT_USERNAME)),
                        c.getInt(c.getColumnIndex(FOREIGN_KEY_DETAILS_PLAN_ID)));
            } while (c.moveToNext());
        }
        db.close();
        return e;
    }

    public boolean createExercisePerformance(String exerciseName, String beatsPerMin, String calories,
                                             String stepCount, String distance, String duration,
                                             String actualStartDate, String username,
                                             int details_id) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PERFORMANCE_EXERCISE_NAME, exerciseName);
        values.put(KEY_PERFORMANCE_HEART_RATE, beatsPerMin);
        values.put(KEY_PERFORMANCE_CALORIES, calories);
        values.put(KEY_PERFORMANCE_STEPS, stepCount);
        values.put(KEY_PERFORMANCE_DISTANCE, distance);
        values.put(KEY_PERFORMANCE_DURATION, duration);
        values.put(KEY_ACTUAL_START_DATE, actualStartDate);
        values.put(FOREIGN_KEY_PERFORMANCE_PATIENT_USERNAME, username);
        values.put(FOREIGN_KEY_PERFORMANCE_PLAN_ID, details_id);

        long create_exercise_performance = db.insert(TABLE_EXERCISE_PERFORMANCE, null, values);

        db.close();
        if (create_exercise_performance == -1) {
            return false;
        } else {
            return true;
        }
    }
}
