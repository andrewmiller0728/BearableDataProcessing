package database;

/*
    database.TimeOfDay Class

    Author:
        Andrew Miller
    database.Date:
        2021 07 29

    Description:
        This class describes a database.TimeOfDay object. Time must be
        input in 24-hr format. A database.TimeOfDay object is considered
        proper if its time (hour & minute) falls within its TimeBlock
 */
public class TimeOfDay {


    /* ENUMS */

    public enum TimeBlock {
        PRE, AM, MID, PM, ALL_DAY
    }


    /* VARIABLES */

    private int hour;
    private int minute;
    private TimeBlock timeBlock;


    /* CONSTRUCTORS */

    public TimeOfDay(int hour, int minute) {
        if (!isRealTime(hour, minute)) {
            throw new IllegalArgumentException("Input time is not a real, 24-hr time.");
        }
        this.hour = hour;
        this.minute = minute;
        this.timeBlock = calcTimeBlock(hour, minute);
    }

    public TimeOfDay(TimeBlock timeBlock) {
        this.hour = -1;
        this.minute = -1;
        this.timeBlock = timeBlock;
    }

    public TimeOfDay(int hour, int minute, TimeBlock timeBlock) {
        if (!isRealTime(hour, minute)) {
            throw new IllegalArgumentException("Input time is not a real, 24-hr time.");
        }
        this.hour = hour;
        this.minute = minute;
        this.timeBlock = timeBlock;
    }


    /* METHODS */

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public TimeBlock getTimeBlock() {
        return timeBlock;
    }

    public void setHour(int hour) {
        if (!isRealHour(hour)) {
            throw new IllegalArgumentException("Input hour is not within the bounds [0, 23].");
        }
        this.hour = hour;
    }

    public void setMinute(int minute) {
        if (!isRealMinute(minute)) {
            throw new IllegalArgumentException("Input minute is not within the bounds [0, 59].");
        }
        this.minute = minute;
    }

    public void setTimeBlock(TimeBlock timeBlock) {
        this.timeBlock = timeBlock;
    }

    public boolean isProper() {
        if (timeBlock == TimeBlock.ALL_DAY || (hour == -1 && minute == -1)) {
            return true;
        }
        else {
            return calcTimeBlock(hour, minute) == timeBlock;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeOfDay)) return false;

        TimeOfDay timeOfDay = (TimeOfDay) o;

        if (hour != timeOfDay.hour) return false;
        if (minute != timeOfDay.minute) return false;
        return timeBlock == timeOfDay.timeBlock;
    }

    private TimeBlock calcTimeBlock(int hour, int minute) {
        if (hour < 6) {
            return TimeBlock.PRE;
        }
        else if (hour < 12) {
            return TimeBlock.AM;
        }
        else if (hour < 18) {
            return TimeBlock.MID;
        }
        else {
            return TimeBlock.PM;
        }
    }

    private boolean isRealHour(int hour) {
        return 0 <= hour && hour <= 23;
    }

    private boolean isRealMinute(int minute) {
        return 0 <= minute && minute <= 59;
    }

    private boolean isRealTime(int hour, int minute) {
        return isRealHour(hour) && isRealMinute(minute);
    }

}
