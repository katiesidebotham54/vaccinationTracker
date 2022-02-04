public class Time implements Comparable<Time> {
    private int hour;
    private int minute;


    public boolean isValid() {
        if((hour >= 9 && hour <= 16)) {
            if(hour == 16 && minute > 45) {
                return false;
            }
            return true;
        }
    }
    @Override
    public String toString() {
        return time.hour + ":" + time.minute;
    }
    @Override
    public int compareTo(Time time) {

    }
}