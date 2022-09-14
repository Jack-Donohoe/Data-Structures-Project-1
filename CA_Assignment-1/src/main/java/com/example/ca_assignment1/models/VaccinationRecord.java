package com.example.ca_assignment1.models;

public class VaccinationRecord {
    private String date;
    private String vacType;
    private String batchID;
    private String vaccinator;

    public VaccinationRecord(String date, String vacType, String batchID, String vaccinator) {
        this.date = date;
        this.vacType = vacType;
        this.batchID = batchID;
        this.vaccinator = vaccinator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    @Override
    public String toString() {
        return date + "  " + vacType + "  " + batchID + "  " + vaccinator + "\n";
    }
}
