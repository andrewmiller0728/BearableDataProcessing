import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public static boolean loadDataFromFile(String filepath, Database database) throws FileNotFoundException {
        String[] csvFileLines = getFileLines(new File(filepath));
        String delimiter = ",";
        for (int i = 0; i < csvFileLines.length; i++) {
            String[] tokens = csvFileLines[i].split(delimiter);
            for (int j = 0; j < tokens.length; j++) {
                tokens[j] = tokens[j].substring(1, tokens[j].length() - 1);
            }
            if (!database.addRecord(Record.createRecordFromBearableTokens(tokens))) {
                return false;
            }
        }
        return true;
    }

    public static void loadDataFromDirectory(String directoryPath, Database database) {

    }

    private static String[] getFileLines(File csvFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(csvFile);

        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
        }
        scanner.reset();

        String[] lines = new String[lineCount];
        int linesIndex = 0;
        while (scanner.hasNextLine()) {
            lines[linesIndex] = scanner.nextLine();
        }
        scanner.close();
        return lines;
    }

}
