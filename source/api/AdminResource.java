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
    
    private static CustomerService CustomerServiceInstance;
    private static ReservationService ReservationServiceInstance;
    private static AdminResource AdminResourceInstance = null;


    private AdminResource() {
                CustomerServiceInstance = CustomerService.getInstance();
                ReservationServiceInstance = ReservationService.getInstance();
    }

    public static AdminResource getInstance() {
        if (AdminResourceInstance == null) {
            AdminResourceInstance = new AdminResource();
        }
        return AdminResourceInstance;
    }

    public static CustomerService getCustomerInstance() {
        return CustomerServiceInstance;
    }

    public static ReservationService getReservationInstance() {
        return ReservationServiceInstance;
    }
    public Customer getCustomer(String email) throws NoEmailFoundException {
        CustomerService CustomerServiceInstance = CustomerService.getInstance();
        return CustomerServiceInstance.getCustomer(email);
    }

    public void addRoom(List<Room> rooms) {
        ReservationService ReservationServiceInstance = ReservationService.getInstance();
        ReservationServiceInstance.addRoom(rooms);
    }

    public Collection<Room> getAllRooms() {
        ReservationService ReservationServiceInstance = ReservationService.getInstance();
        return ReservationServiceInstance.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        CustomerService CustomerServiceInstance = CustomerService.getInstance();
        return CustomerServiceInstance.getAllCustomers();
    }

    public void displayAllReservations() {
        ReservationService ReservationServiceInstance = ReservationService.getInstance();
        ReservationServiceInstance.printAllReservation();
    }
}
