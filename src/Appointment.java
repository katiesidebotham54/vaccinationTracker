import java.util.Calendar;

/*

authors: @katiesidebotham @kevinarbito
 */
// Done
public class Appointment {
    private Patient patient;
    private Timeslot slot;
    private Location location;

    Appointment(Patient patient, Timeslot slot, Location location){
        this.patient = patient;
        this.slot = slot;
        this.location = location;
    }

    public boolean isValidApptDate() {
        Date today;
        today = new Date();
        if (this.slot.getDate().getYear() == today.getYear() && this.slot.getDate().getMonth() == today.getMonth() && this.slot.getDate().getDay() == today.getDay()) {
            return false;
        } else if (this.slot.getDate().getYear() == today.getYear() && this.slot.getDate().getMonth() == today.getMonth() && this.slot.getDate().getDay() - 1 == today.getDay() - 1) {
            return false;
        } else return this.slot.getDate().getYear() == today.getYear();
    }
    public Patient getPatient(){
        return this.patient;
    }
    public Location getLocation(){
        return this.location;
    }
    public Timeslot getSlot(){
        return this.slot;
    }

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

    @Override
    public String toString() {
        return patient.getFname() + " " + patient.getLname() + ", DOB: " + patient.getDob() + " , Appointment detail: " + slot.getDate() + ", " + slot.getTime() + ", " + location.getCity() + " " + location.getZipCode() + ", " + location.getCounty();
    }

}
