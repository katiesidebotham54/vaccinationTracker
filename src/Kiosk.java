public class Kiosk {
    public void run(){
        Scanner sc = new Scanner(System.in);
        Schedule schedule = new Schedule();
        while(sc.hasNext()){
            command = sc.next();
            if( command = "B"){
                schedule.add();
            } else if( command == "C"){
                schedule.remove();
            } else if(command == "CP"){

            } else if(command == "P"){
                schedule.print();
            } else if(command == "PZ"){
                schedule.printByZip();
            } else if(command == "PP"){
                schedule.printByPatient();
            } else if(command == "Q"){
                sc.close();
                System.out.println("Kiosk session ended.");
            } else{
                // invalid input
            }
        }

    }
}
