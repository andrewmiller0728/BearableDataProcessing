package database.datahandlers;

import database.DataCategory;
import database.Date;
import database.Record;

/*
    DataHandlerMood Class

    Author:
        Andrew Miller
    database.Date:
        2021 07 30

    Description:
        This class describes a DataHandlerMood object.
 */
public class DataHandlerMood extends DataHandler {


    /* VARIABLES */

    public final static DataCategory DATA_CATEGORY = DataCategory.MOOD;


    /* CONSTRUCTORS */

    public DataHandlerMood() {
        super(DATA_CATEGORY);
    }


    /* METHODS */

    public float getAverageRating(Date startDate, Date endDate) {
        Record[] recordList = super.getRecordList();
        float ratingSum = 0;
        float ratingCount = 0;
        for (int i = 0; i < recordList.length; i++) {
            if (
                    recordList[i] != null
                            && (recordList[i].getDate().isMoreRecent(startDate))
                            && !recordList[i].getDate().isMoreRecent(endDate)
            ) {
                ratingSum += recordList[i].getAmount();
                ratingCount++;
            }
        }
        return ratingSum / ratingCount;
    }


}
