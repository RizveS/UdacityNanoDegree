package source.ui;
import source.model.*;
import source.ui.ReturnToMainMenuException;

import java.util.Scanner;
import java.util.List;

import source.service.CustomerService;
import source.service.ReservationService;
import source.service.ServiceExceptions.NoCustomerFoundException;
import source.service.ServiceExceptions.NoRoomFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class main_menu {
    /*Find and reserve a room
    -> Asks users to input type of room
    -> Lists out all the rooms that meet the criteria
    -> Asks users to choose room if available
    -> Asks users to confirm selection of room
    -> Users agree to pay in person before moving into room
    -> Confirmation of booked room is shown
    */
    public static void main(String args[]) {
        int userChoice;
        Scanner InputConsole = new Scanner(System.in);
        while (true) {
            System.out.println("--------------- Main Menu------------------");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Open Admin Menu (Requires credentials)");
            System.out.println("5. Exit");
            userChoice = InputConsole.nextInt();
            InputConsole.nextLine();
            if (userChoice == 5) {
                break;
            }

            try {
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
                    default:
                    System.out.println("Invalid Option");
                }
            }
            catch (ReturnToMainMenuException e) {
                System.out.println(e.getMessage());
            }
        }
        InputConsole.close();

    }

    private static void ListOption1(Scanner InputConsole) throws ReturnToMainMenuException{
        Boolean passInputValid = false;
        int branchVar = 0; //Used to decide which branch to take
        int chosenRoom = 0;
        Date CheckInDate = new Date();
        Date CheckOutDate = new Date();
        int roomCount = 0;
        Collection<Room> RoomList;
        List<Room> RoomListIndexable = new ArrayList<Room>();

        System.out.println("---------------- Find & Reserve Room------------------");
        System.out.println("Please input the type of room you want to reserve, SINGLE or DOUBLE");
        System.out.println("Press 1 if you want to reserve a SINGLE room");
        System.out.println("Press 2 if you want to reserve a DOUBLE room");
    
        while (!passInputValid) {
            switch (InputConsole.nextInt()) {
                    case 1:
                    passInputValid = true;
                    branchVar = 1;
                    break;

                    case 2:
                    passInputValid = true;
                    branchVar = 2;
                    break;

                    default:
                    passInputValid = false;
                    System.out.println("Please enter either 1 or 2");
            }
        }
        InputConsole.nextLine();
        CheckInDate = GetDateInput("Please enter the desired check-in date as MM/DD/YYYY:",InputConsole);
        CheckOutDate = GetDateInput("Please enter the desired check-out date as MM/DD/YYYY",InputConsole);
    
        try {
            RoomList = ReservationService.findRooms(CheckInDate,CheckOutDate);
            System.out.println("The following rooms are available");
            for (Room room: RoomList) {
                    if (branchVar == 1) {
                        if (room.getRoomType() == RoomType.SINGLE) {
                            System.out.print(++roomCount);
                            System.out.println(room.toString());
                            RoomListIndexable.add(room);
                        }
                    }
                    else {
                        if (room.getRoomType() == RoomType.DOUBLE) {
                            System.out.print(++roomCount);
                            System.out.println(room.toString());
                            RoomListIndexable.add(room);
                        }
                    }
            };
        }
        catch (NoRoomFoundException e) {
            System.out.println("No rooms with the specified check-in and check-out dates were found");
            throw new ReturnToMainMenuException("Returning to main menu");
        }

        System.out.println("Please specify which room you want to choose by entering the number of the room from list above");
        chosenRoom = InputConsole.nextInt();
        InputConsole.nextLine();
        passInputValid = false;

        while(!passInputValid) {
            if (chosenRoom < roomCount + 1) {
                    System.out.println(String.format("You are agreeing to book Room %d", chosenRoom));
                    break;
            }
            else {
                System.out.println(String.format("Please enter a valid room number between 1 and %d",roomCount));
                System.out.println("If you do want to proceed with booking a room, enter EXIT");
                if (InputConsole.nextLine().matches("EXIT")) {
                    throw new ReturnToMainMenuException("Returning to main menu....");
                };

            }
        }

        System.out.println("By proceeding, you agree to pay the fees and expenses associated with the room. Payment is due at check-in");
        System.out.println("Do you still wish to continue? Press Y to move forward or press any other key to return to main menu");

        if (InputConsole.nextLine().matches("Y")) {
            System.out.println("Please enter your first name: ");
            String firstName = InputConsole.nextLine();
            System.out.println("Please enter your last name: ");
            String lastName = InputConsole.nextLine();
            System.out.println("Please enter your email address (Example: JohnDoe@gmail.com): ");
            String email = InputConsole.nextLine();
            while (true) {
                System.out.println("Please enter a valid email address: ");
                System.out.println("Enter EXIT to return to main menu");
                email = InputConsole.nextLine();
                    if (email.matches("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})")) {
                        break;
                    }
                    if (email.matches("EXIT")) {
                        throw new ReturnToMainMenuException("Returning to main menu....");
                    }

                }
            Customer person = new Customer(firstName, lastName, email);
            ReservationService.reserveARoom(person, RoomListIndexable.get(chosenRoom), CheckInDate, CheckOutDate);
            System.out.println("Thank you for staying with us! Your room has been booked.");
            throw new ReturnToMainMenuException("Returning to main menu....");

        }
    }
    private static void ListOption2(Scanner InputConsole) throws ReturnToMainMenuException{
        System.out.println("---------------- See Your Reservations------------------");
        System.out.println("Please enter your first name: ");
        String firstName = InputConsole.nextLine(); 
        System.out.println("Please enter your last name: ");
        String lastName = InputConsole.nextLine();


        System.out.println("Please enter your email address (Example: JohnDoe@gmail.com): ");
        String email = InputConsole.nextLine();
        while (true) {
            if (email.matches("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})")) {
                break;
            }
            System.out.println("Please enter a valid email address");
            System.out.println("Enter EXIT to return to main menu");
            email = InputConsole.nextLine();
        }
        Customer customer = new Customer(firstName,lastName,email);
        try{
        for (Reservation resv: ReservationService.getCustomerReservation(customer)) {
            System.out.println(resv.toString());
        };
        }
        catch (NoCustomerFoundException e) {
            System.out.println("No customer with that email was found.");
            throw new ReturnToMainMenuException("Returning to main menu");
        }

    }
    private static void ListOption3(Scanner InputConsole) throws ReturnToMainMenuException{
        System.out.println("---------------- Create a Customer Account------------------");
        System.out.println("Please enter your first name: ");
        String firstName = InputConsole.nextLine(); 
        System.out.println("Please enter your last name: ");
        String lastName = InputConsole.nextLine(); 

        System.out.println("Please enter your email address (Example: JohnDoe@gmail.com): ");
        String email = InputConsole.nextLine();
        while (true) {
            if (email.matches("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})")) {
                break;
            }
            System.out.println("Please enter a valid email address");
            System.out.println("Enter EXIT to return to main menu");
            email = InputConsole.nextLine();
        }
        CustomerService.addCustomer(firstName, lastName, email);
        System.out.println(String.format("An account for %s %s has been created, with email %s. Thank you", firstName,lastName,email));
        throw new ReturnToMainMenuException("Returning to main menu");

    }

    //Helper functions
    private static Date GetDateInput(String displayString, Scanner InputConsole){
        System.out.println(displayString);
        Boolean passInputValid = false;
        String DateInp = "";
        Date output = new Date();
        while (!passInputValid) {
            String InputString = InputConsole.nextLine();
            if (InputString.matches("(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|30|31)/(\\d{4})")) {
                DateInp = InputString;
                passInputValid = true;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
            try {
            output = dateFormat.parse(DateInp);
            }
            catch (ParseException e) {
                passInputValid = false;
            }
            if (!passInputValid) {
                System.out.println("Please follow the required MM/dd/YYYY format");
                System.out.println(displayString);
            }
        }
        return output;
      
    }

    }
