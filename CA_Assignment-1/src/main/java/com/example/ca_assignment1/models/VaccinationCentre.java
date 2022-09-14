package com.example.ca_assignment1.models;

import com.example.ca_assignment1.utils.ConnectedList;
import com.example.ca_assignment1.utils.Utilities;

public class VaccinationCentre {
    ConnectedList<Booth> booths = new ConnectedList<>();
    private String name;
    private String address;
    private String eircode;
    private int parking;

    public VaccinationCentre(String name, String address, String eircode, int parking) {
        this.name = name;
        this.address = address;
        this.eircode = eircode;
        this.parking = parking;
    }

    public void addBooth(String boothID, String floorLevel, String accessInfo){
        Booth booth = new Booth(boothID,floorLevel,accessInfo);
        booths.add(booth);
    }

    public void removeBooth(String boothID){
        for (int i = 0; i < booths.size(); i++){
            if(boothID.equals(booths.get(i).getBoothID())){
                booths.remove(i);
            }
        }
    }

    public void setBooth(int index, String boothID, String floorLevel, String accessInfo){
        Booth newBooth = new Booth(boothID,floorLevel,accessInfo);

        if (index < booths.size()){
            booths.set(index,newBooth);
        }
    }

    public String getBoothDetails(int index){
        if (booths.get(index) != null) {
            return booths.get(index).toString();
        } else {
            return "Index is not valid";
        }
    }

    public int getNoOfAppointments(){
        int appointments = 0;
        for(Booth booth: booths){
            appointments = appointments + booth.appointments.size();
        }
        return appointments;
    }

    public Booth getSmallestBooth(){
        if(!booths.isEmpty()) {
            Booth smallestBooth = booths.get(0);

            for (Booth booth: booths){
                if(booth.appointments.size() < smallestBooth.appointments.size()){
                    smallestBooth = booth;
                }
            }

            return smallestBooth;
        } else {
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getParking() {
        return parking;
    }

    public void setParking(int parking) {
        this.parking = parking;
    }

    public ConnectedList<Booth> getBooths() {
        return booths;
    }

    public void setBooths(ConnectedList<Booth> booths) {
        this.booths = booths;
    }

    @Override
    public String toString() {
        return  name + "  " + address + "  " + eircode + "  " + parking + "\n";
    }
}
