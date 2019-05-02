package com.jury.calendar.entity;

public class StartOfYear {

    private DayOfWeek dayOfWeek;
    private Integer dayInMonth;
    private MonthOfYear monthOfYear;

    public StartOfYear(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public StartOfYear(int dayInMonth, MonthOfYear monthOfYear) {
        this.dayInMonth = dayInMonth;
        this.monthOfYear = monthOfYear;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDayInMonth() {
        return dayInMonth;
    }

    public MonthOfYear getMonthOfYear() {
        return monthOfYear;
    }

    public boolean isADay() {
        return dayOfWeek != null;
    }

    public boolean isADate() {
        return dayOfWeek == null;
    }

}
