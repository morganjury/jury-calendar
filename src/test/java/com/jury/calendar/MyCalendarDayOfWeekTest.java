package com.jury.calendar;

import com.jury.calendar.entity.DayOfWeek;
import com.jury.calendar.entity.MonthOfYear;
import com.jury.calendar.entity.StartOfWeek;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class MyCalendarDayOfWeekTest extends MainTest {

    private MyCalendar myCalendar;

    @Test
    @FileParameters(testResources + "MyCalendarDayOfWeekTest/daysInMonth.csv")
    public void daysInMonth(MonthOfYear month, int year, int expected) {
        assertEquals(expected, MyCalendar.daysInMonth(month, year));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayOfWeekTest/daysInYear.csv")
    public void daysInYear(int year, int expected) {
        assertEquals(expected, MyCalendar.daysInYear(year));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayOfWeekTest/firstWeekStartOfMonth.csv")
    public void firstWeekStartOfMonth(DayOfWeek startOfWeek, MonthOfYear month, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek));
        assertEquals(expected, myCalendar.getFirstWeekStartOfMonth(startOfWeek, month, year));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayOfWeekTest/firstWeekStartOfYear.csv")
    public void firstWeekStartOfYear(DayOfWeek startOfWeek, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek));
        assertEquals(expected, myCalendar.getFirstWeekStartOfYear(year));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayOfWeekTest/weeksInMonth.csv")
    public void weeksInMonth(DayOfWeek startOfWeek, MonthOfYear month, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek));
        assertEquals(expected, myCalendar.weeksInMonth(month, year));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayOfWeekTest/weeksInYear.csv")
    public void weeksInYear(DayOfWeek startOfWeek, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek));
        assertEquals(expected, myCalendar.weeksInYear(year));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayOfWeekTest/financialWeek.csv")
    public void financialWeek(DayOfWeek startOfWeek, int day, int month, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek));
        assertEquals(expected, myCalendar.getFinancialWeek(LocalDate.of(year, month, day)));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayOfWeekTest/financialMonth.csv")
    public void financialMonth(DayOfWeek startOfWeek, int day, int month, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek));
        assertEquals(expected, myCalendar.getFinancialMonth(LocalDate.of(year, month, day)));
    }

    @Test
    @FileParameters(testResources + "MyCalendarDayOfWeekTest/financialYear.csv")
    public void financialYear(DayOfWeek startOfWeek, int day, int month, int year, int expected) {
        myCalendar = new MyCalendar(new StartOfWeek(startOfWeek));
        assertEquals(expected, myCalendar.getFinancialYear(LocalDate.of(year, month, day)));
    }

}
