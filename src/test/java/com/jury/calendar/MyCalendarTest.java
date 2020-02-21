package com.jury.calendar;

import com.jury.calendar.entity.*;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class MyCalendarTest extends MainTest {

	private MyCalendar calendar = new MyCalendar(new StartOfWeek(DayOfWeek.MONDAY), new StartOfMonth(DayOfWeek.MONDAY), new StartOfYear(DayOfWeek.MONDAY, MonthOfYear.APRIL));

	@Test
	@FileParameters(testResources + "MyCalendarTest/customYearStart.csv")
	public void customYearStart(int year, int month, int day, int categorisedWeek, int categorisedMonth, int categorisedYear) {
		LocalDate date = LocalDate.of(year, month, day);
		assertEquals(categorisedWeek, calendar.getCategorisedWeek(date));
		assertEquals(categorisedMonth, calendar.getCategorisedMonth(date));
		assertEquals(categorisedYear, calendar.getCategorisedYear(date));
	}

}
