package com.jury.calendar.entity;

public class StartOfMonth {

    private DayOfWeek dayOfWeek;
    private Integer dayInMonth;

    public StartOfMonth(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public StartOfMonth(int dayInMonth) {
        this.dayInMonth = dayInMonth;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDayInMonth() {
        return dayInMonth;
    }

    public boolean isADay() {
        return dayOfWeek != null;
    }

    public boolean isADate() {
        return dayOfWeek == null;
    }

}
