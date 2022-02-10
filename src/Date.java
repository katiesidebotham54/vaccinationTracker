/*

authors: @kevinarbito @katiesidebotham
 */
import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(String date) {
        String[] parts = date.split("/");
        String month = parts[0];
        String day = parts[1];
        String year = parts[2];
        Date d = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day)); // fix name ltr
    } //take “mm/dd/yyyy” and create a Date object
    public Date() {
        Calendar c = Calendar.getInstance();
        Date today = new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
    } //create an object with today’s date (see Calendar class)

    public boolean isLeapYear(){
            if(this.year % QUADRENNIAL == 0){
                if(this.year % CENTENNIAL == 0) {
                    if (this.year % QUATERCENTENNIAL == 0) {
                        return true;
                    }
                } else{
                    return true;
                }
            }
            return false;
    }

    public int getYear(){
        return this.year;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {return this.month;};

    public Date getToday(){
        return today;
    }

    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }


    public boolean isValid() {
        //check if valid input (mm/dd/yyyy)
        if(!this.toString().matches("(\\d{4})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])")){
            return false;
        }
        //must be valid calendar date
        if(this.year > 9999 || this.year < 1800 ){
            return false;
        }
        if(this.month > 12 || this.month < 1){
            return false;
        }
        if(this.day < 1 || this.day > 31){
            return false;
        }

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
        if(this == date){
            return 0;
        } else if(this.year > date.year){
            return 1;
        } else if(this.month == date.month){
            if(this.day > date.day){
                return 1;
            }
        } else{
            return -1;
        }
        return -1;
    }
}