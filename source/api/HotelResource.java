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
    public static Customer getCustomer(String Email) throws NoEmailFoundException {
        Customer cust = CustomerService.getCustomer(Email);
        return cust;
    
    }

    public static void createACustomer(String email, String firstName, String lastName) {
        CustomerService.addCustomer(firstName, lastName, email);
    }

    public static Room getRoom(String roomNumber) throws NoRoomFoundException {
            return ReservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, Room room, Date checkInDate, Date checkOutDate) throws NoEmailFoundException {
        Customer cust = CustomerService.getCustomer(customerEmail);
        return ReservationService.reserveARoom(cust, room, checkInDate, checkOutDate);

    }

    public static Collection<Reservation> getCustomerReservations(String CustomerEmail) throws NoEmailFoundException, NoCustomerFoundException {
        Customer cust = CustomerService.getCustomer(CustomerEmail);
        return ReservationService.getCustomerReservation(cust); 
    }

    public static Collection<Room> findARoom(Date checkInDate, Date checkOutDate) throws NoRoomFoundException{
        return ReservationService.findRooms(checkInDate, checkOutDate);
    }
}
