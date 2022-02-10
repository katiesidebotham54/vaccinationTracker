import java.util.Calendar;
/*

authors: @katiesidebotham @kevinarbito
 */
//done
public class Patient implements Comparable<Patient> {
    private String fname;
    private String lname;
    private Date dob;

    Patient(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }
    // Getter
    public Date getDob() {
        return this.dob;
    }
    // Setter
    public void setDob(Date newDob) {
        this.dob = newDob;
    }

    public String getFname(){
        return this.fname;
    }

    public String getLname(){
        return this.lname;
    }


    public boolean isValidDOB(){
        //check if date of birth is today or future date
        Date today = new Date();
        if(this.dob.compareTo(today) >= 0 ){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob;
    }
    @Override
    public int compareTo(Patient patient) {
        //if last name is the same
         if(this.lname.compareTo(patient.lname) == 0){
        //check if first name is the same
            if( this.fname.compareTo(patient.fname) == 0){
                    //check if date of birth is same
                if(this.dob.compareTo(patient.dob) == 0){
                        //they are the same patient
                    return 0;
                } else {
                    if(this.dob.compareTo(patient.dob) > 0){
                        return -1; //patient is bigger
                    } else {
                        return 1;// this is bigger
                    }
                }
            } else {
                if( this.fname.compareTo(patient.fname) > 0){
                    return -1; //patient obj goes first
                } else{
                    return 1; //this obj goes first
                }
            }
        } else {
            if(this.lname.compareTo(patient.lname) > 0){
                return 1; //this last name is bigger
            } else {
                return -1; //patient last name is bigger
            }
        }
    }
}
