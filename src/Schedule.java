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

    public int checkifExist(Appointment appt) {
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
            System.out.println("nothing in appointments");
            appointments = new Appointment[4];
        }
        if(numAppts == appointments.length){
            System.out.println("I grew");
            grow();
        }
        if(find(appt) == NOT_FOUND){
            System.out.println("wasn't found ");
            appointments[numAppts] = appt;
            numAppts++;
            return true;
        } else {
            for(Appointment a: appointments){ // does not reach here
                System.out.println("Made it to for loop");
                //if the patient and the location are the same
                //then check if the date are the same --> patient cannot book another appointment on the same day
                if(a.getPatient().compareTo(appt.getPatient()) == 0 && a.getLocation() == appt.getLocation()){
                    if(a.getSlot().getDate().compareTo(appt.getSlot().getDate()) == 0){
                        addError = 3;
                        return false;
                    }
                }// if the timeslots and locations of two appointments are the same, timeslot is already taken
                else if(a.getPatient().compareTo(appt.getPatient()) != 0 && a.getSlot().compareTo(appt.getSlot()) == 0 && a.getLocation() == appt.getLocation()){
                    System.out.println("a.getSlot() = " + a.getSlot());
                    System.out.println("appt.getSlot() = " + appt.getSlot());
                    System.out.println("a.getLocation() = " + a.getLocation());
                    System.out.println("appt.getLocation() = " + appt.getLocation());
                    addError = 1;
                    return false;
                }
                else if(a.getPatient().compareTo(appt.getPatient()) == 0 && a.getSlot().getDate() == appt.getSlot().getDate() && a.getLocation() != appt.getLocation()){
                    System.out.println("third if");
                    System.out.println("a.getSlot() = " + a.getSlot());
                    System.out.println("appt.getSlot() = " + appt.getSlot());
                    System.out.println("a.getLocation() = " + a.getLocation());
                    System.out.println("appt.getLocation() = " + appt.getLocation());
                    addError = 2;
                    return false;
                }
            }
        }
        System.out.println("it was actually here");
        return true; // return statement needed
    }

    public boolean remove(Appointment appt) {
            System.out.println(appt);
        if(find(appt) == NOT_FOUND){
            return false;
        } else{
            for(int i = find(appt); i < appointments.length; i++){
                appointments[find(appt)] = appointments[find(appt) +  1];
            }
            numAppts--;
            System.out.println("NumAppts: " + numAppts);

            return true;
        }
    }

    public void print() {
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
