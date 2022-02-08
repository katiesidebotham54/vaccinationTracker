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

    public boolean isScheduled(Appointment appt){
        //check if an appointment with the same patient, timeslot and location is already in the schedule
        if(this.patient == appt.patient && this.slot == appt.slot && this.location == appt.location){
            return true
        }
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
