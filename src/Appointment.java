
/**
 * @author kevinarbito, katiesidebotham
 */
public class Appointment {
    private Patient patient;
    private Timeslot slot;
    private Location location;

    /**
     * Constructor that takes in 3 object within the parameters (patient, slot, location).
     * This makes a appointment object using what is passed through the parameters.
     * @param patient patient information for the appointment
     * @param slot time slot information for the appointment
     * @param location location information for the appointment
     */
    Appointment(Patient patient, Timeslot slot, Location location){
        this.patient = patient;
        this.slot = slot;
        this.location = location;
    }

    /**
     * constructor used for making an appointment using a patient
     * @param patient patient information for the appointment
     */
    Appointment(Patient patient){
        this.patient = patient;
    }

    /**
     * This method checks whether the appointment date is valid. It checks to make sure that the date is not
     * today, yesterday or not this year.
     * @return true if the appointment date is valid, false otherwise.
     */
    public boolean isValidApptDate() {
        Date today = new Date();
        if (this.slot.getDate().getYear() == today.getYear() && this.slot.getDate().getMonth() == today.getMonth() && this.slot.getDate().getDay() == today.getDay()) {
            return false;
        } else if (this.slot.getDate().getYear() == today.getYear() && this.slot.getDate().getMonth() == today.getMonth() && this.slot.getDate().getDay() - 1 == today.getDay() - 1) {
            return false;
        } else return this.slot.getDate().getYear() == today.getYear();
    }

    /**
     * Retrieves the data for patient from appointment
     * @return returns the patient object to where it was called from.
     */
    public Patient getPatient(){
        return this.patient;
    }

    /**
     * Retrieves the location from appointment
     * @return returns the location object to where it was called from.
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     * Retrieves the slot information from appointment
     * @return returns the location object to where it was called from.
     */
    public Timeslot getSlot(){
        return this.slot;
    }

    /**
     * Overrides equals in order to check whether appointments are the same.
     * @param obj Appointment that is being compared.
     * @return Boolean: true if they are the same appointment, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Appointment appt = (Appointment) obj;
        return this.patient.getFname().equals(appt.patient.getFname())
                && this.patient.getLname().equals(appt.patient.getLname())
                && this.patient.getDob().toString().equals(appt.patient.getDob().toString())
                && this.slot.getDate().toString().equals(appt.slot.getDate().toString())
                && this.slot.getTime().toString().equals(appt.slot.getTime().toString())
                && this.location.equals(appt.location);
    }

    /**
     * Overrides toString and returns the Appointment in string format
     * @return Appointment in string format
     */
    @Override
    public String toString() {
        return patient.getFname() + " " + patient.getLname() + ", DOB: " + patient.getDob() + ", Appointment detail: " + slot.getDate() + ", " + slot.getTime() + ", " + location.getCity() + " " + location.getZipCode() + ", " + location.getCounty();
    }

}
