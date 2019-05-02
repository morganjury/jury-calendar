package com.jury.calendar.entity;

public enum MonthOfYear {

    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March"),
    APRIL("April"),
    MAY("May"),
    JUNE("June"),
    JULY("July"),
    AUGUST("August"),
    SEPTEMBER("September"),
    OCTOBER("October"),
    NOVEMBER("November"),
    DECEMBER("December");

    private final int index;
    private final String displayName;

    MonthOfYear(String displayName) {
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

    public static MonthOfYear get(int index) {
        return values()[index - 1]; // i.e. January is 1st so return values()[0]
    }

    public static MonthOfYear get(String displayName) {
        return valueOf(displayName.toUpperCase());
    }

}
