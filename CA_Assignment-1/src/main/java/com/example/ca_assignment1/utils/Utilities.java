package com.example.ca_assignment1.utils;

import java.util.ArrayList;

public class Utilities {

    private static String numbersOnly = "[0-9]+";

    public static boolean validPPS(String pps){
        return (pps.matches("[0-9]{7}[a-zA-Z]{2}"));
    }

    public static boolean max30Chars(String string){
        if (string != null) {
            if(string.length() > 30) {
                return false;
            }
        }
        return true;
    }

    public static boolean max10Chars(String string){
        if (string != null) {
            if(string.length() > 10) {
                return false;
            }
        }
        return true;
    }

    public static boolean validIntRange(int number1, int number2, int number3){
        if (number3 >= number1 && number3 <= number2){
            return true;
        } else {
            return false;
        }
    }

    public static boolean onlyContainsNumbers(String text) {
        if (text != null) {
            return (text.matches(numbersOnly));
        }
        return false;
    }
}