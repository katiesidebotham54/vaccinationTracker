/**
 *
 * @author kevinarbito, katiesidebotham
 */
public class Schedule {

    private Appointment [] appointments;
    private int numAppts;
    public final int NOT_FOUND = -1;
    public int addError;

    /**
     * This method is used to traverse the entire array of appointments until the specific appointment that is being looked for
     * is found. when it is found, it will return the index of where it was or if it is not, it will return NOT_FOUND.
     * @param appt the information for a specific appointment
     * @return index of where the appointment is found in the array if found, otherwise NOT_FOUND
     */
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

    /**
     * This method checks whether the number of appointments is 0, if it is 0, it will return true, otherwise
     * it will return false.
     * @return Boolean: true if number of appointments is 0, false if not.
     */
    public boolean checkNumAppts(){
        return numAppts==0;
    }
    public int getNumAppts(){
        return numAppts;
    }

    /**
     * This method takes in appt as the parameter. It will traverse through the numbers of appointments until the
     * information appointments array matches the information tha was provided in appt.
     * @param appt the information for a specific appointment
     * @return Index of appointment if found, NOT_FOUND otherwise
     */
    public int checkIfExist(Appointment appt) {
        for(int i = 0; i < numAppts; i++){
            if(appointments[i].equals(appt)){
                return i;
            }
        }
        return NOT_FOUND;
    } // return the index, or found

    /**
     * This method is called when the number of appointments equals the length of the appointment array. This will
     * create a new appointments array but with a size of +4 in comparison to the original. it will copy over the data
     * and make the new array the original array.
     */
    private void grow() {
        Appointment[] newArr = new Appointment[numAppts + 4];//Creating a new array with space for an extra element
        for(int i = 0; i < numAppts; i++)
        {
            newArr[i] = appointments[i];//Copying the elements to the new array
        }
        appointments = newArr;
    } //increase the capacity of the container by 4

    /**
     * This method is called when it is time to add a new appointment, and it has gone through all the checks to
     * make sure that it can actually be added. It takes in appt in the parameter which has the appt that has to be
     * added to the schedule. It will check whether it is null, if so, it'll allocate space and put the new appointment
     * inside the array. If the appointment length equals the number of appointments (numAppts) it will call grow() and
     * the size will increase by 4. if the appointment does not already exist in the schedule, it will check whether
     * the slot and the location are the same, if so, it will make the addError 1 and return false. It will also
     * check whether the patient has already scheduled an appointment for that day at a different location, if so, it
     * will make addError 2 and return false. otherwise, it will add the appointment and increase the number of
     * appointments (numAppts) by 1 and return true.
     * @param appt the information for a specific appointment to add
     * @return true if added successfully, false if there was an error and could not be added otherwise
     */
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

    /**
     * This method is called when it is time to remove an appointment from the schedule. It takes in the parameters
     * appt in order to look for the specific appointment, so it can be deleted. The method checks whether the
     * appointment (appt) actually exist, if it does not it will return false, if it does, the appointment information
     * will shift and ultimately overwrite the information that was to be deleted, resulting in deletion.
     * @param appt the information for a specific appointment to remove
     * @return true if appointment was remove, false otherwise.
     */
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

    public Appointment[] getAppt(){
        return appointments;
    }

    /**
     *  has yet to be implemented
     * @param appt the information for a specific patient within the appt
     * @return
     */
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

    /**
     * When this method is called, it prints the schedule in the order it is in.
     */
    public void print() {
        for(int i = 0; i < numAppts; i++){
            System.out.println(appointments[i]);
        }
    }

    /**
     * when this method is called, it sorts the appointments by zip code and timeslot then calls print() in order
     * to print the schedule after it has been sorted.
     */
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
    }

    /**
     * This method is called when it is time to print by patient order. It sorts the patients by last name, first name,
     * and dob. This insertion sort, sorts the patients in place without creating an additional array. Once the sort is
     * complete, print() is called in order to print the schedule.
     */
    public void printByPatient() {
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
    }
}
