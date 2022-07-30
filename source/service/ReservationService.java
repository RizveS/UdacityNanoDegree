package source.service;
import source.model.Room;
import source.model.FreeRoom;
import source.model.Customer;
import source.model.Reservation;

import source.service.ServiceExceptions.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ReservationService {
    static HashMap<String,List<Reservation>> ReservationList = new HashMap<String,List<Reservation>>();
    static List<Room> AllRooms = new ArrayList<>();
    static HashMap<String,Room> AvailableRooms = new HashMap<String,Room>();

    public static void addRoom(Collection<Room> rooms) {
        for (Room room: rooms) {
        AvailableRooms.put(room.getRoomNumber(),room);
        AllRooms.add(room);
        }
    };

    public static Room getARoom(String roomId) throws NoRoomFoundException{
        if (AvailableRooms.containsKey(roomId)){
            return AvailableRooms.get(roomId);
        }
        else {
            throw new NoRoomFoundException(String.format("No room was found with id: %s",roomId));
        }
    };

    public static Reservation reserveARoom(Customer customer,
    Room room, Date checkInDate, Date checkOutDate) {

        AvailableRooms.remove(room.getRoomNumber());
        Reservation customerReservation = new Reservation(customer,room,checkInDate,checkOutDate);
        /*if (ReservationList.containsKey(customer.getEmail())) {
            List<Reservation> prevReservations = ReservationList.get(customer.getEmail());
            for (Reservation reserv: prevReservations) {
                if (checkInDate == reserv.getCheckIn() & checkOutDate == reserv.getCheckOut()) {
                    throw new ReservationException("An existing reservation exists with the same check-in and check-out exists");
                }
            }
            prevReservations.add(customerReservation);
            ReservationList.put(customer.getEmail(),prevReservations);

        }
        else {
        List<Reservation> newReservationList;
        newReservationList.add(customerReservation);
        ReservationList.put(customer.getEmail(),newReservationList);
        }*/

        return customerReservation;

    };

    public static Collection<Room> findRooms(Date checkInDate,
    Date checkOutDate) throws NoRoomFoundException{
        List<Room> MatchedRooms = new ArrayList<>();
        for (Room room:AvailableRooms.values()) {
            MatchedRooms.add(room); //Rooms that are empty can be put up for reservation
        }
        //See what rooms currently reserved has a check-out date earlier than required check-in date
        //See what rooms currently reserved has a check-in date later than the required check-out date
        for (List<Reservation> reservList: ReservationList.values()) {
            for (Reservation reserv:reservList) {
                if (reserv.getCheckOut().before(checkInDate)) {
                    MatchedRooms.add(reserv.getRoom());
                }

                if (reserv.getCheckIn().after(checkOutDate)) {
                    MatchedRooms.add(reserv.getRoom());
                }
            }
        }
        if (MatchedRooms.size() < 1) {
            throw new NoRoomFoundException(String.format("No room available with provided check-in and check-out date"));
        }
        return MatchedRooms;
    };

    public static Collection<Reservation> getCustomerReservation(Customer customer) throws NoCustomerFoundException  {
        if (ReservationList.containsKey(customer.getEmail())) {
            return ReservationList.get(customer.getEmail());
        }
        else {
            throw new NoCustomerFoundException("No customer was found");
        }
    }

    public static Collection<Room> getAllRooms() {
        return AllRooms;
    }

    public static void printAllReservation() {
        System.out.println("**********************Printing out all the reservations****************************");
        for (List<Reservation> reservList:ReservationList.values()) {
            for (Reservation reserv: reservList) {
            System.out.println("-------------------------------------------------------------------------");
            System.out.println(reserv.toString());
            }
        }
    };

}
