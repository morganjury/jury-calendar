package com.jury.calendar.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DayOfWeekTest {

    @Test
    public void getByIndex() {
        assertEquals(DayOfWeek.MONDAY, DayOfWeek.get(1));
        assertEquals(DayOfWeek.TUESDAY, DayOfWeek.get(2));
        assertEquals(DayOfWeek.WEDNESDAY, DayOfWeek.get(3));
        assertEquals(DayOfWeek.THURSDAY, DayOfWeek.get(4));
        assertEquals(DayOfWeek.FRIDAY, DayOfWeek.get(5));
        assertEquals(DayOfWeek.SATURDAY, DayOfWeek.get(6));
        assertEquals(DayOfWeek.SUNDAY, DayOfWeek.get(7));
    }

    @Test
    public void getByName() {
        assertEquals(DayOfWeek.MONDAY, DayOfWeek.get("MONDAY"));
        assertEquals(DayOfWeek.TUESDAY, DayOfWeek.get("TUESDAY"));
        assertEquals(DayOfWeek.WEDNESDAY, DayOfWeek.get("WEDNESDAY"));
        assertEquals(DayOfWeek.THURSDAY, DayOfWeek.get("THURSDAY"));
        assertEquals(DayOfWeek.FRIDAY, DayOfWeek.get("FRIDAY"));
        assertEquals(DayOfWeek.SATURDAY, DayOfWeek.get("SATURDAY"));
        assertEquals(DayOfWeek.SUNDAY, DayOfWeek.get("SUNDAY"));

        assertEquals(DayOfWeek.MONDAY, DayOfWeek.get("monday"));
        assertEquals(DayOfWeek.TUESDAY, DayOfWeek.get("tuesday"));
        assertEquals(DayOfWeek.WEDNESDAY, DayOfWeek.get("wednesday"));
        assertEquals(DayOfWeek.THURSDAY, DayOfWeek.get("thursday"));
        assertEquals(DayOfWeek.FRIDAY, DayOfWeek.get("friday"));
        assertEquals(DayOfWeek.SATURDAY, DayOfWeek.get("saturday"));
        assertEquals(DayOfWeek.SUNDAY, DayOfWeek.get("sunday"));
    }

}
