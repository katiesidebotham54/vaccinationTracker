/*

authors: @katiesidebotham @kevinarbito
 */
public class Schedule {

    private Appointment [] appointments;
    private int numAppts;
    public final int NOT_FOUND = -1;
    public int addError;

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
                if(a.getPatient().compareTo(appt.getPatient())== 0 && a.getLocation() == appt.getLocation()){
                    if(a.getSlot().getDate().compareTo(appt.getSlot().getDate()) == 0){
                        addError = 0;
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
        appointments[numAppts] = appt;
        numAppts++;
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
        for(Appointment a: appointments){
            System.out.println(a.toString());
        }
    } //print all the appointments in current order


    //insertion sort for appointment
    public void sortZip (Appointment appointments[]) {
        for(int i = 1; i < numAppts; i++) {
            int check = Integer.parseInt(appointments[i].getLocation().getZipCode());
            int j = i - 1;

            while(j >= 0 && Integer.parseInt(appointments[j].getLocation().getZipCode()) > check) {
                appointments[j + 1] = appointments[j];
                j = j - 1;
            }
            appointments[j + 1] = appointments[i]; // am i wrong here?
        }

        /*
        // get the order of the zip codes locations, and print the ones with the highest Zip codes. UNION, MORRIS, MERCER, SOMERSET, MIDDLESEX
        for(int i = 0; i < numAppts; i++){
            if(appointments[i].getLocation().getZipCode().equals(county.getZipCode())) { // could also just check the location tbh
                System.out.println(appointments[i].toString());
            }
        }

         */
    }

    public void printByZip() {
        sortZip(appointments);
        print();
        /*
        printZip(Location.UNION);
        printZip(Location.MORRIS);
        printZip(Location.MERCER);
        printZip(Location.SOMERSET);
        printZip(Location.MIDDLESEX);

         */
    } //sort by zip codes and print

    public void sortByPatient(){
        for(int i = 1; i < numAppts; i++){
            if(appointments[i-1].getPatient().compareTo(appointments[i].getPatient()) == 0){
                int tmp = appointments[i-1];
                appointments[i-1] = appointments[i];
                appointments[i] = tmp;
            }
        }
    }
    public void printByPatient() {
        sortByPatient();
    } //sort by patient and print
}
