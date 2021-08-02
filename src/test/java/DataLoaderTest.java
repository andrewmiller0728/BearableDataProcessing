import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataLoaderTest {

    private DataLoader dataLoader;
    private Database database;
    private final static String FILEPATH = "D://Mood Data Exports/Bearable_Export_20210728";

    @BeforeEach
    void setUp() {
        dataLoader = new DataLoader();
        database = new Database("My Database");
    }

    @Test
    void loadDataFromFile() {
        assertTrue();
    }

    @Test
    void loadDataFromDirectory() {
    }
}