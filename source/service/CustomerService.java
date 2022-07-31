package source.service;
import source.model.Customer;
import source.service.ServiceExceptions.*;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerService {
    private Collection<Customer> CustomerList;
    private static CustomerService CustomerServiceInstance = null;

    private CustomerService() {
        CustomerList = new ArrayList<Customer>();
    }

    public static CustomerService getInstance() {
        if (CustomerServiceInstance == null) {
            CustomerServiceInstance = new CustomerService();
        }
        return CustomerServiceInstance;
    }

    public void addCustomer(String FirstName, String LastName, String Email)  throws IllegalArgumentException{
        Customer customer = new Customer(FirstName, LastName, Email);
        CustomerList.add(customer);
    }   

    public Customer getCustomer(String Email) throws NoEmailFoundException {
        for (Customer cust:CustomerList) {
            if (cust.getEmail().matches(Email)) {
                return cust;
            }
        }
        throw new NoEmailFoundException(String.format("No customers found with email %s",Email));
    }

    public Collection<Customer> getAllCustomers() {
        return CustomerList;
    }

}
