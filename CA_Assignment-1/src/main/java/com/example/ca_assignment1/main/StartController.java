package com.example.ca_assignment1.main;

import com.example.ca_assignment1.models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class StartController {
    @FXML
    ListView<Object> list;
    @FXML
    TextField search;

    @FXML
    protected void initialize(){ // adds items to the list view when loading into the view
        list.getItems().add("--- Pending Appointments ---");
        for (VaccinationCentre vac: Driver.systemAPI.vacCentres) {
            for (Booth booth: vac.getBooths()) {
                for (Appointment appointment: booth.getAppointments()) {
                    list.getItems().add(appointment);
                }
            }
        }

        list.getItems().add("--- Vaccination Records ---");
        for (Patient patient: Driver.systemAPI.patients){
            for (VaccinationRecord vac: patient.getRecords()) {
                list.getItems().add(vac);
            }
        }
    }

    @FXML
    protected void switchMain() throws IOException { // switches to main view
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        Driver.mainStage.setTitle("Vaccination System");
        Driver.mainStage.setScene(scene);
        Driver.mainStage.show();
    }

    @FXML
    protected void switchPatient() throws IOException { // switches to patient view
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        Driver.mainStage.setTitle("Vaccination System");
        Driver.mainStage.setScene(scene);
        Driver.mainStage.show();
    }

    @FXML
    public void reset(){ // completely resets the system
        Driver.systemAPI.vacCentres.clear();
        Driver.systemAPI.patients.clear();
        list.getItems().clear();
        initialize();
    }

    @FXML
    public void search(KeyEvent k){ // searches all appointments and appointments based on vaccination type and batch id
        list.getItems().clear();

        for (VaccinationCentre vac: Driver.systemAPI.vacCentres) {
            for (Booth booth: vac.getBooths()) {
                for (Appointment appointment: booth.getAppointments())
                if (appointment.getVacType().contains(search.getText())){
                    list.getItems().add(appointment);
                } else if (appointment.getBatchID().contains(search.getText())) {
                    list.getItems().add(appointment);
                }
            }
        }

        for (Patient patient: Driver.systemAPI.patients){
            for (VaccinationRecord vac: patient.getRecords()){
                if(vac.getVacType().contains(search.getText())){
                    list.getItems().add(vac);
                } else if (vac.getBatchID().contains(search.getText())) {
                    list.getItems().add(vac);
                }
            }
        }
    }

    @FXML
    public void save() throws IOException {
        Driver.save();
    }

    @FXML
    public void load() throws IOException, ClassNotFoundException {
        Driver.load();
        list.getItems().clear();
        initialize();
    }
}