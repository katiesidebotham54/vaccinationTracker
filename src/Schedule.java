/*

authors: @katiesidebotham @kevinarbito
 */
public class Schedule {

    private Appointment [] appointments;
    private int numAppts;
    public final int NOT_FOUND = -1;

    private int find(Appointment appt) {
        for(int i = 0; i < appointments.length; i++) {
            if(appt == appointments[i]) {
                return i;
            } else {
                return NOT_FOUND;
            }
        }
        return NOT_FOUND;
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
            for(Appointment a: appointments){
                if(a.getPatient() == appt.getPatient() && a.getLocation() == appt.getLocation()){
                    if(a.getSlot().getDate().getDay() == appt.getSlot().getDate().getDay()){
                        return false;
                    }
                }
                if(a.getSlot() == appt.getSlot() && a.getLocation() == appt.getLocation()){
                    return false;
                }
                if(a.getPatient() == appt.getPatient() && a.getSlot().getDate() == appt.getSlot().getDate() && a.getLocation() != appt.getLocation()){

                }

            }
        }
        if(numAppts == appointments.length){
            grow();
        }
        appointments[numAppts] = appt;
        numAppts++;
        return true; // retunr statement needed
    }

    public boolean remove(Appointment appt) {
        if(find(appt) == NOT_FOUND){
            return false;
        } else{
            appointments[find(appt)] = appointments[find(appt) + 1];
            numAppts--;
            return true;
        }
    }

    public void print() {
        for(Appointment a: appointments){
            System.out.println(a.toString());
        }
    } //print all the appointments in current order
    public void sortByZip(){

    }
    public void printByZip() {
        for(int i = 0; i < numAppts; i++){
            for(int j = i + 1; j < numAppts; j++){
                if(appointments[i].location.getZipCode() > appointments[j].location.getZipCode()){
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    } //sort by zip codes and print

    public void sortByPatient(){

    }
    public void printByPatient() {

    } //sort by patient and print
}
