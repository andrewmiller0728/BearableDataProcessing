/*
    DataCategory Enum

    Author:
        Andrew Miller
    Date:
        2021 07 29

    Description:
        enum of data categories
 */
// TODO: BOWEL_MOVEMENTS DataCategory is in Bearable Data Export
public enum DataCategory {

    MOOD, ENERGY, SYMPTOM, FACTORS, FOOD_DIARY, MEDS, SLEEP, GRATITUDE, BOWEL_MOVEMENTS;

    public static DataCategory parseDataCategoryFromBearableString(String rawCategory) {
        if (rawCategory.equalsIgnoreCase("Mood")) {
            return MOOD;
        }
        else if (rawCategory.equalsIgnoreCase("Energy")) {
            return ENERGY;
        }
        else if (rawCategory.equalsIgnoreCase("Symptom")) {
            return SYMPTOM;
        }
        else if (rawCategory.equalsIgnoreCase("Factors")) {
            return FACTORS;
        }
        else if (rawCategory.equalsIgnoreCase("Food Diary")
                || rawCategory.equalsIgnoreCase("Food Diary factors")) {
            return FOOD_DIARY;
        }
        else if (rawCategory.equalsIgnoreCase("Meds/Supplements")) {
            return MEDS;
        }
        else if (rawCategory.equalsIgnoreCase("Sleep")
                || rawCategory.equalsIgnoreCase("Sleep quality")
                || rawCategory.equalsIgnoreCase("Sleep factors")) {
            return SLEEP;
        }
        else if (rawCategory.equalsIgnoreCase("Gratitudes")) {
            return GRATITUDE;
        }
        else if (rawCategory.equalsIgnoreCase("Bowel Movements")) {
            return BOWEL_MOVEMENTS;
        }

        throw new IllegalArgumentException(String.format("Unknown input string: \"%s\"", rawCategory));
    }

}