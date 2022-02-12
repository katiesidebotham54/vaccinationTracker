/*

authors: @kevinarbito @katiesidebotham
 */
// done
public class Timeslot implements Comparable<Timeslot> {
    private Date date;
    private Time time;

    Timeslot(Date date, Time time){
        this.date = date;
        this.time = time;
    }

    // getter
    public Date getDate(){
        return this.date;
    }

    public Time getTime() {
        return this.time;
    }

    @Override
    public String toString() { // check if correct format or not
        return this.date + " " + this.time;
    }

    @Override
    public int compareTo(Timeslot slot) {
        if(this == slot) {
            return 0;
        } else if(this.date.compareTo(slot.date) == 1) {
            return 1;
        } else if(this.date.compareTo(slot.date) == -1) {
            return -1;
        } else {
            if(this.getTime().compareTo(slot.getTime()) == -1) {
                return -1;
            } else if(this.getTime().compareTo(slot.getTime()) == 1) {
                return 1;
            }
        }
        return 0;
    }
}
