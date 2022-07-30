package source.tests;
import source.api.*;
import source.model.*;

public class Test_Api {
    public static void main(String args[]) {
    //Add customers
    HotelResource.createACustomer("rizcho@gmail.com","Rizve","Chowdhury");
    HotelResource.createACustomer("nicoricci@gmail.com","Nicolina","Ricciardi");
    HotelResource.createACustomer("dylansandl@gmail.com", "Dylan", "Sandler");
    HotelResource.createACustomer("JohnDoe@gmail.com","John","Doe");
    for (Customer cust:AdminResource.getAllCustomers()) {
        System.out.println(cust.toString());
    }
    //Add rooms
    }

}
