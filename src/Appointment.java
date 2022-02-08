/*

authors: @katiesidebotham @kevinarbito
 */
public class Appointment {
    private Patient patient;
    private Timeslot slot;
    private Location location;

    Appointment(Patient patient, Timeslot slot, Location location){
        this.patient = patient;
        this.slot = slot;
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return patient.fname + " " + patient.lname + ", DOB: " + patient.dob + " , Appointment detail: " + slot.date + ", " + slot.time + ", ";
    }
}
