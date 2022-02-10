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
        if(this == slot){
            return 0;
        } else if(this.date.compareTo(slot.date) > 0) {
            return -1;
        } else if(this.date.compareTo(slot.date) < 0) {
            return 1;
        } else if(this.date.compareTo(slot.date) == 0) {
            if(this.time.compareTo(slot.time) == 0){
                return -1;
            } else{
                return 1;
            }
        }
        return 0; // come back to ltr if doesn't work
    }
}
