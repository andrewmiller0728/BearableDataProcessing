/*
    DataLoader Class

    Author:
        Andrew Miller
    Date:
        2021 07 29

    Description:
        This class provides methods to load data into the database from Bearable data exports.

    Author Notes:
        The class is implemented instead of having the database load its own data to provide
        for future extension to more data types. (ex: Daylio Export Data)
 */
public class DataLoader {

    // Class is static, no constructor


    /* METHODS */

    public static void loadDataFromFile(String filepath, Database database) {
        // load data from <filepath> into <database>
    }

    public static void loadDataFromDirectory(String directorypath, Database database) {
        // load data from all applicable files in <directorypath> into <database>
    }

}
