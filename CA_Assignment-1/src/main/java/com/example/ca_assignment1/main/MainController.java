package com.example.ca_assignment1.main;

import com.example.ca_assignment1.models.Appointment;
import com.example.ca_assignment1.models.Booth;
import com.example.ca_assignment1.models.Patient;
import com.example.ca_assignment1.models.VaccinationCentre;
import com.example.ca_assignment1.utils.AlertBox;
import com.example.ca_assignment1.utils.ConnectedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML
    public Label titleText;
    @FXML
    public TextField name,address,eircode,parking,boothID,floorLevel,accessInfo,vaccinator,batchID;
    @FXML
    public ComboBox<String> vacType, time, ppsn;
    @FXML
    public DatePicker date;
    @FXML
    public ListView<Object> list;
    @FXML
    public AnchorPane vacCentrePane, boothPane, appointmentPane;
    @FXML
    public VaccinationCentre currentCentre;
    @FXML
    public Booth currentBooth;
    @FXML
    public Appointment currentAppointment;

    @FXML
    public void initialize(){ // adds items to the list view when loading into the view
        list.getItems().clear();

        for(VaccinationCentre vac: Driver.systemAPI.vacCentres){
            list.getItems().add(vac);
        }

        for(int i = 0; i < 9; i++){
            int hour = 8 + i;
            time.getItems().add(hour +":00");
        }

        vacType.getItems().add("Pfizer");
        vacType.getItems().add("Moderna");
        vacType.getItems().add("AstraZeneca");
        vacType.getItems().add("Janssen");
        vacType.getItems().add("Sputnik");

        if (!Driver.systemAPI.patients.isEmpty()){
            for (Patient patient: Driver.systemAPI.patients){
                ppsn.getItems().add(patient.getPPSN());
            }
        }
    }

    @FXML
    public void setView(MouseEvent e){ // drills down when an item is double clicked
        if (e.getClickCount() == 2) {
            if (!Driver.systemAPI.vacCentres.isEmpty() && vacCentrePane.isVisible()) {
                if (list.getSelectionModel().getSelectedIndex() >= 0) {
                    titleText.setText("Booths");
                    vacCentrePane.setVisible(false);
                    boothPane.setVisible(true);
                    currentCentre = Driver.systemAPI.vacCentres.get(list.getSelectionModel().getSelectedIndex());
                    list.getItems().clear();
                    if (currentCentre != null) {
                        for (Booth booth: currentCentre.getBooths()) {
                            list.getItems().add(booth);
                        }
                    }
                }
            }

            if (currentCentre != null && !currentCentre.getBooths().isEmpty() && boothPane.isVisible()) {
                if (list.getSelectionModel().getSelectedIndex() >= 0) {
                    titleText.setText("Appointments");
                    boothPane.setVisible(false);
                    appointmentPane.setVisible(true);
                    currentBooth = currentCentre.getBooths().get(list.getSelectionModel().getSelectedIndex());
                    list.getItems().clear();
                    if (currentBooth != null) {
                        for (Appointment appointment: currentBooth.getAppointments()) {
                            list.getItems().add(appointment);
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void addItem(){ // adds an item to the linked list and list view depending on which pane is visible
        if(vacCentrePane.isVisible()) {
            try {
                if (Integer.parseInt(parking.getText()) >= 1) {
                    Driver.addVacCentre(name.getText(), address.getText(), eircode.getText(), Integer.parseInt(parking.getText()));
                    list.getItems().add(new VaccinationCentre(name.getText(), address.getText(), eircode.getText(), Integer.parseInt(parking.getText())).toString());
                    name.clear();
                    address.clear();
                    eircode.clear();
                    parking.clear();
                } else {
                    AlertBox.displayAlert("Incorrect value entered","Not a valid number");
                }
            } catch (RuntimeException runtimeException) {
                AlertBox.displayAlert("Error",runtimeException.getMessage());
            }
        }

        if(boothPane.isVisible()){
            try {
                if (!currentCentre.getBooths().listAll().contains(boothID.getText())) {
                    currentCentre.addBooth(boothID.getText(), floorLevel.getText(), accessInfo.getText());
                    list.getItems().add(new Booth(boothID.getText(), floorLevel.getText(), accessInfo.getText()));
                    boothID.clear();
                    floorLevel.clear();
                    accessInfo.clear();
                } else {
                    AlertBox.displayAlert("Incorrect value entered","Booth ID already exists");
                }

            } catch (RuntimeException runtimeException) {
                AlertBox.displayAlert("Error",runtimeException.getMessage());
            }
        }

        if(appointmentPane.isVisible()){
            for (VaccinationCentre vac: Driver.systemAPI.vacCentres) {
                if (Driver.getPatient(ppsn.getSelectionModel().getSelectedItem()).getEircode().contains(currentCentre.getEircode().substring(0, 3))) {
                    currentBooth.addAppointment(date.getValue().toString(), time.getSelectionModel().getSelectedItem(), vacType.getSelectionModel().getSelectedItem(), batchID.getText(), vaccinator.getText(), ppsn.getSelectionModel().getSelectedItem().toString());
                    list.getItems().add(new Appointment(date.getValue().toString(), time.getSelectionModel().getSelectedItem(), vacType.getSelectionModel().getSelectedItem(), batchID.getText(), vaccinator.getText(), ppsn.getSelectionModel().getSelectedItem()));
                    time.getSelectionModel().clearSelection();
                    vacType.getSelectionModel().clearSelection();
                    ppsn.getSelectionModel().clearSelection();
                    vaccinator.clear();
                    batchID.clear();
                    break;
                } else {
                    AlertBox.displayAlert("Appointment Update", "Patient eircode does not match vaccination centre eircode. Searching for matching eircodes...");
                    if (vac.getEircode().contains(Driver.getPatient(ppsn.getSelectionModel().getSelectedItem()).getEircode().substring(0, 3))) {
                        vac.getBooths().get(0).addAppointment(date.getValue().toString(), time.getSelectionModel().getSelectedItem(), vacType.getSelectionModel().getSelectedItem(), batchID.getText(), vaccinator.getText(), ppsn.getSelectionModel().getSelectedItem().toString());
                        time.getSelectionModel().clearSelection();
                        vacType.getSelectionModel().clearSelection();
                        ppsn.getSelectionModel().clearSelection();
                        vaccinator.clear();
                        batchID.clear();
                        break;
                    } else {
                        if (Driver.findLowestCentre().getBooths().size() != 0) {
                            Driver.findLowestCentre().getSmallestBooth().addAppointment(date.getValue().toString(), time.getSelectionModel().getSelectedItem(), vacType.getSelectionModel().getSelectedItem(), batchID.getText(), vaccinator.getText(), ppsn.getSelectionModel().getSelectedItem().toString());
                            time.getSelectionModel().clearSelection();
                            vacType.getSelectionModel().clearSelection();
                            ppsn.getSelectionModel().clearSelection();
                            vaccinator.clear();
                            batchID.clear();
                            break;
                        } else {
                            currentCentre.getSmallestBooth().addAppointment(date.getValue().toString(), time.getSelectionModel().getSelectedItem(), vacType.getSelectionModel().getSelectedItem(), batchID.getText(), vaccinator.getText(), ppsn.getSelectionModel().getSelectedItem().toString());
                            if (currentCentre.getSmallestBooth() == currentBooth){
                                list.getItems().add(new Appointment(date.getValue().toString(), time.getSelectionModel().getSelectedItem(), vacType.getSelectionModel().getSelectedItem(), batchID.getText(), vaccinator.getText(), ppsn.getSelectionModel().getSelectedItem()));
                            }
                            time.getSelectionModel().clearSelection();
                            vacType.getSelectionModel().clearSelection();
                            ppsn.getSelectionModel().clearSelection();
                            vaccinator.clear();
                            batchID.clear();
                            break;
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void deleteItem(){ // deletes an item from the respective linked list or list view depending on which pane is visible
        if(vacCentrePane.isVisible()){
            Driver.systemAPI.vacCentres.remove(list.getSelectionModel().getSelectedIndex());
            list.getItems().remove(list.getSelectionModel().getSelectedIndex());
        }

        if(boothPane.isVisible()){
            ConnectedList<Appointment> appointmentList = currentCentre.getBooths().get(list.getSelectionModel().getSelectedIndex()).getAppointments();

            currentCentre.getBooths().remove(list.getSelectionModel().getSelectedIndex());
            list.getItems().remove(list.getSelectionModel().getSelectedIndex());

            if (!currentCentre.getBooths().isEmpty()) {
                for (int i = 0; i < appointmentList.size(); i++) {
                    currentCentre.getBooths().get(i%currentCentre.getBooths().size()).addAppointment(appointmentList.get(i).getDate(), appointmentList.get(i).getTime(), appointmentList.get(i).getVacType(), appointmentList.get(i).getBatchID(), appointmentList.get(i).getVaccinator(), appointmentList.get(i).getPPSN());
                }
            }
        }

        if(appointmentPane.isVisible()){
            currentBooth.getAppointments().remove(list.getSelectionModel().getSelectedIndex());
            list.getItems().remove(list.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    public void returnAbove() throws IOException { // returns to the pane above
        if(appointmentPane.isVisible()){
            titleText.setText("Booths");
            appointmentPane.setVisible(false);
            boothPane.setVisible(true);
            list.getItems().clear();
            if (currentCentre != null) {
                for (int i = 0; i < currentCentre.getBooths().size(); i++) {
                    list.getItems().add(currentCentre.getBooths().get(i));
                }
            }
        }else if(boothPane.isVisible()){
            titleText.setText("Vaccination Centres");
            boothPane.setVisible(false);
            vacCentrePane.setVisible(true);
            list.getItems().clear();
            for(int i = 0; i < Driver.systemAPI.vacCentres.size(); i++){
                list.getItems().add(Driver.systemAPI.vacCentres.get(i));
            }
        } else if(vacCentrePane.isVisible()){
            FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("start-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 480);
            Driver.mainStage.setTitle("Vaccination System");
            Driver.mainStage.setScene(scene);
            Driver.mainStage.show();
        }
    }

    public void completeAppointment() { // removes an appointment from the appointments list and adds it to the vaccination records of the respective patient
        if (!currentBooth.getAppointments().isEmpty()) {
            currentAppointment = currentBooth.getAppointments().get(list.getSelectionModel().getSelectedIndex());
            for (Patient patient : Driver.systemAPI.patients) {
                if (patient.getPPSN().equals(currentAppointment.getPPSN())) {
                    patient.addVaccinationRecord(currentAppointment.getDate(), currentAppointment.getVacType(), currentAppointment.getBatchID(), currentAppointment.getVaccinator());
                    currentBooth.removeAppointment(currentBooth.getAppointments().get(list.getSelectionModel().getSelectedIndex()));
                    list.getItems().remove(currentAppointment);
                    break;
                }
            }
        }
    }
}
