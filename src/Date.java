/*

authors: @kevinarbito @katiesidebotham
 */
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
    //Getter for today's date
    public void getToday(){
        return today;
    }

    public Date(String date) {
        String[] parts = date.split("/");
        String month = parts[0];
        String day = parts[1];
        String year = parts[2];
        Date currDate = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)); // fix name ltr
    } //take “mm/dd/yyyy” and create a Date object
    public Date() {
        Calendar c = Calendar.getInstance();
        Date today = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
    } //create an object with today’s date (see Calendar class)
    public boolean isValid() {
        //must be valid calendar date

        if(this.month == 2){ // may not work since feb is 1 in enum class
            if(isLeapYear()){
                return (this.day <= 29);
            } else{
                return (this.day <= 28);
            }
        }
        return true;
    }
    @Override
    public int compareTo(Date date) {

    }
}