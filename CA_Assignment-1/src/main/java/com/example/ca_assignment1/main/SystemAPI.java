package com.example.ca_assignment1.main;

import com.example.ca_assignment1.models.Patient;
import com.example.ca_assignment1.models.VaccinationCentre;
import com.example.ca_assignment1.utils.ConnectedList;

public class SystemAPI {
    ConnectedList<VaccinationCentre> vacCentres;
    ConnectedList<Patient> patients;

    public SystemAPI() {
        vacCentres = new ConnectedList<>();
        patients = new ConnectedList<>();
    }
}
