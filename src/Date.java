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

    } //take “mm/dd/yyyy” and create a Date object
    public Date() {

    } //create an object with today’s date (see Calendar class)
    public boolean isValid() {

    }
    @Override
    public int compareTo(Date date) {

    }
}