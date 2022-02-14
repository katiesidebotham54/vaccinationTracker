import java.util.Calendar;

/**
 *The date class is responsible for creating date objects and holding constants that are necessary to test for
 * valid dates within each appointment and the date of birth of each patient. Date class creates Date objects which
 * hold the year, month, and day. It can also generate today’s date which may be compared to a specific date to check if
 * a date is before today, today’s date, or a future date. Data is utilized to ensure that kiosk generates the correct
 * output for each command.
 * @author @kevinarbitodelgado, @katherinesidebotham
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
    public static final int MAX_YEAR = 2022;
    public static final int MIN_YEAR = 1800;
    public static final int MAX_MONTH = 12;
    public static final int MIN_MONTH = 1;
    public static final int MAX_DAYS = 31;
    public static final int MIN_DAYS = 1;

    /**
     * This constructor takes in string format of date and creates the Date object using integers.
     * This allows for easy acces within the date object.
     * @param date date needed to create object
     */
    public Date(String date) {
        if(!date.matches("\\d{1}/\\d{1}/\\d{4}")
                || this.toString().matches("\\d{2}/\\d{2}/\\d{4}")
                || this.toString().matches("\\d{1}/\\d{2}/\\d{4}")
                || this.toString().matches("\\d{2}/\\d{1}/\\d{4}")){
            return;
        }
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
    /**
     * Retrieves the year for patient from date
     * @return returns the patient object to where it was called from.
     */
    public int getYear(){
        return this.year;
    }
    /**
     * Retrieves the day for patient from date
     * @return returns the patient object to where it was called from.
     */
    public int getDay() {
        return this.day;
    }
    /**
     * Retrieves the month for patient from date
     * @return returns the patient object to where it was called from.
     */
    public int getMonth() {return this.month;}


    /**
     * checkDay() checks whether the date that was given is actually a valid date based on the month, days, and year.
     * It checks month for its max ammount of days. It also takes whether the year is a leap year. If the date is valid,
     * and within range, it will return true, false otherwise.
     * @return true if date is within the correct range, false otherwise
     */
    public boolean isValidDays(){
        switch(this.month){
            case Month.JANUARY, Month.MARCH, Month.MAY, Month.JULY, Month.AUGUST, Month.OCTOBER, Month.DECEMBER -> {
                if(this.day <= MAX_DAYS_IN_MONTH) return true;
            }
            case Month.APRIL, Month.JUNE, Month.SEPTEMBER, Month.NOVEMBER -> {
                if(this.day <= MIN_DAYS_IN_MONTH) return true;
            }
            case Month.FEBRUARY -> {
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
        if(this.toString().matches("\\d{1}/\\d{1}/\\d{4}")
                || this.toString().matches("\\d{2}/\\d{2}/\\d{4}")
                || this.toString().matches("\\d{1}/\\d{2}/\\d{4}")
                || this.toString().matches("\\d{2}/\\d{1}/\\d{4}") ) {
            if (this.year > MAX_YEAR || this.year < MIN_YEAR) {
                return false;
            }
            if (this.month > MAX_MONTH || this.month < MIN_MONTH) {
                return false;
            }
            if (this.day > MAX_DAYS || this.day < MIN_DAYS) {
                return false;
            }
            if (this.month == MIN_MONTH) {
                if (isLeapYear()) {
                    return (this.day <= FEBRUARY_MAX);
                } else {
                    return (this.day <= FEBRUARY_MIN);
                }
            }
        } else return false;
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
    /**
     * Testbed main
     * Testing cases from Test Design doc; commented numbers correspond to specific test case
     * @param args to test commands from console
     */
    public static void main(String[] args){
        Date dateOne = new Date("04-20-2022");
        Date dateTwo = new Date("12/40/2022");
        Date dateThree = new Date("1/0/2022");
        Date dateFour = new Date("13/9/2022");
        Date dateFive =  new Date("0/2/2022");
        Date dateSix = new Date("12/9/2044");
        Date dateSeven = new Date("1/2/1778");
        Date dateEight = new Date("02/13/2022");
        Date dateNine = new Date("02/12/2022");
        Date dateTen =  new Date("01/02/2023");
        Date dateEleven = new Date("02/13/2022");
        Date dateTwelve =  new Date("04/22/2022");
        //Test case 1, check valid format
        System.out.println("Testcase 1: " + dateOne + " Output: " + dateOne.isValidDate());
        //Test case 2, check greater than 31 days
        System.out.println("Testcase 2: " + dateTwo + " Output: " + dateTwo.isValidDate());
        //Test case 3, check less than 1 day
        System.out.println("Testcase 3: " + dateThree + " Output: " + dateThree.isValidDate());
        //Test case 4, check month greater than 12
        System.out.println("Testcase 4: " + dateFour + " Output: " + dateFour.isValidDate());
        //Test case 5, check month less than 1
        System.out.println("Testcase 5: " + dateFive + " Output: " + dateFive.isValidDate());
        //Test case 6, check year greater than current year
        System.out.println("Testcase 6: " + dateSix + " Output: " + dateSix.isValidDate());
        //Test case 7, check year less than 1800
        System.out.println("Testcase 7: " + dateSeven + " Output: " + dateSeven.isValidDate());
        //Test case 8, check appt date is today
        System.out.println("Testcase 8: " + dateEight + " Output: " + dateEight.isValidDate());
        //Test case 9, check appt date is day before today
        System.out.println("Testcase 9: " + dateNine + " Output: " + dateNine.isValidDate());
        //Test case 10, check appt date beyond this year
        System.out.println("Testcase 10: " + dateTen + " Output: " + dateTen.isValidDate());
        //Test case 11, check dob is today
        System.out.println("Testcase 11: " + dateEleven + " Output: " + dateEleven.isValidDate());
        //Test case 12, check dob is future date
        System.out.println("Testcase 12: " + dateTwelve + " Output: " + dateTwelve.isValidDate());

    }
}