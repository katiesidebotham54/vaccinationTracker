public class Patient implements Comparable<Patient> {
    private String fname;
    private String lname;
    private Date dob;

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

    }
}
