import database.*;
import database.datahandlers.DataHandler;
import database.datahandlers.DataHandlerEnergy;
import database.datahandlers.DataHandlerMood;
import database.datahandlers.DataHandlerSymptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {

    private DataHandler[] dataHandlers;
    private Record[] testRecords;

    @BeforeEach
    void setUp() {
        dataHandlers = new DataHandler[]{
                new DataHandlerEnergy(),
                new DataHandlerMood(),
                new DataHandlerSymptom()
        };
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
                        new Date(7, 28, 1998),
                        DayOfWeek.TUESDAY,
                        new TimeOfDay(6, 15),
                        DataCategory.MOOD,
                        10,
                        "My Birthday"
                ),
                new Record(
                        new Date(7, 28, 1998),
                        DayOfWeek.TUESDAY,
                        new TimeOfDay(6, 15),
                        DataCategory.ENERGY,
                        10,
                        "My Birthday"
                ),
                new Record(
                        new Date(1, 10, 1999),
                        DayOfWeek.WEDNESDAY,
                        new TimeOfDay(12, 0),
                        DataCategory.MOOD,
                        10,
                        "Charlotte's Birthday"
                )
        };
    }

    @Test
    void addRecord() {
        assertFalse(dataHandlers[0].addRecord(testRecords[0]));
        assertFalse(dataHandlers[0].addRecord(testRecords[1]));
        assertTrue(dataHandlers[0].addRecord(testRecords[2]));
        assertFalse(dataHandlers[0].addRecord(testRecords[3]));

        assertTrue(dataHandlers[1].addRecord(testRecords[0]));
        assertFalse(dataHandlers[1].addRecord(testRecords[1]));
        assertFalse(dataHandlers[1].addRecord(testRecords[2]));
        assertTrue(dataHandlers[1].addRecord(testRecords[3]));

        assertFalse(dataHandlers[2].addRecord(testRecords[0]));
        assertFalse(dataHandlers[2].addRecord(testRecords[1]));
        assertFalse(dataHandlers[2].addRecord(testRecords[2]));
        assertFalse(dataHandlers[2].addRecord(testRecords[3]));
    }

    @Test
    void removeRecord() {
        dataHandlers[0].addRecord(testRecords[2]);
        assertTrue(dataHandlers[0].removeRecord(testRecords[2]));

        dataHandlers[1].addRecord(testRecords[0]);
        dataHandlers[1].addRecord(testRecords[3]);
        assertTrue(dataHandlers[1].removeRecord(testRecords[0]));
        assertTrue(dataHandlers[1].removeRecord(testRecords[3]));

        assertFalse(dataHandlers[2].removeRecord(testRecords[1]));
    }

    @Test
    void containsRecord() {
        dataHandlers[0].addRecord(testRecords[2]);
        assertTrue(dataHandlers[0].containsRecord(testRecords[2]));

        dataHandlers[1].addRecord(testRecords[0]);
        dataHandlers[1].addRecord(testRecords[3]);
        assertTrue(dataHandlers[1].containsRecord(testRecords[0]));
        assertTrue(dataHandlers[1].containsRecord(testRecords[3]));

        assertFalse(dataHandlers[2].containsRecord(testRecords[1]));
    }

    @Test
    void getRecordIndex() {
        dataHandlers[0].addRecord(testRecords[2]);
        assertEquals(0, dataHandlers[0].getRecordIndex(testRecords[2]));

        dataHandlers[1].addRecord(testRecords[0]);
        dataHandlers[1].addRecord(testRecords[3]);
        assertEquals(0, dataHandlers[1].getRecordIndex(testRecords[0]));
        assertEquals(1, dataHandlers[1].getRecordIndex(testRecords[3]));

        assertEquals(-1, dataHandlers[2].getRecordIndex(testRecords[1]));
    }

}