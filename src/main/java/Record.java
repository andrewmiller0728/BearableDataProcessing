import java.util.Arrays;

/*
    Record Class

    Author:
        Andrew Miller
    Date:
        2021 07 29

    Description:
        This class describes a Record object.
        A Record object contains one Bearable data record.
 */
public class Record {


    /* VARIABLES */

    private Date date;
    private DayOfWeek dayOfWeek;
    private TimeOfDay timeOfDay;
    private DataCategory category;
    private int amount;
    private String[] details;


    /* CONSTRUCTORS */

    public Record(Date date, DayOfWeek dayOfWeek, TimeOfDay timeOfDay,
                  DataCategory category, int amount, String[] details) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
        this.category = category;
        this.amount = amount;
        this.details = details;
    }


    /* METHODS */

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

    public int getAmount() {
        return amount;
    }

    public String[] getDetails() {
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

    public void setDetails(String[] details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;

        Record record = (Record) o;

        if (amount != record.amount) return false;
        if (date != null ? !date.equals(record.date) : record.date != null) return false;
        if (dayOfWeek != record.dayOfWeek) return false;
        if (timeOfDay != null ? !timeOfDay.equals(record.timeOfDay) : record.timeOfDay != null) return false;
        if (category != record.category) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(details, record.details);
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
        result = 31 * result + (timeOfDay != null ? timeOfDay.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + Arrays.hashCode(details);
        return result;
    }


}
