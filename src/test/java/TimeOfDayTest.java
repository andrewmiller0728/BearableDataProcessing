import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeOfDayTest {

    private TimeOfDay timeA, timeB, timeC;

    @BeforeEach
    void setUp() {
        timeA = new TimeOfDay(5, 40);
        timeB = new TimeOfDay(TimeOfDay.TimeBlock.AM);
        timeC = new TimeOfDay(16, 7, TimeOfDay.TimeBlock.PM);
    }

    @Test
    void getHour() {
        assertEquals(5, timeA.getHour());
        assertEquals(-1, timeB.getHour());
        assertEquals(16, timeC.getHour());
    }

    @Test
    void getMinute() {
        assertEquals(40, timeA.getMinute());
        assertEquals(-1, timeB.getMinute());
        assertEquals(7, timeC.getMinute());
    }

    @Test
    void getTimeBlock() {
        assertEquals(TimeOfDay.TimeBlock.PRE, timeA.getTimeBlock());
        assertEquals(TimeOfDay.TimeBlock.AM, timeB.getTimeBlock());
        assertEquals(TimeOfDay.TimeBlock.PM, timeC.getTimeBlock());
    }

    @Test
    void setHour() {
        timeA.setHour(6);
        assertEquals(6, timeA.getHour());
        timeB.setHour(14);
        assertEquals(14, timeB.getHour());
        timeA.setHour(2);
        assertEquals(2, timeA.getHour());
    }

    @Test
    void setMinute() {
        timeA.setMinute(45);
        assertEquals(45, timeA.getMinute());
        timeB.setMinute(30);
        assertEquals(30, timeB.getMinute());
        timeC.setMinute(15);
        assertEquals(15, timeC.getMinute());
    }

    @Test
    void setTimeBlock() {
        timeA.setTimeBlock(TimeOfDay.TimeBlock.ALL_DAY);
        assertEquals(TimeOfDay.TimeBlock.ALL_DAY, timeA.getTimeBlock());
        timeB.setTimeBlock(TimeOfDay.TimeBlock.AM);
        assertEquals(TimeOfDay.TimeBlock.AM, timeB.getTimeBlock());
        timeC.setTimeBlock(TimeOfDay.TimeBlock.MID);
        assertEquals(TimeOfDay.TimeBlock.MID, timeC.getTimeBlock());
    }

    @Test
    void isProper() {
        assertTrue(timeA.isProper());
        assertTrue(timeB.isProper());
        assertFalse(timeC.isProper());
    }

    @Test
    void equals() {
        assertTrue(timeA.equals(new TimeOfDay(5, 40, TimeOfDay.TimeBlock.PRE)));
        assertFalse(timeA.equals(timeB));
    }

}