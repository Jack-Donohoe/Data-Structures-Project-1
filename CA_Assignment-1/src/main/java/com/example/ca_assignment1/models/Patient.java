package com.example.ca_assignment1.models;

import com.example.ca_assignment1.utils.ConnectedList;
import com.example.ca_assignment1.utils.Utilities;

public class Patient {
    ConnectedList<VaccinationRecord> records = new ConnectedList<>();
    private String PPSN;
    private String name;
    private String dateOfBirth;
    private String address;
    private String eircode;
    private String phone;
    private String email;

    public Patient(String PPSN, String name, String dateOfBirth, String address, String eircode, String phone, String email) {
        if (Utilities.validPPS(PPSN)) {
            this.PPSN = PPSN;
        }
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.eircode = eircode;
        if (Utilities.onlyContainsNumbers(phone)) {
            this.phone = phone;
        }
        this.email = email;
    }

    public void addVaccinationRecord(String date, String vacType, String batchID, String vaccinator){
        VaccinationRecord record = new VaccinationRecord(date,vacType,batchID,vaccinator);
        records.add(record);
    }

    public String getPPSN() {
        return PPSN;
    }

    public void setPPSN(String PPSN) {
        this.PPSN = PPSN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEircode() {
        return eircode;
    }

    public void setEircode(String eircode) {
        this.eircode = eircode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ConnectedList<VaccinationRecord> getRecords() {
        return records;
    }

    public void setRecords(ConnectedList<VaccinationRecord> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return PPSN + "  " + name + "  " + dateOfBirth + "  " + address + "  " + eircode + "  " + phone + "  " + email + "\n";
    }
}
