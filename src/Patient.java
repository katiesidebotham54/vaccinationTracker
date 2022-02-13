/*

authors: @katiesidebotham @kevinarbito
 */
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
        if(this.dob.compareTo(today) >= 0){
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
        if(this.lname.compareTo(patient.lname) == 0){
            if(this.fname.compareTo(patient.fname) == 0){
                if(this.dob.compareTo(patient.dob) == 0)  return 0;
                else if(this.dob.compareTo(patient.dob) > 0) return 1;
                else return -1;
            } else if(this.fname.compareTo(patient.fname) > 0) return 1;
            else return -1;
        } else if(this.lname.compareTo(patient.lname) > 0) return 1;
        else return -1;
    }
}
