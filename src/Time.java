import java.text.DecimalFormat;

/*

authors: @kevinarbito @katiesidebotham
 */
// Done
public class Time implements Comparable<Time> {
    private int hour;
    private int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }
    public Time(String time) {
        String[] parts = time.split(":");
        String hour = parts[0];
        String minute = parts[1];
        Time t = new Time(Integer.parseInt(hour), Integer.parseInt(minute));
    } //take “hh:mm” and create a Time object

    public boolean isValid() {
        if((hour >= 9 && hour <= 16)) {
            if(hour == 16 && minute > 45) {
                return false;
            } else return minute % 15 == 0;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.hour + ":" + this.minute;
    }
    @Override
    public int compareTo(Time time) {
        if(this == time){
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
        return -1;
    }
}