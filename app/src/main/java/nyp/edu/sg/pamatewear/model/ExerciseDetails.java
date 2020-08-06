/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear.model;

/**
 * Created by GBXM on 1/2/2018.
 */

public class ExerciseDetails {
    private int infoId;
    private String exerciseName;
    private String beatsPerMin;
    private String calories;
    private String stepsCount;
    private String distance;
    private String durationNeeded;
    private String patientPlanDetails;
    private int planId;

    public ExerciseDetails() {
    }

    public ExerciseDetails(int infoId, String exerciseName, String beatsPerMin, String calories, String stepsCount, String distance, String durationNeeded, String patientPlanDetails, int planId) {
        this.infoId = infoId;
        this.exerciseName = exerciseName;
        this.beatsPerMin = beatsPerMin;
        this.calories = calories;
        this.stepsCount = stepsCount;
        this.distance = distance;
        this.durationNeeded = durationNeeded;
        this.patientPlanDetails = patientPlanDetails;
        this.planId = planId;
    }

    public ExerciseDetails(String exerciseName, String beatsPerMin, String calories, String stepsCount, String distance, String durationNeeded, String patientPlanDetails, int planId) {
        this.exerciseName = exerciseName;
        this.beatsPerMin = beatsPerMin;
        this.calories = calories;
        this.stepsCount = stepsCount;
        this.distance = distance;
        this.durationNeeded = durationNeeded;
        this.patientPlanDetails = patientPlanDetails;
        this.planId = planId;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getBeatsPerMin() {
        return beatsPerMin;
    }

    public void setBeatsPerMin(String beatsPerMin) {
        this.beatsPerMin = beatsPerMin;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getStepsCount() {
        return stepsCount;
    }

    public void setStepsCount(String stepsCount) {
        this.stepsCount = stepsCount;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDurationNeeded() {
        return durationNeeded;
    }

    public void setDurationNeeded(String durationNeeded) {
        this.durationNeeded = durationNeeded;
    }

    public String getPatientPlanDetails() {
        return patientPlanDetails;
    }

    public void setPatientPlanDetails(String patientPlanDetails) {
        this.patientPlanDetails = patientPlanDetails;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
