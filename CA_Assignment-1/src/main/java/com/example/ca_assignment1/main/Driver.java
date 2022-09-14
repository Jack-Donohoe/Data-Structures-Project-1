package com.example.ca_assignment1.main;

import com.example.ca_assignment1.models.Booth;
import com.example.ca_assignment1.models.Patient;
import com.example.ca_assignment1.models.VaccinationCentre;
import com.example.ca_assignment1.utils.ConnectedList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Driver extends Application {
    public static Stage mainStage;
    public static SystemAPI systemAPI;

    public static void addVacCentre(String name, String address, String eircode, int parking) {
        VaccinationCentre vac = new VaccinationCentre(name, address, eircode, parking);
        systemAPI.vacCentres.add(vac);
    }

    public void removeVacCentre(String name) {
        for (int i = 0; i < systemAPI.vacCentres.size(); i++) {
            if (name.equals(systemAPI.vacCentres.get(i).getName())) {
                systemAPI.vacCentres.remove(i);
            }
        }
    }

    public void setVacCentre(int index, String name, String address, String eircode, int parking) {
        VaccinationCentre newVac = new VaccinationCentre(name, address, eircode, parking);

        if (index < systemAPI.vacCentres.size()) {
            systemAPI.vacCentres.set(index, newVac);
        }
    }

    public String getVacCentreDetails(int index) {
        if (systemAPI.vacCentres.get(index) != null) {
            return systemAPI.vacCentres.get(index).toString();
        } else {
            return "Index is not valid";
        }
    }

    public String listVacCentres() {
        String vacList = null;
        for (int i = 0; i < systemAPI.vacCentres.size(); i++) {
            vacList = vacList + systemAPI.vacCentres.get(i);
        }
        return vacList;
    }

    public static Patient getPatient(String ppsn){
        Patient foundPatient = null;
        for(Patient patient: systemAPI.patients){
            if(patient.getPPSN().equals(ppsn)){
                foundPatient = patient;
            }
        }
        return foundPatient;
    }

    public static void addPatient(String PPSN, String name, String dateOfBirth, String address, String eircode, String phone, String email) {
        Patient patient = new Patient(PPSN, name, dateOfBirth, address, eircode, phone, email);
        systemAPI.patients.add(patient);
    }

    public static VaccinationCentre findLowestCentre(){
        if (!systemAPI.vacCentres.isEmpty()) {
            VaccinationCentre smallestCentre = systemAPI.vacCentres.get(0);
            for (VaccinationCentre vac : systemAPI.vacCentres) {
                if (vac.getNoOfAppointments() < smallestCentre.getNoOfAppointments()) {
                    smallestCentre = vac;
                }
            }
            return smallestCentre;
        } else {
            return null;
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        systemAPI = new SystemAPI();
        for (int i = 0; i <= 5; i++) {
            addVacCentre("Centre " + i, "Hi", "X91 D9FK", 8);
            systemAPI.vacCentres.get(0).addBooth(i + "A", "1", "Wheelchair");
            systemAPI.vacCentres.get(0).getBooths().get(0).addAppointment("2021-11-01", "12:00", "Pfizer", "1234DD", "Jack", i + "222222DD");
            addPatient(i + "222222DD", "Patient " + i, "2001-01-01", "Callan","X91 D9FK", "12345678", "Hi");
        }

        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Vaccine System - Welcome");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void save() throws IOException {
        try {
            XStream xstream = new XStream(new DomDriver());
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("System.xml"));
            out.writeObject(systemAPI);
            out.close();
        } catch (RuntimeException runtimeException){
            System.out.println(runtimeException.getMessage());
        }
    }

    public static void load() throws IOException, ClassNotFoundException {
        try {
            XStream xstream = new XStream(new DomDriver());
            xstream.addPermission(AnyTypePermission.ANY);
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("System.xml"));
            systemAPI = (SystemAPI) is.readObject();
            is.close();
        } catch (RuntimeException runtimeException){
            System.out.println(runtimeException.getMessage());
        }
    }
}