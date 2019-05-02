package com.jury.calendar.validation;

import java.time.LocalDate;

public class DateValidator {

    private String errorMessage = "Nothing wrong here!";

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean validate(LocalDate date) {
        return validate(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }

    public boolean validate(int day, int month, int year) {
        return validDay(day, month, year) && validMonth(month) && validYear(year);
    }

    private boolean validDay(int day, int month, int year) {
        if (day <= 0 || day > 31) {
            errorMessage = "Day cannot be less than 0 or greater than 31";
            return false;
        }
        if (month == 2) {
            if (isLeapYear(year) ? day <= 29 : day <= 28) {
                return true;
            }
        } else {
            int[] arr = {31,0,31,30,31,30,31,31,30,31,30,31};
            if (month <= arr.length && day <= arr[month - 1]) {
                return true;
            }
        }
        errorMessage = "Day of month was outside possible range of this month";
        return false;
    }

    private boolean validMonth(int month) {
        if (month > 0 && month <= 12) {
            return true;
        }
        errorMessage = "Month cannot be less than 0 or larger than 12";
        return false;
    }

    private boolean validYear(int year) {
        if (year >= 1900 && year <= 3000) {
            return true;
        }
        errorMessage = "Year cannot be less than 1900 or larger than 3000";
        return false;
    }

    private boolean isLeapYear(int year) {
        return (year % 100 == 0) ? (year % 400 == 0) : (year % 4 == 0);
    }

}
