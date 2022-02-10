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

    public String getLname(){
        return this.lname;
    }
    public String getFname(){
        return this.fname;
    }

    public boolean isValidDOB(){
        //check if date of birth is today or future date
        return this.dob < today;
    }

    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob;
    }
    @Override
    public int compareTo(Patient patient) {
        //last name, first name, dob
        int lnameCompare = compareName(this.lname, patient.lname);
        int fnameCompare;
        int dobCompare;
        if(lnameCompare == 0){
            fnameCompare = compareName(this.fname, patient.fname);
            if(fnameCompare == 0){
                dobCompare = this.dob.compareTo(patient.dob);
                if(dobCompare == 0){
                    return 0;
                } else {
                    if(dobCompare == 1){
                        return -1; //patient obj would go first
                    } else {
                        return 1;//this obj would go first
                    }
                }
            } else {
                if(fnameCompare > 0){
                    return -1; //patient obj goes first
                } else{
                    return 1; //this obj goes first
                }
            }
        } else {
            if(lnameCompare > 0){
                return -1; //patient obj goes first
            } else {
                return 1; //this obj goes first
            }
        }
    }
    public int compareName(String str1, String str2){
        for (int i = 0; i < str1.length() &&
                i < str2.length(); i++) {
            if ((int)str1.charAt(i) ==
                    (int)str2.charAt(i)) {
                continue;
            }
            else {
                return (int)str1.charAt(i) -
                        (int)str2.charAt(i);
            }
        }
        if (str1.length() < str2.length()) {
            return (str1.length()-str2.length());
        }
        else if (str1.length() > str2.length()) {
            return (str1.length()-str2.length());
        }
        else {
            return 0;
        }
    }
}
