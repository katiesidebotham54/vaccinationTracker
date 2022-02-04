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
    public String getDob() {
        return patient.dob;
    }

    // Setter
    public void setDob(newDob) {
        this.dob = newDob;
    }

    @Override
    public String toString() {
        return patient.fname + " " + patient.lname + " " + patient.dob;
    }
    @Override
    public int compareTo(Patient patient) {
        //last name, first name, dob
        int lnameCompare = compareString(this.lname, patient.lname);
        int fnameCompare;
        int dobCompare;
        if(lnameCompare == 0){
            fnameCompare = compareString(this.fname, patient.fname);
            if(fnameCompare == 0){
                dobCompare = this.dob.compareTo(patient.dob);
                if(dobCompare == 0){
                    //it's the same person -- it's a match (ref find() method)
                } else {
                    if(dobCompare == 1){
                        //patient obj would go first
                    } else {
                        //this obj would go first
                    }
                }
            } else {
                if(fnameCompare > 0){
                    //patient obj goes first
                } else{
                    //this obj goes first
                }
            }
        } else {
            if(lnameCompare > 0){
                //patient obj goes first
            } else {
                //this obj goes first
            }
        }
    }
    public int compareString(String str1, String str2){
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
