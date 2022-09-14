package com.example.ca_assignment1;

import com.example.ca_assignment1.models.VaccinationCentre;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VaccinationCentreTest {
    VaccinationCentre vaccinationCentre = new VaccinationCentre(null,null,null,0);

    @BeforeEach
    void setUp() {
        vaccinationCentre.addBooth("A1","2","Wheelchair access");
    }

    @AfterEach
    void tearDown() {
        vaccinationCentre = null;
    }

    @Test
    void addBooth() {
        assertEquals(1,vaccinationCentre.getBooths().size());
        vaccinationCentre.addBooth("A2","2","None");
        assertEquals(2,vaccinationCentre.getBooths().size());
    }

    @Test
    void removeBooth() {
        assertEquals(1,vaccinationCentre.getBooths().size());
        vaccinationCentre.removeBooth("A1");
        assertEquals(0,vaccinationCentre.getBooths().size());
    }

    @Test
    void setBooth() {
        assertEquals("A1",vaccinationCentre.getBooths().get(0).getBoothID());
        vaccinationCentre.setBooth(0,"A3","2","None");
        assertEquals("A3",vaccinationCentre.getBooths().get(0).getBoothID());
    }

    @Test
    void getBoothDetails() {
        assertTrue(vaccinationCentre.getBoothDetails(0).contains("A1"));
        vaccinationCentre.setBooth(0,"A3","2","None");
        assertTrue(vaccinationCentre.getBoothDetails(0).contains("A3"));
    }
}