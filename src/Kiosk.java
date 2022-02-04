public class Kiosk {
    public void run(){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            command = sc.next();
            if( command = "B"){

            } else if( command == "C"){

            } else if(command == "CP"){

            } else if(command == "P"){

            } else if(command == "PZ"){

            } else if(command == "PP"){

            } else if(command == "Q"){
                sc.close();
                System.out.println("Kiosk session ended.");
            } else{
                // invalid input
            }
        }

    }
}
