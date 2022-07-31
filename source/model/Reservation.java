package source.model;
import java.util.Date;

public class Reservation {
    Customer customer;
    Room room;
    Date checkInDate;
    Date checkOutDate;

    public String toString() {
        String displayText = String.format("""
        Customer First Name: %s
        Customer Last Name: %s
        Room Reserved by Customer: %s
        Check-In Date: %s
        Check-Out Date: %s 
        """,customer.getCustomerFirstName(),customer.getCustomerLastName()
        ,room.getRoomNumber(),checkInDate,checkOutDate);
        return displayText;

    }

    public Reservation(Customer customerInp, Room roomInp, Date checkInDateInp, Date checkOutDateInp) {
        customer = customerInp;
        room = roomInp;
        checkInDate = checkInDateInp;
        checkOutDate = checkOutDateInp;
    }

    public Date getCheckIn() {
        return checkInDate;
    }

    public Date getCheckOut() {
        return checkOutDate;
    }

    public String getEmail() {
        return customer.getEmail();
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean equals(Reservation reserv) {
        if ((this.customer == reserv.getCustomer())
        & (this.room == reserv.getRoom()) 
        & (this.checkInDate == reserv.getCheckIn())
        & (this.checkOutDate == reserv.getCheckOut())) {
            return true;
        }
        else {
            return false;
        } 
    }

}