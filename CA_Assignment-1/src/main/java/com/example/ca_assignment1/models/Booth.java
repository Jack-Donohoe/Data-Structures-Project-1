package com.example.ca_assignment1.models;

import com.example.ca_assignment1.utils.ConnectedList;

public class Booth {
    ConnectedList<Appointment> appointments = new ConnectedList<>();
    private String boothID;
    private String floorLevel;
    private String accessInfo;

    public Booth(String boothID, String floorLevel, String accessInfo) {
        this.boothID = boothID;
        this.floorLevel = floorLevel;
        this.accessInfo = accessInfo;
    }

    public void addAppointment(String date, String time, String vacType, String batchID, String vaccinator, String PPSN){
        Appointment appointment = new Appointment(date,time,vacType,batchID,vaccinator,PPSN);
        appointments.add(appointment);
    }

    public void removeAppointment(int index){
        for (int i = 0; i < appointments.size(); i++){
            if(i == index){
                appointments.remove(i);
            }
        }
    }

    public void removeAppointment(Appointment appointment){
        for (int i = 0; i < appointments.size(); i++){
            if (appointment.toString().equals(appointments.get(i).toString())){
                appointments.remove(i);
            }
        }
    }

    public void setAppointment(int index,String date, String time, String vacType, String batchID, String vaccinator, String PPSN){
        Appointment newAppointment = new Appointment(date, time, vacType, batchID, vaccinator, PPSN);

        if (index < appointments.size()){
            appointments.set(index,newAppointment);
        }
    }

    public String getAppointmentDetails(int index){
        if (appointments.get(index) != null) {
            return appointments.get(index).toString();
        } else {
            return "Index is not valid";
        }
    }

    public String getBoothID() {
        return boothID;
    }

    public void setBoothID(String boothID) {
        this.boothID = boothID;
    }

    public String getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(String floorLevel) {
        this.floorLevel = floorLevel;
    }

    public String getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(String accessInfo) {
        this.accessInfo = accessInfo;
    }

    public ConnectedList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ConnectedList<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return boothID + "  " + floorLevel + "  " + accessInfo + "\n";
    }
}
