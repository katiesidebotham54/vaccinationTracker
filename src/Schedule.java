/**
 *The schedule class handles everything when a command is out through the console within the kiosk.
 * It is responsible for holding an array of appointments. Within this class, the appointment can be searched for
 * by matching patients, or the actual appointment. It is also responsible for resizing the appointment array when
 * it reaches capacity, adding and deleting appointments based on patient, or literal appointment. It is also
 * responsible for sorting by zip code & time slot as well as by patient. all printing commands are executed within
 * this class as well.
 * @author @kevinarbitodelgado, @katherinesidebotham
 */
public class Schedule {

    private Appointment [] appointments;
    private int NUM_APPTS;
    public final int NOT_FOUND = -1;
    public int ADD_ERROR;
    public final int SAME_TIMESLOT = 1;
    public final int SAME_PATIENT = 2;


    /**
     * This method is used to traverse the entire array of appointments until the specific appointment that is being
     * searched for is found. When found, it will return the index of the located appointment; if it is not found, it
     * will return NOT_FOUND.
     * @param appt the information for a specific appointment
     * @return index of where the appointment is found in the array if found, otherwise NOT_FOUND
     */
    private int find(Appointment appt) {
        for(int i = 0; i < NUM_APPTS; i++){
            if(appointments[i].equals(appt)){
                return i;
            }
        }
        return NOT_FOUND;
    } //return the index, or NOT_FOUND

    public Appointment[] samePatientArray(Appointment appt){
        Appointment[] patientArr = new Appointment[NUM_APPTS];
        int j = 0;
        for(int i = 0; i < NUM_APPTS; i++){
            if(appointments[i].getPatient().compareTo(appt.getPatient()) == 0){
                patientArr[j] = appointments[i];
                j++;
            }
        }
        return patientArr;
    }

    /**
     * This method checks whether the number of appointments is 0; if it is 0, it will return true, otherwise
     * it will return false.
     * @return Boolean: true if number of appointments is 0, false if not.
     */
    public boolean noAppointments(){
        return NUM_APPTS == 0;
    }
    /**
     * This method takes in appt as the parameter. It will traverse through the numbers of appointments until the
     * information of entered appointment (appt) matches one in the appointments array.
     * @param appt the information for a specific appointment
     * @return Index of appointment if found, NOT_FOUND otherwise
     */
    public int appointmentExists(Appointment appt) {
        for(int i = 0; i < NUM_APPTS; i++){
            if(appointments[i].equals(appt)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This method is called when the number of appointments equals the length of the appointment array. This will
     * create a new appointments array with a size of +4 in comparison to the original. It will copy over the data
     * and make the new array the original array.
     */
    private void grow() {
        Appointment[] newArray = new Appointment[NUM_APPTS + 4];
        for(int i = 0; i < NUM_APPTS; i++)
        {
            newArray[i] = appointments[i];
        }
        appointments = newArray;
    }

    /**
     * This method is called when it is time to add a new appointment, and it has gone through all the checks to
     * make sure that it can be added. It takes in appt in the parameter which is the appt that must be
     * added to the schedule. It will check whether it is null; if so, it will allocate space and put the new appointment
     * inside the array. If the appointment length equals the number of appointments (numAppts) it will call grow() and
     * the size will increase by 4. if the appointment does not already exist in the schedule, it will check whether
     * the slot and the location are the same, if so, it will make the ADDERROR 1 and return false. It will also
     * check whether the patient has already scheduled an appointment for that day at a different location, if so, it
     * will make ADDERROR 2 and return false. otherwise, it will add the appointment and increase the number of
     * appointments (numAppts) by 1 and return true.
     * @param appt the information for a specific appointment to add
     * @return true if added successfully, false if there was an error and could not be added otherwise
     */
    public boolean add(Appointment appt) {
        if(appointments == null) {
            appointments = new Appointment[4];
            appointments[NUM_APPTS] = appt;
            NUM_APPTS++;
            return true;
        }
        if(NUM_APPTS == appointments.length){
            grow();
        }
        if(find(appt) == NOT_FOUND){
            for(int i = 0; i < NUM_APPTS; i++){
                if(appointments[i].getPatient().compareTo(appt.getPatient()) != 0
                        && appointments[i].getSlot().compareTo(appt.getSlot()) == 0
                        && appointments[i].getLocation() == appt.getLocation()){
                    ADD_ERROR = SAME_TIMESLOT;
                    return false;
                }
                else if(appointments[i].getPatient().compareTo(appt.getPatient()) == 0
                        && appointments[i].getSlot().compareTo(appt.getSlot()) == 0
                        && appointments[i].getLocation() != appt.getLocation()){
                    ADD_ERROR = SAME_PATIENT;
                    return false;
                }

            }
        }
        appointments[NUM_APPTS] = appt;
        NUM_APPTS++;
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
            for(int i = find(appt); i < NUM_APPTS; i++){
                appointments[find(appt)] = appointments[find(appt) + 1];
            }
            NUM_APPTS--;
            return true;
        }
    }
    public void removePatient(Appointment appt){
        int foundPatient = 0;
        for(int i = 0; i < NUM_APPTS; i++){
            if(appointments[i].equals(appt)){
                foundPatient = i;
                break;
            }
        }
        for(int i = foundPatient; i < NUM_APPTS; i++){
            appointments[i] = appointments[i + 1];
        }
        NUM_APPTS--;
    }

    /**
     * When this method is called, it prints the schedule in the order it is in.
     */
    public void print() {
        for(int i = 0; i < NUM_APPTS; i++){
            System.out.println(appointments[i]);
        }
    }

    /**
     * When this method is called, it sorts the appointments by zip code and timeslot then calls print() in order
     * to print the schedule after it has been sorted.
     */
    public void printByZip() {
        for (int i = 0; i < NUM_APPTS; i++)
        {
            for (int j = i + 1; j < NUM_APPTS; j++)
            {
                Appointment tempApptByZip;
                if (Integer.parseInt(appointments[i].getLocation().getZipCode()) >
                        Integer.parseInt(appointments[j].getLocation().getZipCode()))
                {
                    tempApptByZip = appointments[i];
                    appointments[i] = appointments[j];
                    appointments[j] = tempApptByZip;
                } else if(Integer.parseInt(appointments[i].getLocation().getZipCode()) ==
                        Integer.parseInt(appointments[j].getLocation().getZipCode())){
                    if(appointments[i].getSlot().compareTo(appointments[j].getSlot()) > 0){
                        tempApptByZip = appointments[i];
                        appointments[i] = appointments[j];
                        appointments[j] = tempApptByZip;
                    }
                }
            }
        }
        print();
    }

    /**
     * This method is called when it is time to print by patient order. It sorts the patients by last name, first name,
     * and dob. This insertion sort sorts the patients in place without creating an additional array. Once the sort is
     * complete, print() is called in order to print the schedule.
     */
    public void printByPatient() {
        for(int i = 1; i < NUM_APPTS; i++) {
            Appointment tempApptByPatient = appointments[i];
            int j = i -1;
            while(j >= 0 && appointments[j].getPatient().compareTo(tempApptByPatient.getPatient()) > 0) {
                appointments[j + 1] = appointments[j];
                j--;
            }
            appointments[j + 1] = tempApptByPatient;
        }
        print();
    }
}
