package source.ui;

import java.util.Scanner;

import source.model.Customer;
import source.model.Room;
import source.service.CustomerService;
import source.service.ReservationService;

public class admin_menu {
    public static void launch_admin_menu(Scanner InputConsole) throws ReturnToMainMenuException{
        int userChoice;
        System.out.println("------------------Admin Menu----------------");
        System.out.println("Please enter the email associated with your admin account");
        String email = InputConsole.nextLine();
        System.out.println("Please enter the password associated with your admin account");
        String pass = InputConsole.nextLine();
        while (true) {
        if (checkPass(email,pass)) {
            System.out.println("Thank you for verifying your identity.");
            System.out.println("What would you like to do today?");
            userChoice = InputConsole.nextInt();
            InputConsole.nextLine();
            switch (userChoice) {
                case 1:
                    ListOption1(InputConsole);
                    break;
                case 2:
                    ListOption2(InputConsole);
                    break;
                case 3:
                    ListOption3(InputConsole);
                    break;
                case 4: 
                    ListOption4(InputConsole);
                    break;
                case 5:
                    throw new ReturnToMainMenuException("Returning to main menu.....");
                    break;
                default:
                    System.out.println("Invalid option chosen");

            }

        }
        else {
            System.out.println("Could not find account with provided credentials");
            throw new ReturnToMainMenuException("Returning to main menu.....");
        }
    }
    }

    private static boolean checkPass(String email,String pass) {
        if (email.matches("admin@admin.com") & pass.matches("pass")) {
            return true;
        }
        else {
            return false;
        }
    }

    private static void ListOption1(Scanner InputConsole) {
        System.out.println("-------------See all Customers-----------------");
        for (Customer cust: CustomerService.getAllCustomers()) {
            System.out.println(cust.toString());
        };
    }

    private static void ListOption2(Scanner InputConsole) {
        System.out.println("----------------See all Rooms-------------------");
        for (Room room: ReservationService.getAllRooms()) {
            System.out.println(room.toString());
        }
    }
    
    private static void ListOption3(Scanner InputConsole) {
        System.out.println("----------------See all Reservations-------------------");
        ReservationService.printAllReservation();
    }

    private static void ListOption4(Scanner InputScanner) {
        int roomType;
        System.out.println("----------------Add a Room-------------------");
        System.out.println("Please enter the type of room, SINGLE or DOUBLE");
        System.out.println("1. SINGLE");
        System.out.println("2. DOUBLE");
        roomType = InputScanner.nextInt();
        InputScanner.nextLine();

        System.out.println("Please enter the room number");
        //Do checks here for room number

        

        ReservationService.addRoom(rooms);

    }


}
