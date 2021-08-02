package database;

/*
    database.DayOfWeek Enum

    Author:
        Andrew Miller
    database.Date:
        2021 07 29

    Description:
        enum of the days of the week
 */
public enum DayOfWeek {

    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    public static DayOfWeek parseDayOfWeekFromBearableString(String rawDayOfWeek) {
        for (int i = 0; i < values().length; i++) {
            if (values()[i].name().equalsIgnoreCase(rawDayOfWeek)) {
                return values()[i];
            }
        }
        throw new IllegalArgumentException(String.format("Unknown input string: \"%s\"", rawDayOfWeek));
    }

}