package database;

/*
    BearableStringParser Class

    Author:
        Andrew Miller
    database.Date:
        2021 08 02

    Description:
        This class contains static methods to parse the various tokens from a Bearable export file.
 */
public class BearableStringParser {


    /* METHODS */

    public static Date parseDateFromBearableString(String rawDate) {
        String[] tokens = rawDate.split(" ");

        String[] monthStrings = {
                "Jan", "Feb", "Mar",
                "Apr", "May", "Jun",
                "Jul", "Aug", "Sep",
                "Oct", "Nov", "Dec"
        };
        int numMonth = -1;
        for (int i = 0; i < monthStrings.length; i++) {
            if (tokens[1].equals(monthStrings[i])) {
                numMonth = i + 1;
            }
        }

        return new Date(
                numMonth,
                Integer.parseInt(tokens[0].substring(0, tokens[0].length() - 2)),
                Integer.parseInt(tokens[2])
        );
    }

    public static DayOfWeek parseDayOfWeekFromBearableString(String rawDayOfWeek) {
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            if (DayOfWeek.values()[i].name().equalsIgnoreCase(rawDayOfWeek)) {
                return DayOfWeek.values()[i];
            }
        }
        throw new IllegalArgumentException(String.format("Unknown input string: \"%s\"", rawDayOfWeek));
    }

    public static Time parseTimeOfDayFromBearableString(String rawTimeOfDay) {
        String timeDelimiter = ":";
        if (rawTimeOfDay.equals("")) {
            return new Time(0, 0);
        }
        else if (rawTimeOfDay.contains(timeDelimiter)) {
            int hour = Integer.parseInt(rawTimeOfDay.split(timeDelimiter)[0]);
            int minute = Integer.parseInt(rawTimeOfDay.split(timeDelimiter)[1]);
            return new Time(hour, minute);
        }
        else {
            for (int i = 0; i < Time.TimeBlock.values().length; i++) {
                if (Time.TimeBlock.values()[i].name().equalsIgnoreCase(rawTimeOfDay.replace(' ', '_'))) {
                    return new Time(Time.TimeBlock.values()[i]);
                }
            }
            throw new IllegalArgumentException(
                    String.format("Unexpected input string: \"%s\"", rawTimeOfDay)
            );
        }
    }

    public static DataCategory parseDataCategoryFromBearableString(String rawCategory) {
        if (rawCategory.equalsIgnoreCase("Mood")) {
            return DataCategory.MOOD;
        }
        else if (rawCategory.equalsIgnoreCase("Energy")) {
            return DataCategory.ENERGY;
        }
        else if (rawCategory.equalsIgnoreCase("Symptom")) {
            return DataCategory.SYMPTOM;
        }
        else if (rawCategory.equalsIgnoreCase("Factors")) {
            return DataCategory.FACTORS;
        }
        else if (rawCategory.equalsIgnoreCase("Food Diary")
                || rawCategory.equalsIgnoreCase("Food Diary factors")) {
            return DataCategory.FOOD_DIARY;
        }
        else if (rawCategory.equalsIgnoreCase("Meds/Supplements")) {
            return DataCategory.MEDS;
        }
        else if (rawCategory.equalsIgnoreCase("Sleep")
                || rawCategory.equalsIgnoreCase("Sleep quality")
                || rawCategory.equalsIgnoreCase("Sleep factors")) {
            return DataCategory.SLEEP;
        }
        else if (rawCategory.equalsIgnoreCase("Gratitudes")) {
            return DataCategory.GRATITUDE;
        }
        else if (rawCategory.equalsIgnoreCase("Bowel Movements")) {
            return DataCategory.BOWEL_MOVEMENTS;
        }

        throw new IllegalArgumentException(String.format("Unknown input string: \"%s\"", rawCategory));
    }

    public static float parseAmountFromBearableString(String rawAmount) {
        float amount = -1;
        if (!rawAmount.equals("")) {
            if (rawAmount.equals("None")) {
                amount = 0;
            }
            else if (rawAmount.contains(":")) {
                int hour = Integer.parseInt(rawAmount.split(":")[0]);
                int minute = Integer.parseInt(rawAmount.split(":")[1]);
                amount = hour + (minute / 60f);
            }
            else {
                try {
                    amount = Float.parseFloat(rawAmount);
                }
                catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return amount;
    }


}
