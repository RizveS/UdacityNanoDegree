package source.model;


public class Room implements iRoomInterface {
String roomNumber;
Double price;
RoomType roomType;


    public Room(String roomNumberInp, Double priceInp, RoomType roomTypeInp) {
        roomNumber = roomNumberInp;
        price = priceInp;
        roomType = roomTypeInp;
    }

    public String toString() {
        String displayText = String.format("""
            This object represents a room with properties: \n
            Room Number: %d \n
            Price: %f \n
            Type of Room: %s \n
        """,roomNumber,price,roomType);
        return displayText;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public Double getRoomPrice() {
        return this.price;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public boolean isFree() {
        return !(this.price > 0.0);
    }
}