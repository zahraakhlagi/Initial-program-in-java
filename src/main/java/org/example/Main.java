package org.example;

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static Scanner userInput=new Scanner(System.in);
    public static final int MAX_CARS= 10;

    public static void main(String[] args) throws ParseException {

        String[][] parkingLot= new String[MAX_CARS][5];
        int numberOfCars=0;

        loadTestData(parkingLot);
        numberOfCars=4;

        while (true){
            switch (menu()){
                case 1:
                    System.out.println("car check in");
                    break;
                case 2:
                    System.out.println("car check out");
                    break;
                case 3:
                    System.out.println("car status");
                    break;
                case 4:
                    System.out.println("print history by arrival date");
                    printHistoryByArrivalDate(parkingLot,numberOfCars);
                    //printParkingSummary(parkingLot);

                    break;
                case 5:
                    System.out.println("print history by registration number");
                    break;
                case -1:
                    System.exit(0);
                    break;
                default:
                    System.out.println("This is invalid entry");
            }
        }

    }

    //test data
    public static void loadTestData(String [][] parkingLot){
        //Entry 1
        parkingLot[0][0] = "GHJ67D";
        parkingLot[0][1] = "2023-01-01";
        parkingLot[0][2] = "2023-01-02";
        parkingLot[0][3] = "Yes";
        parkingLot[0][4] = "370";

        //Entry 2
        parkingLot[1][0] = "DEF456";
        parkingLot[1][1] = "2019-01-01";
        parkingLot[1][2] = "2019-01-02";
        parkingLot[1][3] = "No";
        parkingLot[1][4] = "120";

        //Entry 3
        parkingLot[2][0] = "ABC123";
        parkingLot[2][1] = "2021-01-01";
        parkingLot[2][2] = "";
        parkingLot[2][3] = "No";
        parkingLot[2][4] = "0";

        //Entry 4
        parkingLot[3][0] = "BCD78Y";
        parkingLot[3][1] = "2022-01-01";
        parkingLot[3][2] = "";
        parkingLot[3][3] = "Yes";
        parkingLot[3][4] = "0";
    }

    //print parking summary
    public static void printParkingSummary(String[][] parkingLot, int numberOfCars){
        //Header
        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", "Registration","Entered", "Existed","Charging used","Parking cost");

        for(int i=0;i< numberOfCars;i++){
            System.out.printf("%-15s %-15s %-15s %-15s %-15s%n",parkingLot[i][0],parkingLot[i][1],parkingLot[i][2],parkingLot[i][3],parkingLot[i][4]);
        }
    }

    public static void printHistoryByArrivalDate(String[][] parkingLot, int numberOfCars) {
        String[][] tempParkingLot= new String[MAX_CARS][5];
        System.arraycopy(parkingLot,0,tempParkingLot,0,parkingLot.length);

        for(int i = 0; i < numberOfCars; i++) {
            if(tempParkingLot[i][0]==null) break;
             for(int j = 0; j < numberOfCars - 1; j++) {
                 if(tempParkingLot[j][0]== null || tempParkingLot[j+1]==null) break;
             System.out.println("The value of j is " + j);
                if(tempParkingLot[j][1].compareToIgnoreCase(tempParkingLot[j + 1][1]) > 0) {
                 String[] temp = tempParkingLot[j];
                 tempParkingLot[j] = tempParkingLot[j + 1];
                 tempParkingLot[j + 1] = temp;
                }
            }
        }
        printParkingSummary(tempParkingLot,numberOfCars);
    }
    //create a menu function
    public static int menu(){
        System.out.println("-------------parking lot----------");
        System.out.println("1.Drive in");
        System.out.println("2.Drive out");
        System.out.println("3. Check parking");
        System.out.println("4. Print parking history (by arrival date)");
        System.out.println("5. Print parking history (by registration number)");
        System.out.println("q. End program");
        System.out.println("> Enter your option:");
        return input();
    }

    public static int input() {
        int number=0;
        while(true){
            if(userInput.hasNextInt()){
                String temp= userInput.nextLine();
                number= Math.abs(Integer.parseInt(temp));
                if(number>0){
                    break;
                }
            } else if (userInput.hasNext()) {
                String inString= userInput.nextLine();
                if(inString.equalsIgnoreCase("q")){
                    number=-1;
                    break;
                }
            }

        }
        return number;
    }
}