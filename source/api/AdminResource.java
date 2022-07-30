package source.api;

import source.model.Customer;
import source.model.Room;
import source.model.Reservation;


import source.service.CustomerService;
import source.service.ReservationService;
import source.service.ServiceExceptions.NoEmailFoundException;

import java.util.List;
import java.util.Collection;

public class AdminResource {
    public static Customer getCustomer(String email) throws NoEmailFoundException {
        return CustomerService.getCustomer(email);
    }

    public static void addRoom(List<Room> rooms) {
        ReservationService.addRoom(rooms);
    }

    public static Collection<Room> getAllRooms() {
        return ReservationService.getAllRooms();
    }

    public static Collection<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations() {
        ReservationService.printAllReservation();
    }
}
