package com.jury.calendar;

import com.jury.calendar.entity.*;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class MyCalendarDayInMonthTest extends MainTest {

    private MyCalendar myCalendar;

    @Test
    @FileParameters(testResources + "MyCalendarDayInMonthTest/firstWeekStartOfMonth.csv")
    public void firstWeekStartOfMonth(DayOfWeek startOfWeek, int startOfMonth, MonthOfYear startOfYear, MonthOfYear month, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek), new StartOfMonth(startOfMonth), new StartOfYear(startOfMonth, startOfYear));
        assertEquals(expected, myCalendar.getFirstWeekStartOfMonth(startOfWeek, month, year));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayInMonthTest/financialMonth.csv")
    public void financialMonth(DayOfWeek startOfWeek, int startOfMonth, int day, int month, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek), new StartOfMonth(startOfMonth), new StartOfYear(startOfMonth, MonthOfYear.JANUARY));
        assertEquals(expected, myCalendar.getFinancialMonth(LocalDate.of(year, month, day)));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayInMonthTest/financialYearForDayInMonth.csv")
    public void financialYearStartingOnDayInMonth(DayOfWeek startOfWeek, int startOfMonth, MonthOfYear startOfYear, int day, int month, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek), new StartOfMonth(startOfMonth), new StartOfYear(startOfMonth, startOfYear));
        assertEquals(expected, myCalendar.getFinancialYear(LocalDate.of(year, month, day)));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayInMonthTest/financialYearForDayOfWeek.csv")
    public void financialYearStartingOnDayOfWeek(DayOfWeek startOfWeek, int startOfMonth, MonthOfYear startOfYear, int day, int month, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek), new StartOfMonth(startOfMonth), new StartOfYear(startOfWeek, startOfYear));
        assertEquals(expected, myCalendar.getFinancialYear(LocalDate.of(year, month, day)));
    }

}
