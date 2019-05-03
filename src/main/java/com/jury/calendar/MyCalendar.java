package com.jury.calendar;

import com.jury.calendar.entity.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyCalendar {

    // financial week should be determined by
    //  e.g. MONDAY
    // financial month should be determined by
    //  e.g. MONDAY
    //  e.g. 1st
    // financial year should be determined by
    //  e.g. MONDAY
    //  e.g. 6th APRIL // UkTaxYearCalendar extends MyCalendar
    //  e.g. MONDAY APRIL

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
        if (startOfMonth.isADay()) {
            int startOfMonth = getFirstWeekStartOfMonth(MonthOfYear.get(date.getMonthValue()), date.getYear());
            return (startOfMonth <= date.getDayOfMonth())
                    ? date.getMonthValue()
                    : ((date.getMonthValue() > 1)
                    ? date.getMonthValue() - 1
                    : 12);
        }
        LocalDate monthStartDate = LocalDate.of(date.getYear(), date.getMonthValue(), startOfMonth.getDayInMonth());
        if (date.compareTo(monthStartDate) == 0 || date.isAfter(monthStartDate)) {
            return date.getMonthValue();
        } else {
            return ((date.getMonthValue() > 1)
                    ? date.getMonthValue() - 1
                    : 12);
        }
    }

    public int getFinancialYear(LocalDate date) {
        if (startOfYear.getDayOfWeek() != null && startOfYear.getMonthOfYear() == null) {
            return (getFinancialMonth(date) == 12 && date.getMonthValue() == 1)
                    ? date.getYear() - 1
                    : date.getYear();
        }
        LocalDate yearStartDate;
        if (startOfYear.getDayInMonth() != null) {
            yearStartDate = LocalDate.of(date.getYear(), startOfYear.getMonthOfYear().getIndex(), startOfYear.getDayInMonth());
        } else {
            int dayInMonthForYear = getFirstWeekStartOfMonth(startOfYear.getDayOfWeek(), startOfYear.getMonthOfYear(), date.getYear());
            yearStartDate = LocalDate.of(date.getYear(), startOfYear.getMonthOfYear().getIndex(), dayInMonthForYear);
        }
        if (date.isBefore(yearStartDate) && date.isAfter(yearStartDate.minusYears(1))) {
            return date.getYear() - 1;
        } else {
            return date.getYear();
        }
    }

    // this is where things might be able to be removed

    private int getFirstWeekStartOfMonth(MonthOfYear monthOfYear, int year) {
        return getFirstWeekStartOfMonth(startOfWeek.getDayOfWeek(), monthOfYear, year);
    }

    int getFirstWeekStartOfMonth(DayOfWeek dayOfWeek, MonthOfYear monthOfYear, int year) {
        // financial weeks are independent of financial months so we need only consider startOfWeek
        Calendar cacheCalendar = Calendar.getInstance();
        cacheCalendar.set(Calendar.DAY_OF_WEEK, dayOfWeek.asJavaUtilDayOfWeek());
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
