/*
 * Copyright (c) 2020 Gwyn Bong Xiao Min.
 * All rights reserved.
 * Last Modified 6/8/20 12:54 PM
 */

package nyp.edu.sg.pamatewear.model;

/**
 * Created by GBXM on 1/1/2018.
 */

public class Patient {
    private int patientId;
    private String patientName;
    private String patientGender;
    private String patientDOB;
    private String patientHeight;
    private String patientWeight;
    private String patientEmail;
    private String patientUsername;
    private String patientPassword;
    private String dtLogin;
    private String dtLogout;
    private String memberSince;

    public Patient() {
    }

    public Patient(int patientId, String patientName, String patientGender, String patientDOB, String patientHeight, String patientWeight, String patientEmail, String patientUsername, String patientPassword, String memberSince) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientDOB = patientDOB;
        this.patientHeight = patientHeight;
        this.patientWeight = patientWeight;
        this.patientEmail = patientEmail;
        this.patientUsername = patientUsername;
        this.patientPassword = patientPassword;
        this.memberSince = memberSince;
    }

    public Patient(String patientName, String patientGender, String patientDOB, String patientHeight, String patientWeight, String patientEmail, String patientUsername, String patientPassword, String dtLogin, String dtLogout, String memberSince) {
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientDOB = patientDOB;
        this.patientHeight = patientHeight;
        this.patientWeight = patientWeight;
        this.patientEmail = patientEmail;
        this.patientUsername = patientUsername;
        this.patientPassword = patientPassword;
        this.dtLogin = dtLogin;
        this.dtLogout = dtLogout;
        this.memberSince = memberSince;
    }

    public Patient(String patientName, String patientGender, String patientDOB, String patientHeight, String patientWeight, String patientEmail, String patientUsername, String patientPassword) {
        this.patientName = patientName;
        this.patientGender = patientGender;
        this.patientDOB = patientDOB;
        this.patientHeight = patientHeight;
        this.patientWeight = patientWeight;
        this.patientEmail = patientEmail;
        this.patientUsername = patientUsername;
        this.patientPassword = patientPassword;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientDOB() {
        return patientDOB;
    }

    public void setPatientDOB(String patientDOB) {
        this.patientDOB = patientDOB;
    }

    public String getPatientHeight() {
        return patientHeight;
    }

    public void setPatientHeight(String patientHeight) {
        this.patientHeight = patientHeight;
    }

    public String getPatientWeight() {
        return patientWeight;
    }

    public void setPatientWeight(String patientWeight) {
        this.patientWeight = patientWeight;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }

    public String getDtLogin() {
        return dtLogin;
    }

    public void setDtLogin(String dtLogin) {
        this.dtLogin = dtLogin;
    }

    public String getDtLogout() {
        return dtLogout;
    }

    public void setDtLogout(String dtLogout) {
        this.dtLogout = dtLogout;
    }

    public String getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(String memberSince) {
        this.memberSince = memberSince;
    }
}
