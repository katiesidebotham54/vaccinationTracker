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
        int hour =  Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int check = Integer.parseInt(String.format("%02d", minute));
        this.hour = hour;
        this.minute = check;
    } //take “hh:mm” and create a Time object

    public boolean isValid() {
        if((hour >= 9 && hour <= 16)) {
            if(minute > 45) {
                return false;
            } else return minute % 15 == 0;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return (String.format("%02d", this.hour) + ":" + String.format("%02d", this.minute));
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