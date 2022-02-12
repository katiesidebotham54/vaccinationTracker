import java.util.Scanner;

/*

authors: @katiesidebotham @kevinarbito
 */
public class Kiosk {
    public void run() {
        System.out.print("Kiosk running. Ready to process transactions.");
        System.out.println();
        String[] inputs = new String[7];
        Scanner sc = new Scanner(System.in);
        Schedule schedule = new Schedule();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] tokens = line.split(" ");
            char[] charArray = tokens[0].toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    //if any character is in upper case, return false
                    if (Character.isLowerCase(charArray[i])) {
                        System.out.println("Invalid Command");
                        continue;
                    }
                }
            if(tokens.length == 1){ // test case for 1 input at start
                if(tokens[0].equals("C") || tokens[0].equals("B")) {
                    System.out.println("Not enough inputs");
                    continue;
                } else if(tokens[0].equals("Q")){
                    System.out.println("Kiosk session ended");
                    break;
                }
            }
            if((tokens[0].equals("P") || tokens[0].equals("PZ") || tokens[0].equals("PP")  ||  tokens[0].equals("C") || tokens[0].equals("CP")) && schedule.checkIfEmpty()){
                System.out.println("Schedule is empty!");
                continue;
            }
            for(int i = 0; i< tokens.length; i++){
                inputs[i] = tokens[i];
            }
            String birthdate = inputs[1];
            String fname = inputs[2];
            String lname = inputs[3];
            String apptDate = inputs[4];
            String apptTime = inputs[5];
            String county = "";
            if(apptTime != null) {
                county = inputs[6].toUpperCase();
            }
            Location location;
            try {
                location = Location.valueOf(county);
            } catch (Exception ex) {
                System.out.println("Invalid location!");
                continue;
            }
//            Location location = Location.valueOf(county); // valueOf documentation should work (confirm tmrw)
            Date dob = new Date(birthdate);
            Date date = new Date(apptDate);
            Time time = new Time(apptTime);
            Patient patient = new Patient(fname, lname, dob);
            Timeslot slot = new Timeslot(date, time);
            Appointment appt = new Appointment(patient, slot, location);
            switcher(schedule, inputs, patient, appt);
        }
    }

    public void checkLowercase(String[] tokens){
        char[] charArray = tokens[0].toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            //if any character is in upper case, return false
            if (Character.isLowerCase(charArray[i])) {
                System.out.println("Invalid Command");
                continue;
            }
        }

    }

    public void switcher(Schedule schedule, String[] inputs, Patient patient, Appointment appt) {
        switch (inputs[0]) {
            case "P" -> schedule.print();
            case "PZ" -> schedule.printByZip();
            case "PP" -> schedule.printByPatient();
            case "CP" -> System.out.println("All appointments for " + patient.toString() + " have been cancelled");
            case "B" -> runB(inputs, schedule, appt, patient);
            case "C" -> runC(inputs, schedule, appt, patient);
            default -> System.out.println("Invalid command!");
        }
    }

    public void runB(String[] inputs, Schedule schedule, Appointment appt, Patient patient){

        if(checkForErrors(inputs, schedule, appt, patient)) {
            if (schedule.add(appt)) {
                System.out.println("Appointment booked and added to the schedule.");
            }
        }
    }
    public boolean checkForErrors(String[] inputs, Schedule schedule, Appointment appt, Patient patient) {
        Date dob = new Date(inputs[1]);
        Time apptTime = new Time(inputs[5]);
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


    public void runC(String[] inputs, Schedule schedule, Appointment appt, Patient patient){
        if(checkForErrors(inputs, schedule, appt, patient)) {
            if (schedule.remove(appt)) {
                System.out.println("Appointment Cancelled");
            } else {
                System.out.println("Not cancelled, appointment does not exist");
            }
        }
    }
}



