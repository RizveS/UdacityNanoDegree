package source.model;

public class Customer {
    String firstName;
    String lastName;
    String email;

    public String toString() {
        String displayText = String.format("""
            This object represents a customer with properties: \n
            First Name: %s \n
            Last Name: %s \n
            Email: %s \n
        """,firstName,lastName,email);
        return displayText;
    }

    public Customer(String FirstName, String LastName, String Email) throws IllegalArgumentException {
        firstName = FirstName;
        lastName = LastName;
        if (Email.matches("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})")) {
            email = Email;
        }
        else {
            //Will be handled by caller of constructor
            throw new IllegalArgumentException("Invalid Email provided");
        }
    }

    public String getCustomerFirstName() {
        return this.firstName;
    }

    public String getCustomerLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;

    }
}