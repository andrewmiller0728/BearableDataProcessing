import database.BearableStringParser;
import database.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeTest {

    private Time timeA, timeB, timeC;

    @BeforeEach
    void setUp() {
        timeA = new Time(5, 40);
        timeB = new Time(Time.TimeBlock.AM);
        timeC = new Time(16, 7, Time.TimeBlock.PM);
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
        assertEquals(Time.TimeBlock.PRE, timeA.getTimeBlock());
        assertEquals(Time.TimeBlock.AM, timeB.getTimeBlock());
        assertEquals(Time.TimeBlock.PM, timeC.getTimeBlock());
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
        timeA.setTimeBlock(Time.TimeBlock.ALL_DAY);
        assertEquals(Time.TimeBlock.ALL_DAY, timeA.getTimeBlock());
        timeB.setTimeBlock(Time.TimeBlock.AM);
        assertEquals(Time.TimeBlock.AM, timeB.getTimeBlock());
        timeC.setTimeBlock(Time.TimeBlock.MID);
        assertEquals(Time.TimeBlock.MID, timeC.getTimeBlock());
    }

    @Test
    void isProper() {
        assertTrue(timeA.isProper());
        assertTrue(timeB.isProper());
        assertFalse(timeC.isProper());
    }

    @Test
    void equals() {
        assertTrue(timeA.equals(new Time(5, 40, Time.TimeBlock.PRE)));
        assertFalse(timeA.equals(timeB));
    }

    @Test
    void createTimeOfDayFromBearableString() {
        String[] testStrings = {"03:00", "15:33", "pre", "all day"};
        for (String testString : testStrings) {
            assertTrue(BearableStringParser.parseTimeOfDayFromBearableString(testString).isProper());
        }
    }
}