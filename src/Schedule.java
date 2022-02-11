/*

authors: @katiesidebotham @kevinarbito
 */
public class Schedule {

    private Appointment [] appointments;
    private int numAppts;
    public final int NOT_FOUND = -1;
    public int addError;

    private int find(Appointment appt) {
        for(int i = 0; i < numAppts; i++) {
            if(appt == appointments[i]) {
                return i;
            }
        }
        return NOT_FOUND;
    } //return the index, or NOT_FOUND

    public int checkifExist(Appointment appt) {
        for(int i = 0; i < numAppts; i++) {
            if(appt == appointments[i]) {
                return i;
            }
        }
        return NOT_FOUND;
    } // return the index, or found

    private void grow() {
        Appointment[] temp = new Appointment[numAppts + 4];
        for (int i = 0; i < numAppts; i++){
            temp[i] = appointments[i];
        }
        appointments = temp;
    } //increase the capacity of the container by 4

    public boolean add(Appointment appt) {
        if(find(appt) == NOT_FOUND){
            System.out.println(appt);
//            appointments[numAppts] = appt;
//            System.out.println(appointments[numAppts]);
            numAppts++;
            return true;
        } else{
            for(Appointment a: this.appointments){
                if(a.getPatient().compareTo(appt.getPatient())== 0 && a.getLocation() == appt.getLocation()){
                    if(a.getSlot().getDate().compareTo(appt.getSlot().getDate()) == 0){
                        addError = 3;
                        return false;
                    }
                }
                if(a.getSlot().compareTo(appt.getSlot()) == 0 && a.getLocation() == appt.getLocation()){
                    addError = 1;
                    return false;
                }
                if(a.getPatient().compareTo(appt.getPatient()) == 0 && a.getSlot().getDate() == appt.getSlot().getDate() && a.getLocation() != appt.getLocation()){
                    addError = 2;
                    return false;
                }
            }
        }
        if(numAppts == appointments.length){
            grow();
        }
        return true; // return statement needed
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
        System.out.println(appointments);
        for(Appointment a: appointments){
            System.out.println(a);
        }
    } //print all the appointments in current order

    //insertion sort for appointment
    public void sortZip (Appointment[] appointments) {
        for(int i = 1; i < numAppts; i++) {
            int check = Integer.parseInt(appointments[i].getLocation().getZipCode());
            int j = i - 1;

            while(j >= 0 && Integer.parseInt(appointments[j].getLocation().getZipCode()) >= check) {
                if(Integer.parseInt(appointments[j].getLocation().getZipCode()) == check) {
                    if(Integer.parseInt(appointments[j].getSlot().toString()) < Integer.parseInt(appointments[i].getSlot().toString())){
                        continue;
                        // revise here
                    }
                }
                appointments[j + 1] = appointments[j];
                j = j - 1;
            }
            appointments[j + 1] = appointments[i];
        }
    }

    public void printByZip() {
        sortZip(appointments);
        print();
    } //sort by zip codes and print

    public void sortByPatient(){
        for(int i = 1; i < numAppts; i++){
            if(appointments[i-1].getPatient().compareTo(appointments[i].getPatient()) > 0){
                Appointment tmp = appointments[i-1];
                appointments[i-1] = appointments[i];
                appointments[i] = tmp;
            }
        }
    }
    public void printByPatient() {
        sortByPatient();
        print();
    } //sort by patient and print
}
