/**
 * The time slot class creates and holds the contents of each time slot. The constructor takes date and time.
 * The class also contains getters for both date and time. it is constantly used to compare different time slots
 * based on the time slot itself, date, and time. It is vital to other classes such as Kiosk, appointment and schedule.
 *
 * @author kevinarbito, katiesidebotham
 */
public class Timeslot implements Comparable<Timeslot> {
    private Date date;
    private Time time;


    /**
     * Constructor for Timeslot takes in the objects date and time as parameters.
     * This is used to make the Time slot object with date and time.
     * @param date The date for timeslot
     * @param time The time for timeslot
     */
    Timeslot(Date date, Time time){
        this.date = date;
        this.time = time;
    }

    /**
     * Retrieves the data for patient from appointment
     * @return returns the patient object to where it was called from.
     */

    public Date getDate(){
        return this.date;
    }

    /**
     * Retrieves the time for patient from appointment
     * @return returns the patient object to where it was called from.
     */
    public Time getTime() {
        return this.time;
    }

    /**
     * This method overrides and returns time slot in string format MM/DD/YYYY HH:MM.
     * @return
     */

    @Override
    public String toString() { // check if correct format or not
        return this.date + " " + this.time;
    }

    /**
     * This method takes in the object slot as a parameter. It checks to see if the time slots are equal, if not, it
     * checks if the dates are equal less than or greater than. If they are equal, it will check whether the time is
     * less than or greater than.
     * @param slot The time slot that is being compared.
     * @return -1 if less than, 1 if greater than, 0 if equal to.
     */
    @Override
    public int compareTo(Timeslot slot) {
        if(this == slot) return 0;
        else if(this.date.compareTo(slot.date) > 0) return 1;
        else if(this.date.compareTo(slot.date) < 0) return -1;
        else {
            if(this.getTime().compareTo(slot.getTime()) < 0) return -1;
            else if(this.getTime().compareTo(slot.getTime()) > 0) return 1;
        }
        return 0;
    }
    /**
     * Testbed main
     * Testing cases from Test Design doc; commented numbers correspond to specific test case
     */
    public static void main(String[] args){
        Timeslot timeOne = new Timeslot();
    }
}
