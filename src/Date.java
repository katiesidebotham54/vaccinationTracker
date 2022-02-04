import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public Date(String date) {
        date.useDelimiter("/");


        Date date = new Date();
    } //take “mm/dd/yyyy” and create a Date object
    public Date() {
        Calendar c = Calendar.getInstance();
        Date today = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
    } //create an object with today’s date (see Calendar class)
    public boolean isValid() {
        //must be valid calendar date

        //check if date of birth is today or future date
        if(this >= today){
            return false;
        }
        if(this <= today || this > )


        //date of birth is not today or future date
        //date cannot be today, day before today, date beyond one year
        //
    }
    @Override
    public int compareTo(Date date) {

    }
}