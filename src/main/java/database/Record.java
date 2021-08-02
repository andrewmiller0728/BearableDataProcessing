package database;

/*
    Record Class

    Author:
        Andrew Miller
    database.Date:
        2021 07 29

    Description:
        This class describes a database.Record object.
        A database.Record object contains one Bearable data record.
 */
public class Record {


    /* VARIABLES */

    private Date date;
    private DayOfWeek dayOfWeek;
    private TimeOfDay timeOfDay;
    private DataCategory category;
    private float amount;
    private String details;


    /* CONSTRUCTORS */

    // TODO: Add field for notes from exported Bearable Data csv file
    public Record(Date date, DayOfWeek dayOfWeek, TimeOfDay timeOfDay,
                  DataCategory category, float amount, String details) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
        this.category = category;
        this.amount = amount;
        this.details = details;
    }


    /* METHODS */

    public static Record createRecordFromBearableTokens(String[] tokens) {
        return new Record(
                BearableStringParser.parseDateFromBearableString(tokens[0]),
                BearableStringParser.parseDayOfWeekFromBearableString(tokens[1]),
                BearableStringParser.parseTimeOfDayFromBearableString(tokens[2]),
                BearableStringParser.parseDataCategoryFromBearableString(tokens[3]),
                BearableStringParser.parseAmountFromBearableString(tokens[4]),
                tokens[5]
        );
    }

    public Date getDate() {
        return date;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public DataCategory getCategory() {
        return category;
    }

    public float getAmount() {
        return amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public void setCategory(DataCategory category) {
        this.category = category;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isProper() {
        return
                date.isProper()
                && dayOfWeek != null
                && timeOfDay.isProper()
                && category != null
                && details != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;
        Record record = (Record) o;

        if (amount != record.amount) return false;
        if (!date.equals(record.date)) return false;
        if (dayOfWeek != record.dayOfWeek) return false;
        if (!timeOfDay.equals(record.timeOfDay)) return false;
        if (category != record.category) return false;
        if (!details.equals(record.details)) return false;

        return true;
    }


}
