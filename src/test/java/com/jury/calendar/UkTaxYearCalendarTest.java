package com.jury.calendar;

import com.jury.calendar.entity.DayOfWeek;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class UkTaxYearCalendarTest extends MainTest {

    private LocalDate start2017;
    private LocalDate start2018;
    private LocalDate start2019;
    private LocalDate start2020;

    private UkTaxYearCalendar ukTaxYearCalendar;

    @Before
    public void setup() {
        start2017 = LocalDate.of(2017,4,6);
        start2018 = LocalDate.of(2018,4,6);
        start2019 = LocalDate.of(2019,4,6);
        start2020 = LocalDate.of(2020,4,6);
    }

    @Test
    public void taxYear2017() {
        ukTaxYearCalendar = new UkTaxYearCalendar(DayOfWeek.MONDAY);
        while (start2017.compareTo(start2018) != 0) {
            assertEquals(start2017.toString(), 2017, ukTaxYearCalendar.getCategorisedYear(start2017));
            start2017 = start2017.plusDays(1);
        }
    }

    @Test
    public void taxYear2018() {
        ukTaxYearCalendar = new UkTaxYearCalendar(DayOfWeek.MONDAY);
        while (start2018.compareTo(start2019) != 0) {
            assertEquals(start2018.toString(), 2018, ukTaxYearCalendar.getCategorisedYear(start2018));
            start2018 = start2018.plusDays(1);
        }
    }

    @Test
    public void taxYear2019() {
        ukTaxYearCalendar = new UkTaxYearCalendar(DayOfWeek.MONDAY);
        while (start2019.compareTo(start2020) != 0) {
            assertEquals(start2019.toString(), 2019, ukTaxYearCalendar.getCategorisedYear(start2019));
            start2019 = start2019.plusDays(1);
        }
    }

    @Test
    public void taxYear2017WithMonthStartDate() {
        ukTaxYearCalendar = new UkTaxYearCalendar(DayOfWeek.MONDAY, 1);
        while (start2017.compareTo(start2018) != 0) {
            assertEquals(start2017.toString(), 2017, ukTaxYearCalendar.getCategorisedYear(start2017));
            start2017 = start2017.plusDays(1);
        }
    }

    @Test
    public void taxYear2018WithMonthStartDate() {
        ukTaxYearCalendar = new UkTaxYearCalendar(DayOfWeek.MONDAY, 1);
        while (start2018.compareTo(start2019) != 0) {
            assertEquals(start2018.toString(), 2018, ukTaxYearCalendar.getCategorisedYear(start2018));
            start2018 = start2018.plusDays(1);
        }
    }

    @Test
    public void taxYear2020WithMonthStartDate() {
        ukTaxYearCalendar = new UkTaxYearCalendar(DayOfWeek.MONDAY, 1);
        while (start2019.compareTo(start2020) != 0) {
            assertEquals(start2019.toString(), 2019, ukTaxYearCalendar.getCategorisedYear(start2019));
            start2019 = start2019.plusDays(1);
        }
    }

}
