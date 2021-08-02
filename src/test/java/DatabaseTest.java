import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    private Database database;
    private Record[] testRecords;

    @BeforeEach
    void setUp() {
        database = new Database("My Database");
        testRecords = new Record[]{
                new Record(
                        new Date(7, 28, 1998),
                        DayOfWeek.TUESDAY,
                        new TimeOfDay(6, 15),
                        DataCategory.MOOD,
                        10,
                        "My Birthday"
                ),
                new Record(
                        new Date(8, 7, 1972),
                        DayOfWeek.TUESDAY,
                        new TimeOfDay(12, 0),
                        DataCategory.ENERGY,
                        10,
                        "My Mom's Birthday"
                ),
                new Record(
                        new Date(1, 10, 1999),
                        DayOfWeek.WEDNESDAY,
                        new TimeOfDay(12, 0),
                        DataCategory.SLEEP,
                        10,
                        "Charlotte's Birthday"
                )
        };
    }

    @Test
    void addRecord() {
        for (int i = 0; i < testRecords.length; i++) {
            assertTrue(database.addRecord(testRecords[i]));
        }
    }

    @Test
    void removeRecord() {
        for (int i = 0; i < testRecords.length; i++) {
            database.addRecord(testRecords[i]);
        }
        assertTrue(database.removeRecord(testRecords[0]));
    }

    @Test
    void getRecords() {
        for (int i = 0; i < testRecords.length; i++) {
            database.addRecord(testRecords[i]);
        }
        assertEquals(testRecords[0], database.getRecords(testRecords[0].getDate())[0]);
        assertEquals(testRecords[1], database.getRecords(DataCategory.ENERGY)[0]);
        assertEquals(testRecords[2], database.getRecords(testRecords[2].getDate(), DataCategory.SLEEP)[0]);
    }

    @Test
    void loadDataFromFile() throws FileNotFoundException {
        assertTrue(database.loadDataFromFile("C:\\Users\\Andrew\\Documents\\Programming\\BearableDataProcessing\\src\\main\\resources\\Bearable_Export_20210728.csv"));
    }

}