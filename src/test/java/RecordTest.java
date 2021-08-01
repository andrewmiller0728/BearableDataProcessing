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
                "My Birthday"
        );
        recordB = new Record(
                new Date(7, 28, 1998),
                DayOfWeek.TUESDAY,
                new TimeOfDay(6, 15),
                DataCategory.MOOD,
                10,
                "My Birthday"
        );
        recordC = new Record(
                new Date(1, 10, 1999),
                DayOfWeek.WEDNESDAY,
                new TimeOfDay(12, 0),
                DataCategory.MOOD,
                10,
                "Charlotte's Birthday"
        );
    }

    @Test
    void testEquals() {
        assertTrue(recordA.equals(recordB));
        assertFalse(recordA.equals(recordC));
    }

    @Test
    void isProper() {
        assertTrue(recordA.isProper());
        assertTrue(recordB.isProper());
        assertTrue(recordC.isProper());
    }

    @Test
    void createRecordFromBearableTokens() {
        String[] testLines = {
                "\"23rd Sep 2020\",\"Wednesday\",\"pre\",\"Symptom\",\"1\",\"Headache (Mild)\",\"\"",
                "\"8th May 2021\",\"Saturday\",\"mid\",\"Symptom\",\"1\",\"Indigestion (Mild)\",\"\"",
                "\"9th May 2021\",\"Sunday\",\"pre\",\"Symptom\",\"2\",\"Jaw Clenching (Moderate)\",\"\"",
                "\"22nd Jul 2021\",\"Thursday\",\"am\",\"Symptom\",\"1\",\"Constipation (Mild)\",\"\""
        };

        String[][] testTokens = new String[testLines.length][7];
        for (int i = 0; i < testTokens.length; i++) {
            testTokens[i] = testLines[i].split(",");
            for (int j = 0; j < testTokens[i].length; j++) {
                testTokens[i][j] = testTokens[i][j].substring(1, testTokens[i][j].length() - 1);
            }
            assertTrue(Record.createRecordFromBearableTokens(testTokens[i]).isProper());
        }
    }

}