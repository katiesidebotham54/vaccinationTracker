import java.util.Calendar;

/**
 *
 * @author kevinarbito, katiesidebotham
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int MIN_DAYS_IN_MONTH = 30;
    public static final int MAX_DAYS_IN_MONTH = 31;
    public static final int FEBRUARY_MAX = 29;
    public static final int FEBRUARY_MIN= 28;

    /**
     * This constructor takes in string format of date and creates the Date object using integers.
     * This allows for easy acces within the date object.
     * @param date date needed to create object
     */
    public Date(String date) {
        String[] dateParts = date.split("/");
        this.month = Integer.parseInt(dateParts[0]);
        this.day = Integer.parseInt(dateParts[1]);
        this.year = Integer.parseInt(dateParts[2]);
    }

    /**
     * This constructor takes in no parameters, it simply gets today's date using calendar class and stores
     * today's date within the object.
     */
    public Date() {
        Calendar cal = Calendar.getInstance();
        this.month = cal.get(Calendar.MONTH);
        this.day = cal.get(Calendar.DATE);
        this.year = cal.get(Calendar.YEAR);
    }

    /**
     * isLeapYear() checks whether the year within the date is a leap year. It goes through checks by modding
     * QUADRENNIAL, CENTENNIAL, and QUATERCENTENNIAL. It returns its boolean value true or false based on its
     * results from each conditional.
     * @return true if leap year, false otherwise.
     */
    public boolean isLeapYear(){
            if(this.year % QUADRENNIAL == 0){
                if(this.year % CENTENNIAL == 0) {
                    return this.year % QUATERCENTENNIAL == 0;
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

    public int getMonth() {return this.month;}


    /**
     * checkDay() checks whether the date that was given is actually a valid date based on the month, days, and year.
     * It checks month for its max ammount of days. It also takes whether the year is a leap year. If the date is valid,
     * and within range, it will return true, false otherwise.
     * @return true if date is within the correct range, false otherwise
     */
    public boolean isValidDays(){
        switch(this.month){
            case 1, 3, 5, 7, 8, 10, 12 -> {
                if(this.day <= MAX_DAYS_IN_MONTH) return true;
            }
            case 4, 6, 9, 11 -> {
                if(this.day <= MIN_DAYS_IN_MONTH) return true;
            }
            case 2 -> {
                if(this.isLeapYear()){
                    if(this.day <= FEBRUARY_MAX) return true;
                } else{
                    if(this.day <= FEBRUARY_MIN) return true;
                }
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    /**
     * This method overrides and returns the date in string format.
     * @return the date in string format.
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * isValid() checks to see if the date that was input is a valid date with format MM/DD/YYYY. It also checks to see
     * whether the date has a valid number in terms of month, days, and year. It returns false if they are not within
     * range provided, true otherwise.
     * @return Boolean: true if the months, days, and year is within the range, false otherwise.
     */
    public boolean isValidDate() {
        if(this.toString().matches("\\d{1}/\\d{1}/\\d{4}") || this.toString().matches("\\d{2}/\\d{2}/\\d{4}") || this.toString().matches("\\d{1}/\\d{2}/\\d{4}") || this.toString().matches("\\d{2}/\\d{1}/\\d{4}") ) {
            if (this.year > 2022 || this.year < 1800) {
                return false;
            }
            if (this.month > 12 || this.month < 1) {
                return false;
            }
            if (this.day < 1 || this.day > 31) {
                return false;
            }
            if (this.month == 1) {
                if (isLeapYear()) {
                    return (this.day <= 29);
                } else {
                    return (this.day <= 28);
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * This method overrides compareTo and checks whether the dates are equal to, year is greater or less than, month
     * is greater than or less than, and days is greater than or less than.
     * @param date date to be compared to
     * @return -1 if less than, 1 if greater than, 0 if equal to.
     */
    @Override
    public int compareTo(Date date) {
        if(this.equals(date)){
            return 0;
        } else if(this.year > date.year) {
            return 1;
        } else if(this.year < date.year) {
            return -1;
        } else {
            if(this.month < date.month) {
                return -1;
            } else if(this.month > date.month) {
                return 1;
            } else {
                if(this.day < date.day) {
                    return -1;
                } else if(this.day > date.day) {
                    return 1;
                }
            }
        }
        return 0;
    }
}