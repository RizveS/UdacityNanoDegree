package source.model;

public class Customer {
    String firstName;
    String lastName;
    String email;

    public String toString() {
        String displayText = String.format("""
            First Name: %s
            Last Name: %s
            Email: %s
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

    public boolean equals(Customer customer) {
            if ((this.firstName.matches(customer.getCustomerFirstName())) &
            (this.lastName.matches(customer.getCustomerLastName())) &
            (this.email.matches(customer.getEmail()))) {
                return true;
            }
            else {
                return false;
            }
    }
}