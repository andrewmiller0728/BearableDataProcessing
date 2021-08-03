package database.datahandlers;

import database.DataCategory;
import database.Date;
import database.Record;

/*
    database.datahandlers.DataHandlerEnergy Class

    Author:
        Andrew Miller
    database.Date:
        2021 07 30

    Description:
        This class describes a database.datahandlers.DataHandlerEnergy object.
 */
public class DataHandlerEnergy extends DataHandler {


    /* VARIABLES */

    public final static DataCategory DATA_CATEGORY = DataCategory.ENERGY;


    /* CONSTRUCTORS */

    public DataHandlerEnergy() {
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
                    && recordList[i].getDate().isMoreRecent(startDate)
                    && !recordList[i].getDate().isMoreRecent(endDate)
            ) {
                ratingSum += recordList[i].getAmount();
                ratingCount++;
            }
        }
        return ratingSum / ratingCount;
    }


}
