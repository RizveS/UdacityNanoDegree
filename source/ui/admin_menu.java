package source.ui;
import source.service.ServiceExceptions.*;

import java.util.Collection;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import source.api.AdminResource;
import source.model.Customer;
import source.model.FreeRoom;
import source.model.Room;
import source.model.RoomType;
import source.service.CustomerService;
import source.service.ReservationService;
import source.service.ServiceExceptions.NoCustomerFoundException;
import source.service.ServiceExceptions.NoRoomFoundException;

import java.util.ArrayList;

public class admin_menu {
    public static void launch_admin_menu(Scanner InputConsole) throws ReturnToMainMenuException{
        int userChoice;
        System.out.println("------------------Admin Menu----------------");
        System.out.println("Please enter the email associated with your admin account");
        String email = InputConsole.nextLine();
        System.out.println("Please enter the password associated with your admin account");
        String pass = InputConsole.nextLine();
        System.out.println("Thank you for verifying your identity.");
        while (true) {
        if (checkPass(email,pass)) {
            System.out.println("What would you like to do today?");
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Menu");
            System.out.println("6. Populate customer database from local csv file");
            System.out.println("7. Populate room database from local csv file");
            System.out.println("8. Populate reservation database from local csv file");
            if (InputConsole.hasNextInt()) {
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
                    case 6:
                        ListOption6(InputConsole);
                        break;
                    case 7:
                        ListOption7(InputConsole);
                        break;
                    case 8:
                    try {
                        ListOption8(InputConsole);
                    }
                    catch (Exception e) {
                        System.out.println("Verification of customers added to database failed. Debug");
                        System.exit(1);
                    }
                        break;
                    default:
                        System.out.println("Invalid option chosen");

                }
            
            }
            else {
                System.out.println("Invalid Input");
                InputConsole.nextLine();
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
        CustomerService CustomerServiceInstance = AdminResource.getInstance().getCustomerInstance();
        Collection<Customer> CustomerList = CustomerServiceInstance.getAllCustomers();
        if (CustomerList.size() >  0) {
            for (Customer cust: CustomerList) {
                System.out.println(cust.toString());
            }
        }
        else {
            System.out.println("No customers currently in database");
        }
    }

    private static void ListOption2(Scanner InputConsole) {
        System.out.println("----------------See all Rooms-------------------");
        ReservationService ReservationServiceInstance = AdminResource.getInstance().getReservationInstance();
        Collection<Room> RoomList = ReservationServiceInstance.getAllRooms();
        if (RoomList.size() >  0) {
            for (Room room: RoomList) {
                System.out.println(room.toString());
            }
        }
    }
    
    private static void ListOption3(Scanner InputConsole) {
        System.out.println("----------------See all Reservations-------------------");
        ReservationService ReservationServiceInstance = AdminResource.getInstance().getReservationInstance();
        ReservationServiceInstance.printAllReservation();
    }

    private static void ListOption4(Scanner InputScanner) {
        RoomType roomType;
        String roomNumber;
        int userChoice;
        String userInpString;
        Collection<Room> RoomsToAdd = new ArrayList<Room>(); 
        Room room;
        System.out.println("----------------Add a Room-------------------");
        while (true) {
        System.out.println("Please enter the type of room, SINGLE or DOUBLE");
        System.out.println("1. SINGLE");
        System.out.println("2. DOUBLE");
        while (true) {
            userChoice = InputScanner.nextInt();
            InputScanner.nextLine();
            if (userChoice == 1) {
                roomType = RoomType.SINGLE;
                break;
            }
            else if (userChoice == 2) {
                roomType = RoomType.DOUBLE;
                break;
            }
            else {
                System.out.println("Please enter a valid input");
            }
        }
        InputScanner.nextLine();

        System.out.println("Please enter the room number");
        roomNumber = InputScanner.nextLine();
        System.out.println("Do you want to assign a price for the room? Type Y for yes or any other key to continue");
        userInpString = InputScanner.nextLine();
        if (userInpString.matches("Y")) {
        System.out.println("Please enter the price of the room");
        double priceInp = InputScanner.nextFloat();
        room = new Room(roomNumber, priceInp, roomType);
        }
        else {
            room = new FreeRoom(roomNumber,roomType);
        }
        RoomsToAdd.add(room);
        System.out.println("Do you want to continue adding rooms? Type Y for yes or any other key to continue");
        userInpString = InputScanner.nextLine();
        if (!userInpString.matches("Y")) {
            break;
        }
        }
        ReservationService ReservationServiceInstance = AdminResource.getInstance().getReservationInstance();
        ReservationServiceInstance.addRoom(RoomsToAdd);

    }

    private static void ListOption6(Scanner InputScanner) {
        System.out.println("----------------Populate Customer Database -------------------");
        System.out.println("Please provide location of csv files (relative path reference)");
        System.out.println("This depends on where you're running the java command from");
        System.out.println("If executing from within the hotel_reservation folder, press D to use sample datafile");
        

        CustomerService CustomerServiceInstance = AdminResource.getInstance().getCustomerInstance();
        String pathName = InputScanner.nextLine();
        if (pathName.matches("D")) {
            pathName = ".\\source\\data\\SampleCustomerDataSheet.csv";
        }
        try {
            Scanner inputFileScanner = new Scanner(new File(pathName));
            inputFileScanner.nextLine();
            String firstName;
            String lastName;
            String email;
        
            while(inputFileScanner.hasNextLine()) {
                    String EntryLine = inputFileScanner.nextLine();
                    firstName = EntryLine.split(",")[0];
                    lastName = EntryLine.split(",")[1];
                    email = EntryLine.split(",")[2];
                    try {
                        CustomerServiceInstance.getCustomer(email);
                        System.out.println(String.format("%s %s is already in the database", firstName,lastName));
                    }
                    catch (NoEmailFoundException e) {
                    CustomerServiceInstance.addCustomer(firstName, lastName, email);
                    System.out.println(String.format("%s %s has been added to database",firstName,lastName));
                }
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Invalid file provided");
        }

    }   
    
    private static void ListOption7(Scanner InputScanner) {
        System.out.println("----------------Populate Room Database -------------------");
        System.out.println("Please provide location of csv files (relative path reference)");
        System.out.println("This depends on where you're running the java command from");
        System.out.println("If executing from within the hotel_reservation folder, press D to use sample datafile");
        

        ReservationService ReservationServiceInstance = AdminResource.getInstance().getReservationInstance();
        String pathName = InputScanner.nextLine();
        if (pathName.matches("D")) {
            pathName = ".\\source\\data\\SampleRoomDataSheet.csv";
        }
        try {
            Scanner inputFileScanner = new Scanner(new File(pathName));
            inputFileScanner.nextLine();
            Collection<Room> RoomsToAdd = new ArrayList<Room>();
            String roomNumber;
            String roomType;
            double roomPrice;

            while(inputFileScanner.hasNextLine()) {
                    String EntryLine = inputFileScanner.nextLine();
                    roomNumber = EntryLine.split(",")[0];
                    roomType = EntryLine.split(",")[1];
                    roomPrice = Double.parseDouble(EntryLine.split(",")[2]);
                    try {
                        ReservationServiceInstance.getARoom(roomNumber);
                        System.out.println(String.format("Room %s already in database", roomNumber));
                    }
                    catch (NoRoomFoundException e) {
                    if (roomType.matches("SINGLE")) {
                        RoomsToAdd.add(new Room(roomNumber,roomPrice,RoomType.SINGLE));
                    }
                    else {
                        RoomsToAdd.add(new Room(roomNumber,roomPrice,RoomType.DOUBLE));
                    }
                    
                    System.out.println(String.format("Room %s has been added to database",roomNumber));
                }
            }
            ReservationServiceInstance.addRoom(RoomsToAdd);
        }
        catch (FileNotFoundException e) {
            System.out.println("Invalid file provided");
        }
    }

    private static void ListOption8(Scanner InputScanner) throws NoEmailFoundException {
        System.out.println("----------------Populate Reservation Database -------------------");
        System.out.println("Please provide location of csv files (relative path reference)");
        System.out.println("This depends on where you're running the java command from");
        System.out.println("If executing from within the hotel_reservation folder, press D to use sample datafile");
        
        CustomerService CustomerServiceInstance = AdminResource.getInstance().getCustomerInstance();
        ReservationService ReservationServiceInstance = ReservationService.getInstance();
        String pathName = InputScanner.nextLine();
        if (pathName.matches("D")) {
            pathName = ".\\source\\data\\SampleReservationDataSheet.csv";
        }
        try {
            Scanner inputFileScanner = new Scanner(new File(pathName));
            inputFileScanner.nextLine();
            Collection<Room> RoomsToAdd = new ArrayList<Room>();
            Customer customer;
            Room room;
            Date checkInDate = new Date();
            Date checkOutDate = new Date();

            while(inputFileScanner.hasNextLine()) {
                    String EntryLine = inputFileScanner.nextLine();
                    try {
                        customer = CustomerServiceInstance.getCustomer(EntryLine.split(",")[2]);
                    }
                    catch (NoEmailFoundException e) {
                        CustomerServiceInstance.addCustomer(EntryLine.split(",")[0], EntryLine.split(",")[1], EntryLine.split(",")[2]);
                        customer = CustomerServiceInstance.getCustomer(EntryLine.split(",")[2]);
                    }

                    try {
                        room = ReservationServiceInstance.getARoom(EntryLine.split(",")[3]);
                    }
                    catch (NoRoomFoundException e) {
                        if (EntryLine.split(",")[6].matches("SINGLE")) {
                        room = new Room(EntryLine.split(",")[3],Double.parseDouble(EntryLine.split(",")[7]),RoomType.SINGLE);
                        }
                        else {
                        room = new Room(EntryLine.split(",")[3],Double.parseDouble(EntryLine.split(",")[7]),RoomType.DOUBLE);
                        }
                        
                    }
                    RoomsToAdd.add(room);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
                    try {
                    checkInDate = dateFormat.parse(EntryLine.split(",")[4]);
                    checkOutDate = dateFormat.parse(EntryLine.split(",")[5]);
                    }
                    catch (ParseException e) {
                        System.out.println("The datasheet contained invalid date formats");
                        System.exit(0);
                    }
                    ReservationServiceInstance.reserveARoom(customer, room, checkInDate, checkOutDate);
                    System.out.println(String.format("Reservation for %s has been added to database",customer.getCustomerFirstName()));
            }
            ReservationServiceInstance.addRoom(RoomsToAdd);
        }
        catch (FileNotFoundException e) {
            System.out.println("Invalid file provided");
        }
    }
}

