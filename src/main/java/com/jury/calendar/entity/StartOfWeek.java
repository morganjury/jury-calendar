package com.jury.calendar.entity;

public class StartOfWeek {

    private final DayOfWeek dayOfWeek;

    public StartOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

}
