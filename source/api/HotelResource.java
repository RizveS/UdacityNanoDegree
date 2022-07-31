package source.api;
import source.model.Customer;
import source.model.Room;
import source.model.Reservation;

import source.service.CustomerService;
import source.service.ServiceExceptions.*;
import source.service.ReservationService;

import java.util.Date;
import java.util.Collection;


public class HotelResource {
    private static CustomerService CustomerServiceInstance;
    private static ReservationService ReservationServiceInstance;
    private static HotelResource HotelResourceInstance;

    private HotelResource() {
        CustomerServiceInstance = CustomerService.getInstance();
        ReservationServiceInstance =  ReservationService.getInstance();
    }

    public static HotelResource getInstance() {
        if (HotelResourceInstance == null) {
            HotelResourceInstance = new HotelResource();
        }
        return HotelResourceInstance;
    }

    public CustomerService getCustomerInstance() {
        return CustomerServiceInstance;
    }

    public ReservationService getReservationInstance() {
        return ReservationServiceInstance;
    }
    
    public static Customer getCustomer(String Email) throws NoEmailFoundException {
        CustomerService CustomerServiceInstance = CustomerService.getInstance();
        Customer cust = CustomerServiceInstance.getCustomer(Email);
        return cust;
    
    }

    public static void createACustomer(String email, String firstName, String lastName) {
        CustomerService CustomerServiceInstance = CustomerService.getInstance();
        CustomerServiceInstance.addCustomer(firstName, lastName, email);
    }

    public static Room getRoom(String roomNumber) throws NoRoomFoundException {
        ReservationService ReservationServiceInstance = ReservationService.getInstance();
        return ReservationServiceInstance.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, Room room, Date checkInDate, Date checkOutDate) throws NoEmailFoundException {
        ReservationService ReservationServiceInstance = ReservationService.getInstance();
        CustomerService CustomerServiceInstance = CustomerService.getInstance();
        Customer cust = CustomerServiceInstance.getCustomer(customerEmail);
        return ReservationServiceInstance.reserveARoom(cust, room, checkInDate, checkOutDate);

    }

    public static Collection<Reservation> getCustomerReservations(String CustomerEmail) throws NoEmailFoundException, NoCustomerFoundException {
        ReservationService ReservationServiceInstance = ReservationService.getInstance();
        CustomerService CustomerServiceInstance = CustomerService.getInstance();
        Customer cust = CustomerServiceInstance.getCustomer(CustomerEmail);
        return ReservationServiceInstance.getCustomerReservation(cust); 
    }

    public static Collection<Room> findARoom(Date checkInDate, Date checkOutDate) throws NoRoomFoundException{
        ReservationService ReservationServiceInstance = ReservationService.getInstance();
        return ReservationServiceInstance.findRooms(checkInDate, checkOutDate);
    }
}
