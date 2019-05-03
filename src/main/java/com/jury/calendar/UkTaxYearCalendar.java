package com.jury.calendar;

import com.jury.calendar.entity.*;

public class UkTaxYearCalendar extends MyCalendar {

    private static final StartOfYear startOfUkTaxYear = new StartOfYear(6, MonthOfYear.APRIL);

    public UkTaxYearCalendar(DayOfWeek startOfWeek) {
        super(new StartOfWeek(startOfWeek), new StartOfMonth(6), startOfUkTaxYear);
    }

    public UkTaxYearCalendar(DayOfWeek startOfWeek, DayOfWeek startOfMonth) {
        super(new StartOfWeek(startOfWeek), new StartOfMonth(startOfMonth), startOfUkTaxYear);
    }

    public UkTaxYearCalendar(DayOfWeek startOfWeek, int startOfMonth) {
        super(new StartOfWeek(startOfWeek), new StartOfMonth(startOfMonth), startOfUkTaxYear);
    }

}
