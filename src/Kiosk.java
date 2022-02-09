import java.util.Scanner;
/*

authors: @katiesidebotham @kevinarbito
 */
public class Kiosk {
    public void run(){
        System.out.print("Kiosk running. Ready to process transactions.");
        Scanner sc = new Scanner(System.in);
        Schedule schedule = new Schedule();
        while(sc.hasNext()){
            String command = sc.next();
            if(command == "P"){
                schedule.print();
            } else if(command == "PZ"){
                schedule.printByZip();
            } else if(command == "PP"){
                schedule.printByPatient();
            } else if(command == "Q"){
                sc.close();
                System.out.println("Kiosk session ended.");
            }
            String patientDOB = sc.next();
            Date dob = new Date(patientDOB);
            String patientF = sc.next();
            String patientL = sc.next();
            if(command == "CP"){
                System.out.println("All appointments for " + patient.toString() + " have been cancelled");
            }
            String date = sc.next();
            Date apptDate = new Date(date);
            String time = sc.next();
            Time apptTime = new Time(time)
            String county = sc.next();

            if(command == "B"){
               runB();
            } else if( command == "C"){
                runC();
            }  else{
                System.out.println("Invalid command!");
            }
        }

    }

    public void runB(){
        Patient patient = new Patient(patientF, patientL, dob);
        Timeslot slot = new Timeslot(apptDate, apptTime);
        Location location = new Location(county);
        Appointment appt = new Appointment(patient, slot, location);
        checkForErrors();
        if(schedule.add(appt)){
            System.out.println("Appointment booked and added to the schedule.")
        }
    }

    public void checkForErrors() {
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
        System.out.println("Same appointment exists in the schedule");
        System.out.println("Same patient cannot book an appointment with the same date");
        System.out.println("Time slot has been taken at this location.");

    }


    public void runC(){
        Patient patient = new Patient(patientF, patientL, dob);
        Timeslot slot = new Timeslot(apptDate, apptTime);
        Location location = new Location(county);
        Appointment appt = new Appointment(patient, slot, location);
        checkForErrors();
        if(schedule.remove(appt)) {
            System.out.println("Appointment Cancelled");
        } else{
            System.out.println("Not cancelled, appointment does not exist");
        }
    }
}
