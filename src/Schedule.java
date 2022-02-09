/*

authors: @katiesidebotham @kevinarbito
 */
public class Schedule {

    Schedule(){

    }

    private Appointment [] appointments;
    private int numAppts;

    private int find(Appointment appt) {

    } //return the index, or NOT_FOUND
    private void grow() {
        Appointment[] temp = new Appointment[numAppts + 4];
        for (int i = 0; i < numAppts; i++){
            temp[i] = appointments[i];
        }
        appointments = temp;
    } //increase the capacity of the container by 4
    public boolean add(Appointment appt) {
        if(find(appt) == NOT_FOUND){
            return false;
        } else{
            //check if same patient doing multiple appointments, make sure appointments aren't on same day
            //if patient = appt.patient
            //
        }
        if(numAppts == appointments.length){
            grow();
        }
        appointments[numAppts] = appt;
        numAppts++;
    }

    public boolean remove(Appointment appt) {

    }
    public void print() {
        for(Appointment a: appointments){
            System.out.println(a.toString());
        }
    } //print all the appointments in current order
    public void sortByZip(){

    }
    public void printByZip() {

    } //sort by zip codes and print

    public void sortByPatient(){

    }
    public void printByPatient() {

    } //sort by patient and print }
}
