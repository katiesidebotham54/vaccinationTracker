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

    public boolean isValidApptDate(){
        Date today = new Date();
        //checks if appt date is today, day before, or date beyond this year
        if(this.slot.getDate() >= today || this.slot.getDate() > today.add(Calendar.DATE, -1) || this.slot.getDate().getYear() > today.get(Calendar.YEAR)){
            return false;
        }
        return true;
    }

    public boolean isValidLocation(){
        //check if the location with the county name is a valid location
        for(Location l : Location.values()){
            if(this.location.getCounty() == l.getCounty()){
                return true;
            }
        }
        return false;
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
        if(obj == this){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return patient.getFname() + " " + patient.getLname() + ", DOB: " + patient.getDob() + " , Appointment detail: " + slot.getDate() + ", " + slot.time + ", " + location.getCity() + " " + location.getZipCode() + ", " + location.getCounty();
    }
}
