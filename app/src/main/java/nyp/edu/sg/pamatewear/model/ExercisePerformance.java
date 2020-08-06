/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear.model;

/**
 * Created by GBXM on 1/1/2018.
 */

public class ExercisePerformance {
    private int performanceId;
    private String nameOfExercise;
    private String heartRate;
    private String caloriesBurnt;
    private String noOfSteps;
    private String distanceCovered;
    private String durationMeasured;
    private String startDate;
    private String patientPerf;
    private int planDetailsId;


    public ExercisePerformance() {
    }

    public ExercisePerformance(int performanceId, String nameOfExercise, String heartRate, String caloriesBurnt, String noOfSteps, String distanceCovered, String durationMeasured, String startDate, String patientPerf, int planDetailsId) {
        this.performanceId = performanceId;
        this.nameOfExercise = nameOfExercise;
        this.heartRate = heartRate;
        this.caloriesBurnt = caloriesBurnt;
        this.noOfSteps = noOfSteps;
        this.distanceCovered = distanceCovered;
        this.durationMeasured = durationMeasured;
        this.startDate = startDate;
        this.patientPerf = patientPerf;
        this.planDetailsId = planDetailsId;
    }

    public ExercisePerformance(String nameOfExercise, String heartRate, String caloriesBurnt, String noOfSteps, String distanceCovered, String durationMeasured, String startDate, String patientPerf, int planDetailsId) {
        this.nameOfExercise = nameOfExercise;
        this.heartRate = heartRate;
        this.caloriesBurnt = caloriesBurnt;
        this.noOfSteps = noOfSteps;
        this.distanceCovered = distanceCovered;
        this.durationMeasured = durationMeasured;
        this.startDate = startDate;
        this.patientPerf = patientPerf;
        this.planDetailsId = planDetailsId;
    }

    public int getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(int performanceId) {
        this.performanceId = performanceId;
    }

    public String getNameOfExercise() {
        return nameOfExercise;
    }

    public void setNameOfExercise(String nameOfExercise) {
        this.nameOfExercise = nameOfExercise;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getCaloriesBurnt() {
        return caloriesBurnt;
    }

    public void setCaloriesBurnt(String caloriesBurnt) {
        this.caloriesBurnt = caloriesBurnt;
    }

    public String getNoOfSteps() {
        return noOfSteps;
    }

    public void setNoOfSteps(String noOfSteps) {
        this.noOfSteps = noOfSteps;
    }

    public String getDistanceCovered() {
        return distanceCovered;
    }

    public void setDistanceCovered(String distanceCovered) {
        this.distanceCovered = distanceCovered;
    }

    public String getDurationMeasured() {
        return durationMeasured;
    }

    public void setDurationMeasured(String durationMeasured) {
        this.durationMeasured = durationMeasured;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPatientPerf() {
        return patientPerf;
    }

    public void setPatientPerf(String patientPerf) {
        this.patientPerf = patientPerf;
    }

    public int getPlanDetailsId() {
        return planDetailsId;
    }

    public void setPlanDetailsId(int planDetailsId) {
        this.planDetailsId = planDetailsId;
    }
}
