/*
    DataCategoryHandler Class

    Author:
        Andrew Miller
    Date:
        2021 07 29

    Description:
        This class describes a DataHandler object.
 */
public abstract class DataHandler {


    /* VARIABLES */

    // Provides a unique ID number for each DataHandler object
    private static int nextID = 0;
    private final int id;

    private final DataCategory category;
    private Record[] recordList;
    private int listIndex;


    /* CONSTRUCTORS */

    public DataHandler(DataCategory category) {
        this.id = nextID;
        nextID++;
        this.category = category;
        this.recordList = new Record[1];
        this.listIndex = 0;
    }


    /* METHODS */

    public boolean addRecord(Record newRecord) {
        if (this.containsRecord(newRecord) || newRecord.getCategory() != category) {
            return false;
        }
        else if (listIndex == recordList.length) {
            extendRecordList();
        }
        recordList[listIndex] = newRecord;
        listIndex++;
        return true;
    }

    public boolean removeRecord(Record oldRecord) {
        if (this.containsRecord(oldRecord)) {
            int index = this.getRecordIndex(oldRecord);
            recordList[index] = null;
            for (int i = index; i < recordList.length - 1; i++) {
                recordList[i] = recordList[i+1];
                listIndex--;
            }
            return true;
        }
        else {
            return false;
        }
    }

    public boolean containsRecord(Record record) {
        if (recordList != null && recordList.length > 0) {
            for (int i = 0; i < recordList.length; i++) {
                if (recordList[i] != null && recordList[i].equals(record)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getRecordIndex(Record record) {
        for (int i = 0; i < recordList.length; i++) {
            if (recordList[i] != null && recordList[i].equals(record)) {
                return i;
            }
        }
        return -1;
    }

    public Record getRecord(int index) {
        return recordList[index];
    }

    public Record[] getRecordList() {
        return recordList;
    }

    public DataCategory getCategory() {
        return category;
    }

    public int getID() {
        return id;
    }

    private void extendRecordList() {
        Record[] extendedRecordList = new Record[recordList.length * 2];
        for (int i = 0; i < recordList.length; i++) {
            extendedRecordList[i] = recordList[i];
        }
        recordList = extendedRecordList;
    }


}
