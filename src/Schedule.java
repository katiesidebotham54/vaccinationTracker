/*

authors: @katiesidebotham @kevinarbito
 */
public class Schedule {

    private Appointment [] appointments;
    private int numAppts;
    public final int NOT_FOUND = -1;
    public int addError;

    private int find(Appointment appt) {
        for(int i = 0; i < numAppts; i++){
            if(appointments[i].equals(appt)){
                return i;
            }
        }
        return NOT_FOUND;
    } //return the index, or NOT_FOUND

    public boolean checkIfEmpty(){
        return numAppts==0;
    }

    public int checkIfExist(Appointment appt) {
        for(int i = 0; i < numAppts; i++){
            if(appointments[i].equals(appt)){
                return i;
            }
        }
        return NOT_FOUND;
    } // return the index, or found
    private void grow() {
        Appointment[] newArr = new Appointment[numAppts + 4];//Creating a new array with space for an extra element
        for(int i = 0; i < numAppts; i++)
        {
            newArr[i] = appointments[i];//Copying the elements to the new array
        }
        appointments = newArr;
    } //increase the capacity of the container by 4

    public boolean add(Appointment appt) {
        if(appointments == null) {
            appointments = new Appointment[4];
            appointments[numAppts] = appt;
            numAppts++;
            return true;
        }
        if(numAppts == appointments.length){
            grow();
        }
        if(find(appt) == NOT_FOUND){
            for(int i = 0; i < numAppts; i++){
                if(appointments[i].getPatient().compareTo(appt.getPatient()) != 0 && appointments[i].getSlot().compareTo(appt.getSlot()) == 0 && appointments[i].getLocation() == appt.getLocation()){
                    addError = 1;
                    return false;
                }
                else if(appointments[i].getPatient().compareTo(appt.getPatient()) == 0 && appointments[i].getSlot().compareTo(appt.getSlot()) == 0 && appointments[i].getLocation() != appt.getLocation()){
                    addError = 2;
                    return false;
                }

            }
        }
        appointments[numAppts] = appt;
        numAppts++;
        return true;
    }

    public boolean remove(Appointment appt) {
        if(find(appt) == NOT_FOUND){
            return false;
        } else{
            for(int i = find(appt); i < numAppts; i++){
                appointments[find(appt)] = appointments[find(appt) + 1];
            }
            numAppts--;
            return true;
        }
    }

    public boolean removeByPatient(){

    }

    public void print() {
        for(int i = 0; i < numAppts; i++){
            System.out.println(appointments[i]);
        }
    } //print all the appointments in current order

    public void sortZip (Appointment[] appointments) {
        System.out.println("Entering sortZip method");
        for(int i = 1; i < numAppts; i++) {
            int current = Integer.parseInt(appointments[i].getLocation().getZipCode());
            int next = i - 1;
            while(next >= 0 && Integer.parseInt(appointments[next].getLocation().getZipCode()) > current) {
                appointments[next + 1] = appointments[next];
            }
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
