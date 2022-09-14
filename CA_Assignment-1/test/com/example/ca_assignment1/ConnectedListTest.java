package com.example.ca_assignment1;

import com.example.ca_assignment1.utils.ConnectedList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectedListTest {

    public ConnectedList<String> list = new ConnectedList<>();
    @BeforeEach
    void setUp() {
        for(int i = 0; i < 5; i++) {
            list.add("Hi " + i);
        }
    }

    @AfterEach
    void tearDown() {
        list = null;
    }

    @Test
    void add() {
        assertEquals(5, list.size());
        list.add("Yeah");
        assertEquals(6, list.size());
    }

    @Test
    void removeByIndex() {
        assertEquals(5, list.size());
        list.remove(0);
        assertEquals(4, list.size());
    }

    @Test
    void removeByObject() {
        assertEquals(5, list.size());
        list.remove(list.get(0));
        assertEquals(4, list.size());
    }

    @Test
    void clear() {
        assertEquals(5, list.size());
        list.clear();
        assertEquals(0,list.size());
        assertFalse(list.contains("Hi 1"));
    }

    @Test
    void get() {
        assertEquals("Hi 3", list.get(3));
    }

    @Test
    void set() {
        assertEquals("Hi 3", list.get(3));
        list.set(3,"Yo");
        assertEquals("Yo",list.get(3));
    }

    @Test
    void size() {
        assertEquals(5, list.size());
        list.add("Yeah");
        list.add("Hello");
        list.add("Greetings");
        assertEquals(8, list.size());
    }

    @Test
    void isEmpty() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void contains() {
        assertTrue(list.contains("Hi 1"));
        list.add("Yeah");
        assertTrue(list.contains("Yeah"));
    }
}