public class Patient implements Comparable<Patient> {
    private String fname;
    private String lname;
    private Date dob;


    @Override
    public String toString() {
        return patient.fname + " " + patient.lname + " " + patient.dob;
    }
    @Override
    public int compareTo(Patient patient) {

    }
}
