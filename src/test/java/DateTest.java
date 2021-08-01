import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    private Date dateA, dateB, dateC, dateD;

    @BeforeEach
    void setUp() {
        dateA = new Date(7, 28, 1998);
        dateB = new Date(4, 20, 3021);
        dateC = new Date(4, 80, 2021);
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
}