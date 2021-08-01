import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {

    private Record recordA, recordB, recordC;

    @BeforeEach
    void setUp() {
        recordA = new Record(
                new Date(7, 28, 1998),
                DayOfWeek.TUESDAY,
                new TimeOfDay(6, 15),
                DataCategory.MOOD,
                10,
                new String[]{"My Birthday"}
        );
        recordB = new Record(
                new Date(7, 28, 1998),
                DayOfWeek.TUESDAY,
                new TimeOfDay(6, 15),
                DataCategory.MOOD,
                10,
                new String[]{"My Birthday"}
        );
        recordC = new Record(
                new Date(1, 10, 1999),
                DayOfWeek.WEDNESDAY,
                new TimeOfDay(12, 0),
                DataCategory.MOOD,
                10,
                new String[]{"Charlotte's Birthday"}
        );
    }

    @Test
    void testEquals() {
        assertTrue(recordA.equals(recordB));
        assertFalse(recordA.equals(recordC));
    }

}