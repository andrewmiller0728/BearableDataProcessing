package database;

import database.datahandlers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
    database.Database Class

    Author:
        Andrew Miller
    database.Date:
        2021 07 29

    Description:
        This class describes a database.Database object. A database.Database object maintains the total
        database of Bearable data. Each database contains database.datahandlers.DataHandler objects for
        each of the eight categories of data.
        A database.Database object can:
          - receive data from a DataLoader object and send it to the appropriate
            DataCategoryHandler object
          - save data to the internal storage for faster load next time
          - request data analysis from each of the database.datahandlers.DataHandler objects
 */
public class Database {


    /* VARIABLES */

    // Provides a unique ID number for each database.Database object
    private static int nextID = 0;
    private int id;
    // As each database.Database has a unique ID number, names are optional
    private String name;

    private DataHandler[] dataHandlers;


    /* CONSTRUCTORS */

    public Database() {
        constructorHelper(null);
    }

    public Database(String name) {
        constructorHelper(name);
    }

    private void constructorHelper(String name) {
        id = nextID;
        nextID++;
        this.name = name;
        dataHandlers = new DataHandler[DataCategory.values().length];
        for (int i = 0; i < dataHandlers.length; i++) {
            dataHandlers[i] = createDataHandlerByCategory(DataCategory.values()[i]);
        }
        System.out.printf("[database.Database]\tCreated new object %s at %d ms\n", this.toString(), System.currentTimeMillis());
    }


    /* METHODS */

    public boolean addRecord(Record newRecord) {
        return this.getDataHandlerByCategory(newRecord.getCategory()).addRecord(newRecord);
    }

//    public boolean forceAddRecord(database.Record newRecord) {
//        // Add database.Record to appropriate DataCategoryHandler regardless of contents
//        // Returns true if database.Record is added (forced), false otherwise
//        return false;
//    }

    public boolean removeRecord(Record oldRecord) {
        return this.getDataHandlerByCategory(oldRecord.getCategory()).removeRecord(oldRecord);
    }

    public Record[] getRecords(Date date) {
        if (!date.isProper()) {
            throw new IllegalArgumentException("Input date is improper");
        }

        Record[] recordList = new Record[8];
        int recordListIndex = 0;

        for (int i = 0; i < DataCategory.values().length; i++) {
            Record[] tempRecordList = this.getRecords(date, DataCategory.values()[i]);
            for (int j = 0; j < tempRecordList.length; j++) {
                if (tempRecordList[j] != null){
                    recordList[recordListIndex] = tempRecordList[j];
                    recordListIndex++;
                    if (recordListIndex == recordList.length) {
                        extendRecordList(recordList);
                    }
                }
            }
        }

        return recordList;
    }

    public Record[] getRecords(DataCategory category) {
        return this.getDataHandlerByCategory(category).getRecordList();
    }

    public Record[] getRecords(Date date, DataCategory category) {
        Record[] recordList = new Record[8];
        int recordListIndex = 0;

        DataHandler currDataHandler = getDataHandlerByCategory(category);
        for (int j = 0; j < currDataHandler.getRecordList().length; j++) {
            Record currRecord = currDataHandler.getRecord(j);
            if (currRecord != null && currRecord.getDate().equals(date)) {
                recordList[recordListIndex] = currRecord;
                recordListIndex++;
                if (recordListIndex == recordList.length) {
                    extendRecordList(recordList);
                }
            }
        }

        return recordList;
    }

    public boolean loadDataFromFile(String filepath) throws FileNotFoundException {
        String[] csvFileLines = getFileLines(new File(filepath));
        System.out.printf("[database.Database]\tReceived %d lines...\n", csvFileLines.length);
        String delimiter = ",";
        for (int i = 0; i < csvFileLines.length; i++) {
            System.out.printf("[database.Database]\tLoading line %4d of %4d:\n\t\"%s\"\n", i, csvFileLines.length, csvFileLines[i]);
            String[] tokens = csvFileLines[i].split(delimiter);
            for (int j = 0; j < tokens.length; j++) {
                tokens[j] = tokens[j].substring(1, tokens[j].length() - 1);
            }
            if (!this.addRecord(Record.createRecordFromBearableTokens(tokens))) {
                return false;
            }
        }
        return true;
    }

    public static void loadDataFromDirectory(String directoryPath, Database database) {

    }

    // TODO: saveData, loadData
    public boolean saveData(String filepath) {
        // Save data to input filepath for faster load on resume
        // Returns true if data is saved, false otherwise
        return false;
    }

    public boolean loadData(String filepath) {
        // Loads previously saved data (not data from .csv files)
        // Returns true if data is loaded, false otherwise
        return false;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return "database.Database{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    private DataHandler createDataHandlerByCategory(DataCategory category) {
        switch (category) {
            case MOOD:
                return new DataHandlerMood();
            case ENERGY:
                return new DataHandlerEnergy();
            case SYMPTOM:
                return new DataHandlerSymptom();
            case FACTORS:
                return new DataHandlerFactors();
            case FOOD_DIARY:
                return new DataHandlerFoodDiary();
            case MEDS:
                return new DataHandlerMeds();
            case SLEEP:
                return new DataHandlerSleep();
            case GRATITUDE:
                return new DataHandlerGratitude();
            case BOWEL_MOVEMENTS:
                return new DataHandlerBowelMovements();
        }
        throw new IllegalArgumentException(String.format("Unexpected database.DataCategory \"%s\"", category.name()));
    }

    private DataHandler getDataHandlerByCategory(DataCategory category) {
        for (int i = 0; i < dataHandlers.length; i++) {
            if (dataHandlers[i].getCategory() == category) {
                return dataHandlers[i];
            }
        }
        throw new IllegalArgumentException(String.format("Input database.DataCategory \"%s\" is not allowed", category.name()));
    }

    private static void extendRecordList(Record[] recordList) {
        Record[] extendedRecordList = new Record[recordList.length * 2];
        for (int i = 0; i < recordList.length; i++) {
            extendedRecordList[i] = recordList[i];
        }
        recordList = extendedRecordList;
    }

    private static String[] getFileLines(File csvFile) throws FileNotFoundException {
        System.out.printf("[database.Database]\tAttempting to load lines from file:\n\t\"%s\"...\n", csvFile.getAbsoluteFile());
        Scanner scanner = new Scanner(csvFile);
        System.out.println("[database.Database]\tFile found, attempting to count lines...");

        scanner.nextLine(); // skip header
        int lineCount = 0;
        while (scanner.hasNextLine()) {
            lineCount++;
            scanner.nextLine();
        }
        scanner.close();
        System.out.printf("[database.Database]\t%d lines found in file...\n", lineCount);

        System.out.println("[database.Database]\tGathering lines as Strings...");
        scanner = new Scanner(csvFile);
        scanner.nextLine(); //skip header
        String[] lines = new String[lineCount];
        int linesIndex = 0;
        while (scanner.hasNextLine()) {
            lines[linesIndex] = scanner.nextLine();
            linesIndex++;
        }
        scanner.close();
        System.out.printf("[database.Database]\tLines gathered, returning object\n\t\"%s\"\n", lines.toString());
        return lines;
    }

}
