import database.BearableStringParser;
import database.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    private Date dateA, dateB, dateC;

    @BeforeEach
    void setUp() {
        dateA = new Date(7, 28, 2021);
        dateB = new Date(4, 20, 2021);
        dateC = new Date(4, 30, 2021);
    }

    @Test
    void isProper() {
        assertTrue(dateA.isProper());
        assertFalse(dateB.isProper());
        assertFalse(dateC.isProper());
    }

    @Test
    void testEquals() {
        assertEquals(new Date(7, 28, 1998), dateA);
        assertNotEquals(new Date(4, 20, 2021), dateB);
    }

    @Test
    void testCreateFromBearableString() {
        String[] testStrings = {
                "23rd Sep 2020", // length = 13
                "9th Dec 2020", // length = 12
                "8th May 2021" // length = 12
        };
        for (int i = 0; i < testStrings.length; i++) {
            assertTrue(BearableStringParser.parseDateFromBearableString(testStrings[i]).isProper());
        }
    }

    @Test
    void isMoreRecent() {
        assertTrue(dateA.isMoreRecent(dateB));
        assertTrue(dateC.isMoreRecent(dateB));
    }

}