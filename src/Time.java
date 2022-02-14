/**
 *The time class is used to make Time object. the constructyer  take in the hour and minute of the inputs and check
 * whether the time is in range and a possible time. the compareTo checks if the time is less than, greater than, or
 * equal to the time it is being compared to. Time also in clues a method that returns the time in string HH:MM format.
 * @author kevinarbito, katiesidebotham
 */
public class Time implements Comparable<Time> {
    private int hour;
    private int minute;

    /**
     * This constructor takes in time as a string and splits it up into integers. it formats it, so it is in HH:MM time.
     * it creates the time object within this constructor.
     * @param time The time that must be formatted correctly and used to make a Time object.
     */
    public Time(String time) {
        String[] timeParts = time.split(":");
        int hour =  Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        int check = Integer.parseInt(String.format("%02d", minute));
        this.hour = hour;
        this.minute = check;
    }

    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
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
        if(this.equals(time)) return 0;
        else if(this.hour > time.hour)return 1;
        else if(this.hour == time.hour){
           if(this.minute > time.minute) return 1;
        } else return -1;
        return 0;
    }
}