package source.service;
import source.model.Customer;
import source.service.ServiceExceptions.*;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerService {
    static Collection<Customer> CustomerList = new ArrayList<>();

    static public void addCustomer(String FirstName, String LastName, String Email)  throws IllegalArgumentException{
        Customer customer = new Customer(FirstName, LastName, Email);
        CustomerList.add(customer);
    }   

    static public Customer getCustomer(String Email) throws NoEmailFoundException {
        for (Customer cust:CustomerList) {
            if (cust.getEmail().matches(Email)) {
                return cust;
            }
        }
        throw new NoEmailFoundException(String.format("No customers found with email %s",Email));
    }

    static public Collection<Customer> getAllCustomers() {
        return CustomerList;
    }

}
