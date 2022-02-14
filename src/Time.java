/**
 *
 * @author kevinarbito, katiesidebotham
 */
public class Time implements Comparable<Time> {
    private int hour;
    private int minute;
    private static final int OPENING_HOUR = 9;
    private static final int CLOSING_HOUR = 16;
    private static final int OPENING_MINUTE = 45;
    private static final int TIME_INTERVAL = 15;

    /**
     * This constructor takes in time as a string and splits it up into integers. it formats it, so it is in HH:MM time.
     * it creates the time object within this constructor.
     * @param time The time that must be formatted correctly and used to make a Time object.
     */
    public Time(String time) {
        String[] parts = time.split(":");
        int hour =  Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int check = Integer.parseInt(String.format("%02d", minute));
        this.hour = hour;
        this.minute = check;
    }

    /**
     * This method is called in order to check whether the time is valid.
     * It makes sure that the hour is within the window provided.
     * it checks that the minute is intervals of 15.
     * @return Boolean: true if hour and minute are valid, false otherwise.
     */
    public boolean isValid() {
        if((hour >= OPENING_HOUR && hour <= CLOSING_HOUR)) {
            if(minute > OPENING_MINUTE) {
                return false;
            } else return minute % TIME_INTERVAL == 0;
        } else {
            return false;
        }
    }

    /**
     * This overrides and returns the string format of Time.
     * @return String format of Time.
     */
    @Override
    public String toString() {
        return (String.format("%02d", this.hour) + ":" + String.format("%02d", this.minute));
    }

    /**
     * This method overrides and compares two times to each other. It checks whether the times are the same, and if
     * they are not it will return 1 or -1 based whether the hour is less than or greater than or the minute is less
     * than or greater than.
     * @param time Time to be compared.
     * @return -1 is less than, 1 if greater than, 0 if equal
     */
    @Override
    public int compareTo(Time time) {
        if(this.equals(time)){
            return 0;
        } else if(this.hour > time.hour){
           return 1;
        } else if(this.hour == time.hour){
           if(this.minute > time.minute){
               return 1;
           }
        } else{
            return -1;
        }
        return 0;
    }
}