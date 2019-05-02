package com.jury.calendar.entity;

public enum DayOfWeek {

    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final int index;
    private final String displayName;

    DayOfWeek(String displayName) {
        this.index = ordinal() + 1;
        this.displayName = displayName;
    }

    public int getIndex() {
        return index;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    public static DayOfWeek get(int index) {
        return values()[index - 1]; // i.e. Monday is 1st so return values()[0]
    }

    public static DayOfWeek get(String displayName) {
        return valueOf(displayName.toUpperCase());
    }

}
