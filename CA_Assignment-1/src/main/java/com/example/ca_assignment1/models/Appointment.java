package com.example.ca_assignment1.models;

public class Appointment {
    private String date;
    private String time;
    private String vacType;
    private String batchID;
    private String vaccinator;
    private String PPSN;

    public Appointment(String date, String time, String vacType, String batchID, String vaccinator, String PPSN) {
        this.date = date;
        this.time = time;
        this.vacType = vacType;
        this.batchID = batchID;
        this.vaccinator = vaccinator;
        this.PPSN = PPSN;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVacType() {
        return vacType;
    }

    public void setVacType(String vacType) {
        this.vacType = vacType;
    }

    public String getBatchID() {
        return batchID;
    }

    public void setBatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getVaccinator() {
        return vaccinator;
    }

    public void setVaccinator(String vaccinator) {
        this.vaccinator = vaccinator;
    }

    public String getPPSN() {
        return PPSN;
    }

    public void setPPSN(String PPSN) {
        this.PPSN = PPSN;
    }

    @Override
    public String toString() {
        return date + "  " + time + "  " + vacType + "  " + batchID + "  " + vaccinator + "  " + PPSN + "\n";
    }
}
