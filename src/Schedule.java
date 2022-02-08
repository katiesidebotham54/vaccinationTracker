/*

authors: @katiesidebotham @kevinarbito
 */
public class Schedule {

    Schedule(Patient patient, Date date, Time time, Location location){
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    private Appointment [] appointments;
    private int numAppts;
    private int find(Appointment appt) {

    } //return the index, or NOT_FOUND
    private void grow() {

    } //increase the capacity of the container by 4
    public boolean add(Appointment appt) {
        if(numAppts == appointments.length){
            grow();
        }
        //if same patient doing multiple appointments, make sure appointments aren't on same day

        numAppts++;
    }
    public boolean remove(Appointment appt) {

    }
    public void print() {

    } //print all the appointments in current order
    public void printByZip() {

    } //sort by zip codes and print
    public void printByPatient() {

    } //sort by patient and print }
}
