/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear.model;

/**
 * Created by GBXM on 1/1/2018.
 */

public class ExercisePlan {
    private int planId;
    private String planName;
    private String planStartDate;
    private String patientPlan;

    public ExercisePlan() {
    }

    public ExercisePlan(int planId, String planName, String planStartDate, String patientPlan) {
        this.planId = planId;
        this.planName = planName;
        this.planStartDate = planStartDate;
        this.patientPlan = patientPlan;
    }

    public ExercisePlan(String planName, String planStartDate) {
        this.planName = planName;
        this.planStartDate = planStartDate;
    }

    public ExercisePlan(String planName, String planStartDate, String patientPlan) {
        this.planName = planName;
        this.planStartDate = planStartDate;
        this.patientPlan = patientPlan;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(String planStartDate) {
        this.planStartDate = planStartDate;
    }

    public String getPatientPlan() {
        return patientPlan;
    }

    public void setPatientPlan(String patientPlan) {
        this.patientPlan = patientPlan;
    }
}
