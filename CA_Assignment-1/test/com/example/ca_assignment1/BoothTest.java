package com.example.ca_assignment1;

import com.example.ca_assignment1.models.Booth;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoothTest {
    Booth booth = new Booth(null,null,null);

    @BeforeEach
    void setUp() {
        booth.addAppointment("October 8th 2021","12:00","mRNA","1402","John Markovitz","4417388");
    }

    @AfterEach
    void tearDown() {
        booth = null;
    }

    @Test
    void addAppointment() {
        assertEquals(1,booth.getAppointments().size());
        booth.addAppointment("01/01/2022","1:00","mRNA","1803","James Hanby","323277");
        assertEquals(2,booth.getAppointments().size());
    }

    @Test
    void removeAppointment() {
        assertEquals(1,booth.getAppointments().size());
        booth.removeAppointment(booth.getAppointments().get(0));
        assertEquals(0,booth.getAppointments().size());
    }

    @Test
    void setAppointment() {
        assertEquals("October 8th 2021",booth.getAppointments().get(0).getDate());
        booth.setAppointment(0,"October 9th 2021","12:00","mRNA","1402","John Markovitz","441738");
        assertEquals("October 9th 2021",booth.getAppointments().get(0).getDate());
    }

    @Test
    void getAppointmentDetails() {
        assertTrue(booth.getAppointmentDetails(0).contains("October 8th 2021"));
        booth.setAppointment(0,"October 10th 2021","12:00","mRNA","1402","John Markovitz","441738");
        assertTrue(booth.getAppointmentDetails(0).contains("October 10th 2021"));
    }
}