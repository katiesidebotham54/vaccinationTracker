import java.util.Scanner;
import java.util.StringTokenizer;

/*

authors: @katiesidebotham @kevinarbito
 */
public class Kiosk {
    public void run() {
        System.out.print("Kiosk running. Ready to process transactions.");
        String[] inputs = new String[6];
        Scanner sc = new Scanner(System.in);
        Schedule schedule = new Schedule();
        while (sc.hasNext()) {
            StringTokenizer st = new StringTokenizer(sc.next());
            while(st.hasMoreTokens()){
                for (int i = 0; i < inputs.length; i++) {
                    inputs[i] = st.nextToken();
                }
            }
            String command = inputs[0];
            switch (command) {
                case "P" -> schedule.print();
                case "PZ" -> schedule.printByZip();
                case "PP" -> schedule.printByPatient();
                case "Q" -> {
                    sc.close();
                    System.out.println("Kiosk session ended.");
                }
                case "CP" -> System.out.println("All appointments for " + patient.toString() + " have been cancelled");
                case "B" -> {
                    runB(inputs, schedule);
                }
                case "C" -> runC(inputs, schedule);
                default -> System.out.println("Invalid command!");
            }
        }
    }

    public void runB(String[] inputs, Schedule schedule){
        String birthdate = inputs[1];
        String fname = inputs[2];
        String lname = inputs[3];
        String apptDate = inputs[4];
        String apptTime = inputs[5];
        String county = inputs[6];
        Date dob = new Date(birthdate);
        Date date = new Date(apptDate);
        Time time = new Time(apptTime);
        Patient patient = new Patient(fname, lname, dob);
        Timeslot slot = new Timeslot(date, time);
        Appointment appt = new Appointment(patient, slot, location);
        checkForErrors(inputs, schedule);
        if(schedule.add(appt)){
            System.out.println("Appointment booked and added to the schedule.");
        }
    }


    public void checkForErrors(String[] inputs, Schedule schedule) {
        Date dob = new Date(inputs[1]);
        Date date = new Date(inputs[4]);
        Time time = new Time(inputs[5]);
        Time apptTime = new Time(inputs[5]);
        Patient patient = new Patient(inputs[2], inputs[3], dob);
        Timeslot slot = new Timeslot(date, time);
        Appointment appt = new Appointment(patient, slot, location);
        if(!apptTime.isValid()){
            System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.");
        }
        if(!appt.isValidApptDate()){
            System.out.println("Appointment date invalid -> must be future date.");
        }
        if(!patient.isValidDOB()) {
            System.out.println("Date of birth invalid -> it is a future date");
        }
        if(!appt.isValidLocation()){
            System.out.println("Invalid location!");
        }
        if(!dob.isValid()){
            System.out.println("Invalid date of birth!");
        }
        if(addError == 0 || addError == 2){
            System.out.println("Same patient cannot book an appointment with the same date");
        }
        if(schedule.find(appt) != -1){
            System.out.println("Same appointment exists in the schedule");
        }
        if(addError == 1){
            System.out.println("Time slot has been taken at this location.");
        }
    }


    public void runC(String[] inputs, Schedule schedule){
        String birthdate = inputs[1];
        String fname = inputs[2];
        String lname = inputs[3];
        String apptDate = inputs[4];
        String apptTime = inputs[5];
        String county = inputs[6];
        Date dob = new Date(birthdate);
        Date date = new Date(apptDate);
        Time time = new Time(apptTime);
        Patient patient = new Patient(fname, lname, dob);
        Timeslot slot = new Timeslot(date, time);
        Appointment appt = new Appointment(patient, slot, location);
        checkForErrors();
        if(schedule.remove(appt)) {
            System.out.println("Appointment Cancelled");
        } else{
            System.out.println("Not cancelled, appointment does not exist");
        }
    }
}
