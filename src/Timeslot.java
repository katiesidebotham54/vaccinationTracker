import java.util.Calendar;

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
    //Getter for today's date
    public void getToday(){
        return today;
    }
     */
    public boolean isValid() {
        //check if appointment date is today or date before today, or date beyond this year

        if(this.date >= today || this.date > today-1 || this.date.getYear()){
            return false;
        }
    }
    @Override
    public String toString() { // check if correct format or not
        return timeslot.date + " " + timeslot.time;
    }
    @Override
    public int compareTo(Timeslot slot) {
        if(this == slot){
            return 0;
        } else if(this.date.compareTo(slot.date) == 1) {
            return -1;
        } else if(this.date.compareTo(slot.date) == -1) {
            return 1;
        } else if(this.date.compareTo(slot.date) == 0){
            if(this.time.compareTo(slot.time) == 0){
                return -1;
            } else{
                return 1;
            }
        }
        return 0; // come back to ltr if doesn't work
    }
}
