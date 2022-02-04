public class Time implements Comparable<Time> {
    private int hour;
    private int minute;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;


    public boolean isValid() {
        if((hour >= 9 && hour <= 16)) {
            if(hour == 16 && minute > 45) {
                return false;
            } else if(minute % 15 != 0) {
                return false;
            }
            return true;
        }
        return true;
    }

    @Override
    public String toString() {
        return time.hour + ":" + time.minute;
    }
    @Override
    public int compareTo(Time time) {

    }
}