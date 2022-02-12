import java.util.Scanner;

/*

authors: @katiesidebotham @kevinarbito
 */
public class Kiosk {
    public void run() {
        System.out.print("Kiosk running. Ready to process transactions.");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        Schedule schedule = new Schedule();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] tokens = line.split(" ");
            commandHelper(tokens, schedule);
        }
    }

    public void commandHelper(String[] tokens, Schedule schedule) {
        if(tokens[0].equals("P")) {
            schedule.print();
        } else if(tokens[0].equals("PZ")) {
            schedule.printByZip();
        } else if(tokens[0].equals("PP")) {
            schedule.printByPatient();
        } else if(tokens[0].equals("Q")) {
            System.out.println("Kiosk Session Ended");
        } else if(tokens[0].equals("CP") || tokens[0].equals("C")) {
            cancel(schedule, tokens);
        } else if(tokens[0].equals("B")) {
            bookAppointment(schedule, tokens);
        } else {
            System.out.println("Invalid Command");
        }
    }

    public void bookAppointment(Schedule schedule, String[] tokens) {
        if(checkErrors(tokens, schedule)) {
            addHelper(tokens, schedule);
        }
    }

    public void addHelper(String[] tokens, Schedule schedule) {
        Date dob = new Date(tokens[1]);
        Date apptDate = new Date(tokens[4]);
        Patient patient = new Patient(tokens[2], tokens[3],dob);
        Time apptTime = new Time(tokens[5]);
        Timeslot slot = new Timeslot(apptDate, apptTime);
        Location location = Location.valueOf(tokens[6].toUpperCase());
        Appointment appt = new Appointment(patient, slot, location);
        schedule.add(appt);
        System.out.println("Appointment booked and added to the schedule.");
    }
    public void cancel(Schedule schedule, String[] tokens){
        if(checkErrors(tokens, schedule)) {
            removeHelper(tokens, schedule);
        }
    }

    public void removeHelper(String[] tokens, Schedule schedule) {
        Date dob = new Date(tokens[1]);
        Date apptDate = new Date(tokens[4]);
        Patient patient = new Patient(tokens[2], tokens[3],dob);
        Time apptTime = new Time(tokens[5]);
        Timeslot slot = new Timeslot(apptDate, apptTime);
        Location location = Location.valueOf(tokens[6].toUpperCase());
        Appointment appt = new Appointment(patient, slot, location);
        if(schedule.remove(appt)) {
            System.out.println("Appointment Cancelled");
        } else {
            System.out.println("Not cancelled, appointment does not exist");
        }
    }

    public boolean checkErrors(String[] tokens, Schedule schedule){
        if(tokens.length == 1 && schedule.checkIfEmpty()){
            System.out.println("Schedule is empty!");
            return false;
        }
        Date dob = new Date(tokens[1]);
        Date apptDate = new Date(tokens[4]);
        Patient patient = new Patient(tokens[2], tokens[3],dob);
        Time apptTime = new Time(tokens[5]);
        Timeslot slot = new Timeslot(apptDate, apptTime);
        Location location;
        if(!tokens[6].equalsIgnoreCase("MORRIS") && !tokens[6].equalsIgnoreCase("SOMERSET") && !tokens[6].equalsIgnoreCase("UNION")  && !tokens[6].equalsIgnoreCase("MIDDLESEX")
        && !tokens[6].equalsIgnoreCase("MERCER")) {
            System.out.println("Invalid Location");
            return false;
        }
        location = Location.valueOf(tokens[6].toUpperCase());
        Appointment appt = new Appointment(patient, slot, location);
        if(!apptTime.isValid()){
            System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.");
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
        if(!dob.isValid()){
            System.out.println(dob);
            System.out.println("Invalid date of birth!");
            return false;
        }
        if(schedule.addError == 2 || schedule.addError == 3){
            System.out.println("Same patient cannot book an appointment with the same date");
            return false;
        }
        if(schedule.checkifExist(appt) != -1){
            System.out.println("Same appointment exists in the schedule");
            return false;
        }
        if(schedule.addError == 1){
            System.out.println("Time slot has been taken at this location.");
            return false;
        }
        return true;
    }
}



