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
    //Getter for today's date
    public void getToday(){
        return today;
    }
    public boolean isValid(){
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

    }
}
