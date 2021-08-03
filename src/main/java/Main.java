import database.DataCategory;
import database.Database;
import database.Date;
import database.Record;
import database.datahandlers.DataHandler;
import database.datahandlers.DataHandlerEnergy;
import database.datahandlers.DataHandlerMood;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        Database myBearableDatabase = new Database("My Bearable Database");

        try {
            myBearableDatabase.loadDataFromFile("src/main/resources/Bearable_Export_20210728.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        DataCategory dataCategory = DataCategory.MOOD;
//        Record[] records = myBearableDatabase.getRecords(dataCategory);
//        System.out.printf("[Main]\tRetrieved %d %s records from database\n", records.length, dataCategory.name());
//        for (int i = 0; i < records.length; i++) {
//            if (records[i] != null) {
//                System.out.printf("[Main]\t%4d of %4d:\t%s\n", i, records.length, records[i].toString());
//            }
//        }

        DataHandlerMood moodDataHandler = (DataHandlerMood)(myBearableDatabase.getDataHandler(DataCategory.MOOD));
        System.out.printf(
                "[Main]\tAverage Mood Score: %4.3f\n",
                moodDataHandler.getAverageRating(
                        new Date(7, 15, 2000),
                        new Date(7, 31, 2021)
                )
        );

        DataHandlerEnergy energyDataHandler = (DataHandlerEnergy)(myBearableDatabase.getDataHandler(DataCategory.ENERGY));
        System.out.printf(
                "[Main]\tAverage Energy Score: %4.3f\n",
                energyDataHandler.getAverageRating(
                        new Date(7, 15, 2000),
                        new Date(7, 31, 2021)
                )
        );
    }

}
