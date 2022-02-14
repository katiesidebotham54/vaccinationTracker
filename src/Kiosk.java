import java.util.Scanner;

/**
 *
 * @author kevinarbito, katiesidebotham
 */
public class Kiosk {
    public static final int MIN_INPUT_LENGTH = 1;
    public static final int CP_MIN_LENGTH = 4;
    public static final int SAME_APPT = -1;
    public void run() {
        System.out.print("Kiosk running. Ready to process transactions.");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        Schedule schedule = new Schedule();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] tokens = line.split(" ");
            if(tokens[0].equals("Q")){
                System.out.println("Kiosk session ended.");
                break;
            }
            handleCommand(tokens, schedule);
        }
        sc.close();
    }
    /**
     * Handles commands that are read in through the console and displays the output in the console.
     * The commands are case-sensitive in which the onl valid commands are the following:
     * "B" to book an appointment and add that appointment to the schedule.
     * "C" to cancel an appointment and remove it from the schedule.
     * "CP" to cancel all appointments for a specific patient and to remove them from the schedule.
     * "P" to display all appointments from the schedule to the console in the order they are in.
     * "PP" to display all appointments in order, sorted by patient, Sequence - Last Name, First Name, DOB.
     * "PZ" to display all appointments sorted by zip code. If multiple appointments have the same zip code,
     * they are then sorted by timeslot.
     * "Q" to quit kiosk.
     * All cases are accounted for (empty schedule, non-existent patient).
     *  Any other command will print "Invalid Command".
     *  A switch is used in order to keep it neat and to execute the correct commands.
     * @param tokens the input commands along with the appointment information are stored in this array
     * @param schedule The schedule object that holds all appointments
     */
    public void handleCommand(String[] tokens, Schedule schedule) {
        switch (tokens[0]) {
            case "P" -> {
                if(isEmptySchedule(tokens, schedule)){
                    System.out.println("Invalid command");
                } else{
                    System.out.println();
                    System.out.println("*list of appointments in the schedule*");
                    schedule.print();
                    System.out.println("*end of schedule*");
                    System.out.println();
                }
            }
            case "PZ" -> {
                System.out.println();
                System.out.println("*list of appointments by zip and time slot.");
                schedule.printByZip();
                System.out.println("*end of schedule.");
                System.out.println();
            }
            case "PP" -> {
                System.out.println();
                System.out.println("*list of appointments by patient.");
                schedule.printByPatient();
                System.out.println("*end of list");
                System.out.println();
            }
            case "C" -> cancelAppointment(schedule, tokens);
            case "CP" -> {
                if(isEmptySchedule(tokens, schedule) || tokens.length < CP_MIN_LENGTH)
                    System.out.println("Invalid command");
                else cancelByPatient(schedule, tokens);
            }
            case "B" -> bookAppointment(schedule, tokens);
            default -> System.out.println("Invalid Command");
        }
    }

    /**
     * This executes the command "B" in order to book an appointment.
     * This method will always check whether the location, dates that were entered are valid and
     * whether the schedule is not empty, in order to prevent errors.
     * Inside the body of this method, it creates objects, dob, appointmentDate, appointmentTime, slot, location,
     * and appt to be passed through the parameters of others to create objects and to execute the add-in schedule.
     * if schedule.add() is successful, it returns that it was added, if it is not, it will execute the error message
     * in checkAddError().
     * @param schedule The schedule object that holds all appointments
     * @param tokens the command and appointment information are stored in tokens in order to create objects and to
     * execute other methods
     */
    public void bookAppointment(Schedule schedule, String[] tokens) {
        if(isValidLocation(tokens) && !isEmptySchedule(tokens, schedule)) {
            schedule.ADD_ERROR = 0;
            if(isValidDate(tokens) && isValidAdd(tokens, schedule)) {
                Date dob = new Date(tokens[1]);
                Date apptDate = new Date(tokens[4]);
                Patient patient = new Patient(tokens[2], tokens[3],dob);
                Time apptTime = new Time(tokens[5]);
                Timeslot slot = new Timeslot(apptDate, apptTime);
                Location location = Location.valueOf(tokens[6].toUpperCase());
                Appointment appt = new Appointment(patient, slot, location);
                if(schedule.add(appt)) {
                    System.out.println("Appointment booked and added to the schedule.");
                } else {
                    isValidAdd(tokens, schedule);
                }
            }
        }
    }

    /**
     * This is executed if the command was "CP" in order to cancel all appointments for a specific patient
     * (First name, Last name, DOB)
     * This method will create the dob, patient, and appt objects and pass the appt through the
     * schedule.removeByPatient in order to remove all of the appointments that correspond to the specific patient
     * that was put in through the console. When schedule.removeByPatient is called, it will immediately display
     * whether all appointments were cancelled or whether the appoinment of a specific patient does not exist
     * based on returning boolean value (true or false)
     * @param schedule The schedule object that holds all appointments
     * @param tokens the command and appointment information are stored in tokens in order to create objects and to
     * execute other methods
     */
    public void cancelByPatient(Schedule schedule, String[] tokens){
        Date dob = new Date(tokens[1]);
        Patient patient = new Patient(tokens[2], tokens[3], dob);
        Appointment appt = new Appointment(patient);
        Appointment[] patientArr = schedule.samePatientArray(appt);
        for (Appointment appointment : patientArr) {
            if (appointment != null) {
                schedule.removePatient(appointment);
            }
        }
        System.out.println("All Appointments for " + patient.getFname() + " " + patient.getLname() + " DOB: "
                + patient.getDob() + " have been cancelled.");
    }

    /**
     * This is executed if the command was "C" in order to cancel a specific appointment.
     * This method will create the dob, patient, location, time, timeslot and appt objects in order to
     * successfully execute schedule.remove(). schedule.remove() immediately returns a boolean in order to display
     * whether the removal was successful. If the boolean value is true it print that the appointment was cancelled
     * else it will print that it is not cancelled because it doesn't exist.
     * @param schedule The schedule object that holds all appointments
     * @param tokens the command and appointment information are stored in tokens in order to create objects and
     * to execute other methods
     */
    public void cancelAppointment(Schedule schedule, String[] tokens){
        if(isValidLocation(tokens) && !isEmptySchedule(tokens, schedule) && isValidDate(tokens)) {
            Date dob = new Date(tokens[1]);
            Date apptDate = new Date(tokens[4]);
            Patient patient = new Patient(tokens[2], tokens[3],dob);
            Time apptTime = new Time(tokens[5]);
            Timeslot slot = new Timeslot(apptDate, apptTime);
            Location location = Location.valueOf(tokens[6].toUpperCase());
            Appointment appt = new Appointment(patient, slot, location);
            if(schedule.remove(appt)) {
                System.out.println("Appointment cancelled.");
            } else {
                System.out.println("Not cancelled, appointment does not exist.");
            }
        }
    }

    /**
     * This method takes in the tokens, and schedule parameters in order to check whether there was only one command and
     * to check how many appointments are in the schedule by calling boolean schedule.checkNumAppts().
     * this function will return true to wherever it may have been called from if the length of tokens is 1 and
     * checkNumAppts() returns true.
     * @param tokens The command and appointment information are stored in tokens in order to create objects and
     * to execute other methods
     * @param schedule The schedule object that holds all appointments
     * @return boolean true or false, true if schedule is empty & tokens length is 1, false if not empty or
     * tokens length is not 1
     */
    public boolean isEmptySchedule(String[] tokens, Schedule schedule){
         return tokens.length == MIN_INPUT_LENGTH && schedule.noAppointments();
    }

    /**
     * This method takes in the tokens in order to check whether the location was that put in through the
     * console is one of the 5 valid locations in where an appointment can be made. This will immediately return
     * whether the location is valid or not.
     * @param tokens The command and appointment information are stored in tokens in order to create objects and
     * to execute other methods
     * @return boolean true or false: true if valid location, false if not valid location.
     */
    public boolean isValidLocation(String[] tokens){
        if(!tokens[6].equalsIgnoreCase("MORRIS")
                && !tokens[6].equalsIgnoreCase("SOMERSET")
                && !tokens[6].equalsIgnoreCase("UNION")
                && !tokens[6].equalsIgnoreCase("MIDDLESEX")
                && !tokens[6].equalsIgnoreCase("MERCER")) {
            System.out.println("Invalid location!");
            return false;
        }
        return true;
    }

    /**
     * This method takes in tokens in order to check whether the date that was put into the console is a valid date.
     * handleValidDate will create objects dob, apptDate, apptTime, patient, slot, location, and appt.
     * This will return immedialty if the date does not return true for the condtions that it is being tested for.
     * @param tokens The command and appointment information are stored in tokens in order to create objects and to
     * execute other methods
     * @return boolean true or false: true if conditions tested return true, false otherwise.
     */
    public boolean isValidDate(String[] tokens){
        Date dob = new Date(tokens[1]);
        Date apptDate = new Date(tokens[4]);
        Time apptTime = new Time(tokens[5]);
        Patient patient = new Patient(tokens[2], tokens[3],dob);
        Timeslot slot = new Timeslot(apptDate, apptTime);
        Location location = Location.valueOf(tokens[6].toUpperCase());
        Appointment appt = new Appointment(patient, slot, location);
        if(!dob.isValidDate() || !dob.isValidDays()){
            System.out.println("Invalid date of birth!");
            return false;
        }
        if(!apptDate.isValidDate() || !apptDate.isValidDays()){
            System.out.println("Invalid appointment date!");
            return false;
        }
        if(!appt.isValidApptDate()){
            System.out.println("Appointment date invalid -> must be future date.");
            return false;
        }
        if(!patient.isValidDOB()) { // may be wrong
            System.out.println("Date of birth invalid -> it is a future date");
            return false;
        }
        if(slot.isValidTime()){
            System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.");
            return false;
        }
        return true;
    }

    /**
     * This method takes in tokens and schedule in order to check for errors that may occur while when adding an
     * appointment. checkAddError creates objects dob, apptDate, apptTime, patient, slot, location, and appt in order
     * to pass these through other methods that returns an integer to specify what error occurred while adding.
     * This method will print the error depending on what integer was returned
     * and ultimately return false if an error was ran into, otherwise true.
     * @param tokens The command and appointment information are stored in tokens in order to create
     * objects and to execute other methods
     * @param schedule The schedule object that holds all appointments
     * @return boolean true or false: true if there are no errors, false otherwise.
     */
    public boolean isValidAdd(String[] tokens, Schedule schedule){
        Date dob = new Date(tokens[1]);
        Date apptDate = new Date(tokens[4]);
        Time apptTime = new Time(tokens[5]);
        Patient patient = new Patient(tokens[2], tokens[3],dob);
        Timeslot slot = new Timeslot(apptDate, apptTime);
        Location location = Location.valueOf(tokens[6].toUpperCase());
        Appointment appt = new Appointment(patient, slot, location);
        if(schedule.ADD_ERROR == schedule.SAME_PATIENT){
            System.out.println("Same patient cannot book an appointment with the same date.");
            return false;
        }
        if(schedule.appointmentExists(appt) != SAME_APPT){
            System.out.println("Same appointment exists in the schedule.");
            return false;
        }
        if(schedule.ADD_ERROR == schedule.SAME_TIMESLOT){
            System.out.println("Time slot has been taken at this location.");
            return false;
        }
        return true;
    }
}



