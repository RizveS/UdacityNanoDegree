package source.model;
import java.util.Date;

public class Reservation {
    Customer customer;
    Room room;
    Date checkInDate;
    Date checkOutDate;

    public String toString() {
        String displayText = String.format("""
        This object represents a reservation with properties: \n
        Customer First Name: %s \n
        Customer Last Name: %s \n
        Room Reserved by Customer: %s\n
        Check-In Date: %s \n
        Check-Out Date: %s \n
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

}