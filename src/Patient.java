/**
 * The patient class is responsible for holding all information regarding the patient. Patient contains a constructor
 * for the object that holds the contents of the object -> first name, last name, and date of birth. It includes 3
 * getters for retrieving information from the patient object. This class also checks if date of birth is valid,
 * and checks whether a patient is less than, greater than, or equal to another patient based off of alphabetical last,
 * & first name as numerically by date of birth.
authors: @kevinarbitodelgado, @katherinesidebotham
 */
public class Patient implements Comparable<Patient> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * This constructor holds the information of each patient. It holds the first name, last name, and date of birth
     * (dob). This is made for easy access to what is within the object. It can be referenced within this class using
     * this and this. for specific.
     * @param fname First name of patient
     * @param lname Last name of patient
     * @param dob Date of birth of patient
     */
    Patient(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Retrieves the date of birth for patient from Patient
     * @return returns the patient object to where it was called from.
     */
    public Date getDob() {
        return this.dob;
    }

    /**
     * Retrieves the first name for patient from Patient
     * @return returns the patient object to where it was called from.
     */
    public String getFname(){
        return this.fname;
    }

    /**
     * Retrieves the last name for patient from Patient
     * @return returns the patient object to where it was called from.
     */
    public String getLname(){
        return this.lname;
    }

    /**
     * This method is called in order to check whether the date of birth of a specific patient is today or
     * a future date. If it is, it will return a boolean from where it was called.
     * @return Boolean: true if date of birth is not today or future date, false otherwise.
     */
    public boolean isValidDOB(){
        //check if date of birth is today or future date
        Date today = new Date();
        return this.dob.compareTo(today) < 0;
    }

    /**
     * This is called in order to return the information of the patient as a string.
     * @return String format of patient information.
     */
    @Override
    public String toString() {
        return this.fname + " " + this.lname + " " + this.dob;
    }

    /**
     * This method takes in the parameter patient and compares the patients name in order to see whether the name
     * is the same, greater than, or less than another patient alphabetically.
     * It compares whether the date of birth is the same, greater than, or less than another patient and returns an
     * integer value based on the condition that was met.
     * @param patient information of the patient that is to be compared.
     * @return integer value based on whether one of the conditions are met, (-1 = less, 0 = equals, 1 = greater)
     */
    @Override
    public int compareTo(Patient patient) {
        if(this.lname.compareTo(patient.lname) == 0){
            if(this.fname.compareTo(patient.fname) == 0){
                return Integer.compare(this.dob.compareTo(patient.dob), 0);
            } else if(this.fname.compareTo(patient.fname) > 0) return 1;
            else return -1;
        } else if(this.lname.compareTo(patient.lname) > 0) return 1;
        else return -1;
    }
}
