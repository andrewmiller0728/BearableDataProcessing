/*
    Database Class

    Author:
        Andrew Miller
    Date:
        2021 07 29

    Description:
        This class describes a Database object. A Database object maintains the total
        database of Bearable data. Each database contains DataHandler objects for
        each of the eight categories of data.
        A Database object can:
          - receive data from a DataLoader object and send it to the appropriate
            DataCategoryHandler object
          - save data to the internal storage for faster load next time
          - request data analysis from each of the DataHandler objects
 */
public class Database {


    /* VARIABLES */

    public final int DATA_CATEGORY_COUNT = 8;

    // Provides a unique ID number for each Database object
    private static int nextID = 0;
    private final int id;
    // As each Database has a unique ID number, names are optional
    private final String name;

    private final DataHandler[] dataHandlers;


    /* CONSTRUCTORS */

    public Database() {
        id = nextID;
        nextID++;
        this.name = null;
        dataHandlers = new DataHandler[DATA_CATEGORY_COUNT];
        for (int i = 0; i < dataHandlers.length; i++) {
            dataHandlers[i] = createDataHandlerByCategory(DataCategory.values()[i]);
        }
//        dataHandlers[0] = new DataHandlerMood();
//        dataHandlers[1] = new DataHandlerEnergy();
//        dataHandlers[2] = new DataHandlerSymptom();
//        dataHandlers[3] = new DataHandlerFactors();
//        dataHandlers[4] = new DataHandlerFoodDiary();
//        dataHandlers[5] = new DataHandlerMeds();
//        dataHandlers[6] = new DataHandlerSleep();
//        dataHandlers[7] = new DataHandlerGratitude();
    }

    public Database(String name) {
        id = nextID;
        nextID++;
        this.name = name;
        dataHandlers = new DataHandler[DATA_CATEGORY_COUNT];
        for (int i = 0; i < dataHandlers.length; i++) {
            dataHandlers[i] = createDataHandlerByCategory(DataCategory.values()[i]);
        }
//        dataHandlers[0] = new DataHandlerMood();
//        dataHandlers[1] = new DataHandlerEnergy();
//        dataHandlers[2] = new DataHandlerSymptom();
//        dataHandlers[3] = new DataHandlerFactors();
//        dataHandlers[4] = new DataHandlerFoodDiary();
//        dataHandlers[5] = new DataHandlerMeds();
//        dataHandlers[6] = new DataHandlerSleep();
//        dataHandlers[7] = new DataHandlerGratitude();
    }


    /* METHODS */

    public boolean addRecord(Record newRecord) {
        return this.getDataHandlerByCategory(newRecord.getCategory()).addRecord(newRecord);
    }

//    public boolean forceAddRecord(Record newRecord) {
//        // Add Record to appropriate DataCategoryHandler regardless of contents
//        // Returns true if Record is added (forced), false otherwise
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
        }
        return null;
    }

    private DataHandler getDataHandlerByCategory(DataCategory category) {
        for (int i = 0; i < dataHandlers.length; i++) {
            if (dataHandlers[i].getCategory() == category) {
                return dataHandlers[i];
            }
        }
        throw new IllegalArgumentException("Input DataCategory is not allowed");
    }

    private static void extendRecordList(Record[] recordList) {
        Record[] extendedRecordList = new Record[recordList.length * 2];
        for (int i = 0; i < recordList.length; i++) {
            extendedRecordList[i] = recordList[i];
        }
        recordList = extendedRecordList;
    }


}
