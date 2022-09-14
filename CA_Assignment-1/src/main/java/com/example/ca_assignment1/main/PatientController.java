package com.example.ca_assignment1.main;

import com.example.ca_assignment1.models.Patient;
import com.example.ca_assignment1.utils.AlertBox;
import com.example.ca_assignment1.utils.Utilities;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PatientController {
    @FXML
    public Label titleText,patientName;
    @FXML
    public TextField ppsn,name,address,eircode,phone,email;
    @FXML
    public DatePicker dateOfBirth;
    @FXML
    public ListView<Object> list;
    @FXML
    public Patient currentPatient;
    @FXML
    public AnchorPane patientPane;

    @FXML
    public void initialize(){ // adds the items to the list view when loading into the view
        list.getItems().clear();
        for(Patient patient: Driver.systemAPI.patients){
            list.getItems().add(patient);
        }
    }

    @FXML
    public void setView(MouseEvent e){
        if (e.getClickCount() == 2) {
            if (!Driver.systemAPI.patients.isEmpty() && patientPane.isVisible()) {
                if (list.getSelectionModel().getSelectedIndex() >= 0) {
                    titleText.setText("Vaccination Records");
                    patientPane.setVisible(false);
                    currentPatient = Driver.systemAPI.patients.get(list.getSelectionModel().getSelectedIndex());
                    list.getItems().clear();
                    patientName.setText("Name: " + currentPatient.getName() + " Address: " + currentPatient.getAddress() + " PPSN: " + currentPatient.getPPSN());
                    patientName.setVisible(true);
                    if (currentPatient != null) {
                        for (int i = 0; i < currentPatient.getRecords().size(); i++) {
                            list.getItems().add(currentPatient.getRecords().get(i));
                        }
                    }
                }
            }
        }
    }

    @FXML
    public void addItem(){
        if(patientPane.isVisible()) {
            try {
                if (Utilities.validPPS(ppsn.getText())) {
                    Driver.addPatient(ppsn.getText(), name.getText(), dateOfBirth.getValue().toString(), address.getText(), eircode.getText(), phone.getText(), email.getText());
                    list.getItems().add(new Patient(ppsn.getText(), name.getText(), dateOfBirth.getValue().toString(), address.getText(), eircode.getText(), phone.getText(), email.getText()));
                    ppsn.clear();
                    name.clear();
                    address.clear();
                    eircode.clear();
                    phone.clear();
                    email.clear();
                } else {
                    AlertBox.displayAlert("Invalid PPSN", "PPSN is not valid. Please enter a valid PPSN (7 numbers followed by 2 letters)");
                }
            } catch (RuntimeException runtimeException) {
                AlertBox.displayAlert("Error",runtimeException.getMessage());
            }
        }
    }

    @FXML
    public void deleteItem() {
        if (patientPane.isVisible()) {
            Driver.systemAPI.patients.remove(list.getSelectionModel().getSelectedIndex());
            list.getItems().remove(list.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    public void returnAbove() throws IOException {
        if(!patientPane.isVisible()){
            titleText.setText("Patients");
            patientPane.setVisible(true);
            list.getItems().clear();
            for(int i = 0; i < Driver.systemAPI.patients.size(); i++){
                list.getItems().add(Driver.systemAPI.patients.get(i));
            }
            patientName.setVisible(false);
        } else if(patientPane.isVisible()){
            FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("start-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 480);
            Driver.mainStage.setTitle("Vaccination System");
            Driver.mainStage.setScene(scene);
            Driver.mainStage.show();
        }
    }
}
