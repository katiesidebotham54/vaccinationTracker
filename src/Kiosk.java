public class Kiosk {
    public void run(){
        Scanner sc = new Scanner(System.in);
        Schedule schedule = new Schedule();
        while(sc.hasNext()){
            command = sc.next();
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
            patientDOB = sc.next();
            patientF = sc.next();
            patientL = sc.next();
            if(command == "CP"){

            }
            date = sc.next();
            time = sc.next();
            county = sc.next();

            if( command = "B"){
                Patient patient = new Patient(patientF, patientL, patientDOB);
                Timeslot slot = new Timeslot(date, time);
                Location location = new Location(county);
                Appointment appt = new Appointment(patient, slot, location);
                schedule.add(appt);
            } else if( command == "C"){
                schedule.remove();
            }  else{
                // invalid input
            }
        }

    }
}
