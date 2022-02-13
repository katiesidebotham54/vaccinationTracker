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

    public int findPatient(Appointment appt){
        for(int i = 0; i < numAppts; i++){
            if(appointments[i].getPatient().compareTo(appt.getPatient()) == 0){
                return i;
            }
        }
        return NOT_FOUND;
    }
    public boolean checkNumAppts(){
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

    public boolean removeByPatient(Appointment appt){
        if(findPatient(appt) == NOT_FOUND){
            return false;
        } else{
            for(int i = findPatient(appt); i < numAppts; i++){
                appointments[findPatient(appt)] = appointments[findPatient(appt) + 1];
            }
            numAppts--;
            return true;
        }
    }

    public void print() {
        for(int i = 0; i < numAppts; i++){
            System.out.println(appointments[i]);
        }
    } //print all the appointments in current order


    public void printByZip() {
        for (int i = 0; i < numAppts; i++)
        {
            for (int j = i + 1; j < numAppts; j++)
            {
                Appointment tmp;
                if (Integer.parseInt(appointments[i].getLocation().getZipCode()) > Integer.parseInt(appointments[j].getLocation().getZipCode()))
                {
                    tmp = appointments[i];
                    appointments[i] = appointments[j];
                    appointments[j] = tmp;
                } else if(Integer.parseInt(appointments[i].getLocation().getZipCode()) == Integer.parseInt(appointments[j].getLocation().getZipCode())){
                    if(appointments[i].getSlot().compareTo(appointments[j].getSlot()) > 0){
                        tmp = appointments[i];
                        appointments[i] = appointments[j];
                        appointments[j] = tmp;
                    }
                }
            }
        }
        print();
    } //sort by zip codes and print

    public void printByPatient() {
        //System.out.println("HERE");
        for(int i = 1; i < numAppts; i++) {
            Appointment temp = appointments[i];
            int j = i -1;
            while(j >= 0 && appointments[j].getPatient().compareTo(temp.getPatient()) > 0) {
                appointments[j + 1] = appointments[j];
                j--;
            }
            appointments[j + 1] = temp;
        }
        print();
    } //sort by patient and print
}
