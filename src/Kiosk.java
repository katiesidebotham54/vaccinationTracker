import java.util.Scanner;

/*

authors: @katiesidebotham @kevinarbito
 */
public class Kiosk {
    public static final int CP_MIN_LENGTH = 4;
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
        switch (tokens[0]) {
            case "P" -> {
                if(checkEmptySchedule(tokens, schedule)){
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
            case "Q" -> System.out.println("Kiosk Session Ended");
            case "C" -> cancel(schedule, tokens);
            case "CP" -> {
                if(checkEmptySchedule(tokens, schedule) || tokens.length < CP_MIN_LENGTH){
                    System.out.println("Invalid command");
                } else{
                    cancelByPatient(schedule, tokens);
                }
            }
            case "B" -> bookAppointment(schedule, tokens);
            default -> System.out.println("Invalid Command");
        }
    }

    public void bookAppointment(Schedule schedule, String[] tokens) {
        if(checkLocation(tokens, schedule) && !checkEmptySchedule(tokens, schedule)) {
//            System.out.println("checkAddError: " + checkAddError(tokens, schedule));
            schedule.addError = 0;
            if(handleValidDate(tokens, schedule) && checkAddError(tokens, schedule)) {
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
                    checkAddError(tokens, schedule);
                }
            }
        }
        return;
    }

    public void cancelByPatient(Schedule schedule, String[] tokens){
        if(!checkEmptySchedule(tokens, schedule)) {
            Date dob = new Date(tokens[1]);
            Patient patient = new Patient(tokens[2], tokens[3], dob);
            Appointment appt = new Appointment(patient);
            System.out.println(schedule.removeByPatient(appt));
            if(schedule.removeByPatient(appt)) {
                System.out.println("All Appointments for " + patient.getFname() + " " + patient.getLname() + " DOB: " + patient.getDob() + " have been cancelled.");
            } else {
                System.out.println("Not cancelled, appointment(s) does not exist.");
            }
        }
    }

    public void cancel(Schedule schedule, String[] tokens){
        if(checkLocation(tokens, schedule) && !checkEmptySchedule(tokens, schedule) && handleValidDate(tokens, schedule)) {
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

    public boolean checkEmptySchedule(String[] tokens, Schedule schedule){
         return tokens.length == 1 && schedule.checkNumAppts();
    }

    public boolean checkLocation(String[] tokens, Schedule schedule){
        if(!tokens[6].equalsIgnoreCase("MORRIS") && !tokens[6].equalsIgnoreCase("SOMERSET") && !tokens[6].equalsIgnoreCase("UNION")  && !tokens[6].equalsIgnoreCase("MIDDLESEX")
        && !tokens[6].equalsIgnoreCase("MERCER")) {
            System.out.println("Invalid location!");
            return false;
        }
        return true;
    }
    public boolean handleValidDate(String[] tokens, Schedule schedule){
        Date dob = new Date(tokens[1]);
        Date apptDate = new Date(tokens[4]);
        Time apptTime = new Time(tokens[5]);
        Patient patient = new Patient(tokens[2], tokens[3],dob);
        Timeslot slot = new Timeslot(apptDate, apptTime);
        Location location = Location.valueOf(tokens[6].toUpperCase());
        Appointment appt = new Appointment(patient, slot, location);
        if(!dob.isValid() || !dob.checkDays()){
            System.out.println("Invalid date of birth!");
            return false;
        }
        if(!apptDate.isValid() || !apptDate.checkDays()){
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
        if(!apptTime.isValid()){
            System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.");
            return false;
        }
        return true;
    }

    public boolean checkAddError(String[] tokens,Schedule schedule){
        Date dob = new Date(tokens[1]);
        Date apptDate = new Date(tokens[4]);
        Time apptTime = new Time(tokens[5]);
        Patient patient = new Patient(tokens[2], tokens[3],dob);
        Timeslot slot = new Timeslot(apptDate, apptTime);
        Location location = Location.valueOf(tokens[6].toUpperCase());
        Appointment appt = new Appointment(patient, slot, location);
        if(schedule.addError == 2){
            System.out.println("Same patient cannot book an appointment with the same date.");
            return false;
        }
        if(schedule.checkIfExist(appt) != -1){
            System.out.println("Same appointment exists in the schedule.");
            return false;
        }
        if(schedule.addError == 1){
            System.out.println("Time slot has been taken at this location.");
            return false;
        }
        return true;
    }
}



