package com.jury.calendar;

import com.jury.calendar.entity.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyCalendar {

    // financial week should be determined by day of week
    //  e.g. MONDAY
    // financial month should be determined by first day of week in month or first day in month of month
    //  e.g. MONDAY
    //  e.g. 1st
    // financial year should be determined by first day of week in year or first day of month and month in year
    //  e.g. MONDAY
    //  e.g. 6th APRIL

    private StartOfWeek startOfWeek;
    private StartOfMonth startOfMonth;
    private StartOfYear startOfYear;

    public MyCalendar(StartOfWeek startOfWeek) {
        this(startOfWeek, new StartOfMonth(startOfWeek.getDayOfWeek()), new StartOfYear(startOfWeek.getDayOfWeek()));
    }

    // TODO is there a way of taking startofweek and startofmonth?
    //  would startofyear take startofweek or startofmonth value?


    public MyCalendar(StartOfWeek startOfWeek, StartOfMonth startOfMonth, StartOfYear startOfYear) {
        this.startOfWeek = startOfWeek;
        this.startOfMonth = startOfMonth;
        this.startOfYear = startOfYear;
    }

    public int getFinancialWeek(LocalDate date) {
        LocalDate firstWeekStartOfYear = LocalDate.of(date.getYear(), MonthOfYear.JANUARY.getIndex(), getFirstWeekStartOfYear(date.getYear()));
        if (date.isBefore(firstWeekStartOfYear)) {
            // bug where 2019-01-01 would have week 0 because first Monday (startOfWeek) of 2019 was the 7th
            LocalDate lastWeek = date.minusDays(7);
            return getFinancialWeek(lastWeek) + 1;
        }
        // get to start of week to make calculation easier at next step
        int dayOfWeek = date.getDayOfWeek().getValue();
        int daysAfterStartOfWeek = dayOfWeek >= startOfWeek.getDayOfWeek().getIndex()
                ? dayOfWeek - startOfWeek.getDayOfWeek().getIndex()
                : 7 - (startOfWeek.getDayOfWeek().getIndex() - dayOfWeek);
        LocalDate weekStart = date.minusDays(daysAfterStartOfWeek);
        // number of days since start of year / number of days per week = number of weeks since start of year
        int firstWeekStartDayOfYear = firstWeekStartOfYear.getDayOfYear();
        int weekStartDayOfYear = weekStart.getDayOfYear();
        return ((weekStartDayOfYear-firstWeekStartDayOfYear)/7) + 1;
    }

    public int getFinancialMonth(LocalDate date) {
        // TODO this will need tweaking if startOfMonth is a date
        int startOfMonth = getFirstWeekStartOfMonth(MonthOfYear.get(date.getMonthValue()), date.getYear());
        return (startOfMonth <= date.getDayOfMonth())
                ? date.getMonthValue()
                : ((date.getMonthValue() > 1)
                    ? date.getMonthValue() - 1
                    : 12);
    }

    public int getFinancialYear(LocalDate date) {
        // TODO this will need tweaking if startOfYear is a date
        return (getFinancialMonth(date) == 12 && date.getMonthValue() == 1)
                ? date.getYear() - 1
                : date.getYear();
    }

    // this is where things might be able to be removed

    int getFirstWeekStartOfMonth(MonthOfYear monthOfYear, int year) {
        // financial weeks are independent of financial months so we need only consider startOfWeek
        Calendar cacheCalendar = Calendar.getInstance();
        int javaUtilDayOfWeek = startOfWeek.getDayOfWeek()== DayOfWeek.SUNDAY ? 1 : startOfWeek.getDayOfWeek().getIndex() + 1;
        cacheCalendar.set(Calendar.DAY_OF_WEEK, javaUtilDayOfWeek);
        cacheCalendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        cacheCalendar.set(Calendar.MONTH, monthOfYear.getIndex() - 1);
        cacheCalendar.set(Calendar.YEAR, year);
        return cacheCalendar.get(Calendar.DATE);
    }

    int getFirstWeekStartOfYear(int year) {
        return getFirstWeekStartOfMonth(MonthOfYear.JANUARY, year);
    }

    int weeksInMonth(MonthOfYear month, int year) {
        int firstWeekStart = getFirstWeekStartOfMonth(month, year);
        int numDays = daysInMonth(month, year);
        return ((numDays - firstWeekStart) / 7) + 1;
    }

    int weeksInYear(int year) {
        int sum = 0;
        for (MonthOfYear month : MonthOfYear.values()) {
            sum += weeksInMonth(month, year);
        }
        return sum;
    }

    static int daysInMonth(MonthOfYear monthOfYear, int year) {
        Calendar myCal = new GregorianCalendar(year, monthOfYear.getIndex() - 1, 1);
        return myCal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    static int daysInYear(int year) {
        Calendar myCal = new GregorianCalendar(year, MonthOfYear.JANUARY.getIndex() - 1, 1);
        return myCal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

}
