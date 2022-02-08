/*

authors: @kevinarbito @katiesidebotham
 */
public class Timeslot implements Comparable<Timeslot> {
    private Date date;
    private Time time;

    Timeslot(Date date, Time time){
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() { // check if correct format or not
        return timeslot.date + " " + timeslot.time;
    }
    @Override
    public int compareTo(Timeslot slot) {

    }
}
