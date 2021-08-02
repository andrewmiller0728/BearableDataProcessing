/*
    Date Class

    Author:
        Andrew Miller
    Date:
        2021 07 29

    Description:
        This class describes a Date object.
        A Date object contains integer values for day, month, and year.
 */
public class Date {


    /* VARIABLES */

    private int day;
    private int month;
    private int year;


    /* CONSTRUCTORS */

    public Date(int month, int day, int year) {
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }


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

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isProper() {
        return isRealDay(day) && isRealMonth(month) && isRealYear(year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;

        Date date = (Date) o;

        if (day != date.day) return false;
        if (month != date.month) return false;
        return year == date.year;
    }

    private int getDaysInMonth(int month, int year) {
        if (month == 1 || month == 3 || month == 5
                || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        }
        else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        }
        else if (month == 2) {
            if (isLeapYear(year)) {
                return 29;
            }
            else {
                return 28;
            }
        }
        else {
            throw new IllegalArgumentException("Month value is not within expected bounds [1, 12]");
        }
    }

    private boolean isRealDay(int day) {
        return 0 < day && day <= getDaysInMonth(month, year);
    }

    private boolean isRealMonth(int month) {
        return 0 < month && month <= 12;
    }

    private boolean isRealYear(int year) {
        return 0 < year && year <= getCurrentYear();
    }

    private boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    private int getCurrentYear() {
        return (int) (System.currentTimeMillis() / 1000f / 3600f / 8766f) + 1970;
    }


}
